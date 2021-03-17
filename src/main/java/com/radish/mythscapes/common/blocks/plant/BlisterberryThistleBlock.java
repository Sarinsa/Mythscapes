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

import java.util.Base64;
import java.util.Random;

public class BlisterberryThistleBlock extends AbstractHarvestableBlock {

    private static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    private static final BooleanProperty TOP = BooleanProperty.create("top");

    private static final VoxelShape[] lowerShapes = {
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
    };
    private static final VoxelShape[] upperShapes = {
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D),
    };

    public BlisterberryThistleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(TOP, false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return this.isTop(state) ? lowerShapes[state.getValue(AGE)] : upperShapes[state.getValue(AGE)];
    }

    private boolean isTop(BlockState state) {
        return state.getValue(TOP);
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
        return state.getValue(AGE);
    }

    @Override
    boolean isMature(BlockState state) {
        return state.getValue(AGE) >= getMaxAge();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        boolean isTop = this.isTop(state);

        if (facing.getAxis() != Direction.Axis.Y || isTop == (facing == Direction.UP) || facingState.is(this) && this.isTop(facingState) != isTop) {
            return !isTop && facing == Direction.DOWN && !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
        }
        else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public boolean mayPlaceOn(BlockState state, IBlockReader world, BlockPos pos) {
        BlockState aboveState = world.getBlockState(pos.above());
        return state.is(Blocks.FARMLAND) && (aboveState.getBlock().isAir(aboveState, world, pos.above()) || aboveState.is(this));
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        if (!this.isTop(state)) {
            return this.mayPlaceOn(world.getBlockState(pos.below()), world, pos);
        }
        else {
            BlockState blockState = world.getBlockState(pos.below());

            if (world.getRawBrightness(pos, 0) >= 8 || world.canSeeSky(pos)) {
                return blockState.is(this) && !this.isTop(blockState);
            }
            else {
                return false;
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (this.isMature(state)) {
            if (!this.isTop(state)) {
                world.setBlock(pos, this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, false), 2);
                world.setBlock(pos.above(), this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, true), 2);
            }
            else {
                world.setBlock(pos.below(), this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, false), 2);
                world.setBlock(pos, this.setStateWithAgeAndTop(this.getAgeBeforeDouble() + 1, true), 2);
            }

            if (player.getItemInHand(hand).getItem().is(Tags.Items.SHEARS)) {
                if (!world.isClientSide) {
                    InventoryHelper.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(MythItems.BLISTERBERRY.get()));
                }
                return ActionResultType.SUCCESS;
            }
            else {
                if (!world.isClientSide) {
                    world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 2.0f, true, Explosion.Mode.NONE);
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

        if (this.isValidBonemealTarget(world, pos, state, world.isClientSide)) {

            if (ForgeHooks.onCropsGrowPre(world, pos, state, true) && random.nextInt(23) == 0) {
                int age = this.getAge(state);
                int newAge = age + 1;

                if (this.isTop(state)) {
                    world.setBlock(pos, this.setStateWithAgeAndTop(newAge, true), 2);
                    world.setBlock(pos.below(), this.setStateWithAgeAndTop(newAge, false), 2);
                }
                else {
                    world.setBlock(pos, this.setStateWithAgeAndTop(newAge, false), 2);

                    if (newAge > this.getAgeBeforeDouble())
                        world.setBlock(pos.above(), this.setStateWithAgeAndTop(newAge, true), 2);
                }
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    private BlockState setStateWithAgeAndTop(int age, boolean top) {
        return this.defaultBlockState().setValue(AGE, age).setValue(TOP, top);
    }

    private int getAgeBeforeDouble() {
        return 1;
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
        BlockState aboveState = world.getBlockState(pos.above());
        return pos.getY() < 255 && !this.isMature(state) && (aboveState.getBlock().isAir(aboveState, world, pos.above()) || aboveState.is(this));
    }

    @Override
    public boolean isBonemealSuccess(World world, Random random, BlockPos blockPos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int newAge = this.getAge(state) + 1;

        if (newAge > this.getMaxAge())
            newAge = this.getMaxAge();

        if (this.isTop(state)) {
            world.setBlock(pos, this.setStateWithAgeAndTop(newAge, true), 2);
            world.setBlock(pos.below(), this.setStateWithAgeAndTop(newAge, false), 2);
        }
        else {
            world.setBlock(pos, this.setStateWithAgeAndTop(newAge, false), 2);

            if (newAge > this.getAgeBeforeDouble())
                world.setBlock(pos.above(), this.setStateWithAgeAndTop(newAge, true), 2);
        }
    }

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, TOP);
    }
}
