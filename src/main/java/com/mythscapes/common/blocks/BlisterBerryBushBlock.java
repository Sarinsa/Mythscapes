package com.mythscapes.common.blocks;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class BlisterBerryBushBlock extends AbstractHarvestableBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    private static final VoxelShape SHAPE_0 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    private static final VoxelShape SHAPE_1 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public BlisterBerryBushBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return state.get(AGE) > 0 ? SHAPE_1 : SHAPE_0;
    }

    @Override
    public IItemProvider getSeed() {
        return MythItems.BLISTER_BERRY.get();
    }

    public int getAge(BlockState blockState) {
        return blockState.get(AGE);
    }

    public int getMaxAge() {
        return 2;
    }

    public boolean isMature(BlockState blockState) {
        return this.getAge(blockState) >= this.getMaxAge();
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return !this.isMature(state) ? 0 : 3;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos above = pos.up();
        BlockState aboveState = world.getBlockState(above);

        if (this.getAge(state) > 0) {
            return this.isValidGround(state, world, pos) && (aboveState.getBlock() == MythBlocks.BLISTER_BERRY_BUSH_TOP.get());
        }
        return this.isValidGround(state, world, pos);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        Block block = state.getBlock();
        return block == MythBlocks.CHARGED_PLANKS.get();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (this.isMature(state)) {
            if (player.getHeldItem(hand).getItem() == Items.SHEARS) {
                world.setBlockState(pos, state.with(AGE, 1), 2);
                world.setBlockState(pos.up(), MythBlocks.BLISTER_BERRY_BUSH_TOP.get().getDefaultState().with(BlisterBerryBushTopBlock.AGE, 0), 2);

                if (!world.isRemote) {
                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MythItems.BLISTER_BERRY.get()));
                }
                return ActionResultType.SUCCESS;
            }
            else {
                world.setBlockState(pos, state.with(AGE, 1), 2);
                world.setBlockState(pos.up(), MythBlocks.BLISTER_BERRY_BUSH_TOP.get().getDefaultState().with(BlisterBerryBushTopBlock.AGE, 0), 2);

                if (!world.isRemote) {
                    world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2.0f, true, Explosion.Mode.NONE);
                }
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.tick(state, world, pos, rand);

        // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (!world.isAreaLoaded(pos, 1))
            return;
        int age = this.getAge(state);

        if (this.canGrow(world, pos, state, false)) {
            Mythscapes.LOGGER.info("Can grow");

            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(35) == 0)) {
                Mythscapes.LOGGER.info("Grew!!");
                if (age == 0) {
                    world.setBlockState(pos, this.getDefaultState().with(AGE, 1), 2);
                    world.setBlockState(pos.up(), MythBlocks.BLISTER_BERRY_BUSH_TOP.get().getDefaultState(), 2);
                } else {
                    world.setBlockState(pos, this.getDefaultState().with(AGE, 2), 2);
                    world.setBlockState(pos.up(), MythBlocks.BLISTER_BERRY_BUSH_TOP.get().getDefaultState().with(BlisterBerryBushTopBlock.AGE, 1), 2);
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
        else {
            Mythscapes.LOGGER.info("Cannot grow");
        }
    }

    @Override
    public boolean canGrow(IBlockReader blockReader, BlockPos pos, BlockState state, boolean b) {
        boolean isAirAbove = blockReader.getBlockState(pos.up()).isAir(blockReader, pos.up());
        return this.getAge(state) == 0 ? isAirAbove : !this.isMature(state);
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos above = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        int age = this.getAge(state);

        if (age == 0) {
            world.setBlockState(pos, this.getDefaultState().with(AGE, 1));
            world.setBlockState(above, MythBlocks.BLISTER_BERRY_BUSH_TOP.get().getDefaultState().with(AGE, 0));
        } else {
            world.setBlockState(pos, this.getDefaultState().with(AGE, 2));
            world.setBlockState(above, MythBlocks.BLISTER_BERRY_BUSH_TOP.get().getDefaultState().with(AGE, 0));
        }
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
