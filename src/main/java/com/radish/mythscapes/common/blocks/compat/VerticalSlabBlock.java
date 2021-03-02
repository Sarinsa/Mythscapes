package com.radish.mythscapes.common.blocks.compat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import org.jetbrains.annotations.Nullable;

/**
 * Based on Quark's Vertical Slab block
 */
public class VerticalSlabBlock extends Block implements IWaterLoggable {

    public static final EnumProperty<Type> SLAB_TYPE = EnumProperty.create("type", Type.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape NORTH = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    private static final VoxelShape DOUBLE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public VerticalSlabBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(SLAB_TYPE, Type.NORTH).with(WATERLOGGED, false));
    }
    @Override
    @SuppressWarnings("deprecation")
    public boolean isTransparent(BlockState blockState) {
        return blockState.get(SLAB_TYPE) != Type.DOUBLE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        if (state.get(SLAB_TYPE) == Type.DOUBLE)
            return state;

        return state.with(SLAB_TYPE, Type.getTypeFromDirection(rot.rotate(state.get(SLAB_TYPE).getDirection())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        if (state.get(SLAB_TYPE) == Type.DOUBLE)
            return state;

        return state.rotate(mirror.toRotation(state.get(SLAB_TYPE).getDirection()));
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(SLAB_TYPE) != Type.DOUBLE) {
            switch (state.get(SLAB_TYPE)) {
                default:
                case NORTH:
                    return NORTH;
                case SOUTH:
                    return SOUTH;
                case WEST:
                    return WEST;
                case EAST:
                    return EAST;
            }
        }
        return DOUBLE;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        BlockState state = context.getWorld().getBlockState(blockpos);

        if(state.getBlock() == this)
            return state.with(SLAB_TYPE, Type.DOUBLE).with(WATERLOGGED, false);

        FluidState fluid = context.getWorld().getFluidState(blockpos);
        BlockState newState = getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
        Direction direction = getDirectionForPlacement(context);

        return newState.with(SLAB_TYPE, Type.getTypeFromDirection(direction));
    }

    private Direction getDirectionForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace();
        if(direction.getAxis() != Direction.Axis.Y)
            return direction;

        BlockPos pos = context.getPos();
        Vector3d vec = context.getHitVec().subtract(new Vector3d(pos.getX(), pos.getY(), pos.getZ())).subtract(0.5, 0, 0.5);
        double angle = Math.atan2(vec.x, vec.z) * -180.0 / Math.PI;
        return Direction.fromAngle(angle).getOpposite();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
        ItemStack itemstack = useContext.getItem();
        Type type = state.get(SLAB_TYPE);
        Direction direction = type.getDirection();

        return type != Type.DOUBLE && itemstack.getItem() == asItem() && useContext.replacingClickedOnBlock() &&
                (useContext.getFace() == direction && getDirectionForPlacement(useContext) == direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean receiveFluid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(SLAB_TYPE) != Type.DOUBLE && IWaterLoggable.super.receiveFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canContainFluid(IBlockReader world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(SLAB_TYPE) != Type.DOUBLE && IWaterLoggable.super.canContainFluid(world, pos, state, fluid);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if(state.get(WATERLOGGED))
            world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));

        return super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return type == PathType.WATER && worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SLAB_TYPE, WATERLOGGED);
    }


    public enum Type implements IStringSerializable {
        NORTH(Direction.NORTH),
        EAST(Direction.EAST),
        SOUTH(Direction.SOUTH),
        WEST(Direction.WEST),
        DOUBLE(null);

        Type(@Nullable Direction direction) {
            this.name = direction == null ? "double" : direction.getString();
            this.direction = direction;
        }

        private final Direction direction;
        private final String name;

        @Override
        public String getString() {
            return this.name;
        }

        public Direction getDirection() {
            return this.direction;
        }

        public static Type getTypeFromDirection(Direction direction) {
            switch (direction) {
                default:
                case NORTH:
                    return NORTH;
                case WEST:
                    return WEST;
                case EAST:
                    return EAST;
                case SOUTH:
                    return SOUTH;
            }
        }
    }
}
