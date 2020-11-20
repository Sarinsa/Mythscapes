package com.radish.mythscapes.common.blocks.plant;

import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class BlisterberryThistleBlock extends AbstractHarvestableBlock {

    private static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
    private static final BooleanProperty TOP = BooleanProperty.create("top");

    private static final VoxelShape[] lowerShapes = {
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
    };
    private static final VoxelShape[] upperShapes = {
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
    };

    public BlisterberryThistleBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(AGE, 0).with(TOP, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return this.isTop(state) ? lowerShapes[state.get(AGE)] : upperShapes[state.get(AGE)];
    }

    private boolean isTop(BlockState state) {
        return state.get(TOP);
    }

    @Override
    public IItemProvider getSeed() {
        return MythItems.BLISTERBERRY.get();
    }

    @Override
    public int getMaxAge() {
        return 5;
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
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        boolean isTop = this.isTop(state);

        if (facing.getAxis() != Direction.Axis.Y || isTop == (facing == Direction.UP) || facingState.isIn(this) && this.isTop(facingState) != isTop) {
            return !isTop && facing == Direction.DOWN && !state.isValidPosition(world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
        }
        else {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        BlockState aboveState = world.getBlockState(pos.up());
        return state.isIn(Blocks.FARMLAND) && (aboveState.getBlock().isAir(aboveState, world, pos.up()) || aboveState.isIn(this));
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        if (!this.isTop(state)) {
            return this.isValidGround(world.getBlockState(pos.down()), world, pos);
        }
        else {
            BlockState blockState = world.getBlockState(pos.down());

            if (world.getLightSubtracted(pos, 0) >= 8 || world.canSeeSky(pos)) {
                return blockState.isIn(this) && !this.isTop(blockState);
            }
            else {
                return false;
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (this.isMature(state)) {
            if (!this.isTop(state)) {
                world.setBlockState(pos, this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, false), 2);
                world.setBlockState(pos.up(), this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, true), 2);
            }
            else {
                world.setBlockState(pos.down(), this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, false), 2);
                world.setBlockState(pos, this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, true), 2);
            }

            if (player.getHeldItem(hand).getItem().isIn(Tags.Items.SHEARS)) {
                if (!world.isRemote) {
                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MythItems.BLISTERBERRY.get()));
                }
                return ActionResultType.SUCCESS;
            }
            else {
                if (!world.isRemote) {
                    world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2.0f, true, Explosion.Mode.NONE);
                }
                return ActionResultType.PASS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light

        if (this.canGrow(world, pos, state, world.isRemote)) {

            if (ForgeHooks.onCropsGrowPre(world, pos, state, true) && random.nextInt(23) == 0) {
                int age = this.getAge(state);
                int newAge = age + 1;

                if (this.isTop(state)) {
                    world.setBlockState(pos, this.setStateWithAgeAndTop(newAge, true), 2);
                    world.setBlockState(pos.down(), this.setStateWithAgeAndTop(newAge, false), 2);
                }
                else {
                    world.setBlockState(pos, this.setStateWithAgeAndTop(newAge, false), 2);

                    if (newAge > this.getAgeBeforeDouble())
                        world.setBlockState(pos.up(), this.setStateWithAgeAndTop(newAge, true), 2);
                }
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    private BlockState setStateWithAgeAndTop(int age, boolean top) {
        return this.getDefaultState().with(AGE, age).with(TOP, top);
    }

    private int getAgeBeforeDouble() {
        return 1;
    }

    @Override
    public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        BlockState aboveState = world.getBlockState(pos.up());
        return pos.getY() < 255 && !this.isMature(state) && (aboveState.getBlock().isAir(aboveState, world, pos.up()) || aboveState.isIn(this));
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int newAge = this.getAge(state) + 1;

        if (newAge > this.getMaxAge())
            newAge = this.getMaxAge();

        if (this.isTop(state)) {
            world.setBlockState(pos, this.setStateWithAgeAndTop(newAge, true), 2);
            world.setBlockState(pos.down(), this.setStateWithAgeAndTop(newAge, false), 2);
        }
        else {
            world.setBlockState(pos, this.setStateWithAgeAndTop(newAge, false), 2);

            if (newAge > this.getAgeBeforeDouble())
                world.setBlockState(pos.up(), this.setStateWithAgeAndTop(newAge, true), 2);
        }
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, TOP);
    }
}
