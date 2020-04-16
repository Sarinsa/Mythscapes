package com.mythscapes.common.blocks.plant;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlisterberryThistleBlock extends AbstractHarvestableBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    private static final VoxelShape SHAPE_0 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    private static final VoxelShape SHAPE_1 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public BlisterberryThistleBlock(int maxAge, Properties properties) {
        super(maxAge, properties);
        this.setDefaultState(this.getDefaultState().with(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return state.get(AGE) > 0 ? SHAPE_1 : SHAPE_0;
    }

    @Override
    public IItemProvider getSeed() {
        return MythItems.BLISTERBERRY.get();
    }

    @Override
    int getAge(BlockState state) {
        return state.get(AGE);
    }

    @Override
    boolean isMature(BlockState state) {
        return state.get(AGE) >= getMaxAge();
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return !this.isMature(state) ? 0 : 5;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        return this.isValidGround(world.getBlockState(pos.down()), world, pos.down());
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        Block block = state.getBlock();
        Mythscapes.LOGGER.info(block.getTranslationKey());
        return block == MythBlocks.WOLT_PLANKS.get();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (this.isMature(state)) {
            if (player.getHeldItem(hand).getItem() == Items.SHEARS) {
                world.setBlockState(pos, state.with(AGE, 1), 2);
                world.setBlockState(pos.up(), MythBlocks.BLISTERBERRY_THISTLE_TOP.get().getDefaultState().with(BlisterberryThistleTopBlock.AGE, 0), 2);

                if (!world.isRemote) {
                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MythItems.BLISTERBERRY.get()));
                }
                return ActionResultType.SUCCESS;
            }
            else {
                world.setBlockState(pos, state.with(AGE, 1), 2);
                world.setBlockState(pos.up(), MythBlocks.BLISTERBERRY_THISTLE_TOP.get().getDefaultState().with(BlisterberryThistleTopBlock.AGE, 0), 2);

                if (!world.isRemote) {
                    world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2.0f, true, Explosion.Mode.NONE);
                }
                return ActionResultType.PASS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (!world.isAreaLoaded(pos, 1))
            return;

        int age = this.getAge(state);

        if (this.canGrow(world, pos, state, false)) {

            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(35) == 0)) {
                if (age == 0) {
                    world.setBlockState(pos, this.getDefaultState().with(AGE, 1), 2);
                    world.setBlockState(pos.up(), MythBlocks.BLISTERBERRY_THISTLE_TOP.get().getDefaultState(), 2);
                } else {
                    world.setBlockState(pos, this.getDefaultState().with(AGE, 2), 2);
                    world.setBlockState(pos.up(), MythBlocks.BLISTERBERRY_THISTLE_TOP.get().getDefaultState().with(BlisterberryThistleTopBlock.AGE, 1), 2);
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state);
            }
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
            world.setBlockState(above, MythBlocks.BLISTERBERRY_THISTLE_TOP.get().getDefaultState().with(AGE, 0));
        } else {
            world.setBlockState(pos, this.getDefaultState().with(AGE, 2));
            world.setBlockState(above, MythBlocks.BLISTERBERRY_THISTLE_TOP.get().getDefaultState().with(AGE, 0));
        }
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
