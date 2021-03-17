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

    private static final VoxelShape NORTH = Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST = Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST = Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    private static final VoxelShape DOUBLE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public VerticalSlabBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SLAB_TYPE, Type.NORTH).setValue(WATERLOGGED, false));
    }
    @Override
    @SuppressWarnings("deprecation")
    public boolean useShapeForLightOcclusion(BlockState blockState) {
        return blockState.getValue(SLAB_TYPE) != Type.DOUBLE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rot) {
        if (state.getValue(SLAB_TYPE) == Type.DOUBLE)
            return state;

        return state.setValue(SLAB_TYPE, Type.getTypeFromDirection(rot.rotate(state.getValue(SLAB_TYPE).getDirection())));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        if (state.getValue(SLAB_TYPE) == Type.DOUBLE)
            return state;

        return state.rotate(mirror.getRotation(state.getValue(SLAB_TYPE).getDirection()));
    }


    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.getValue(SLAB_TYPE) != Type.DOUBLE) {
            switch (state.getValue(SLAB_TYPE)) {
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
        BlockPos blockpos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(blockpos);

        if(state.getBlock() == this)
            return state.setValue(SLAB_TYPE, Type.DOUBLE).setValue(WATERLOGGED, false);

        FluidState fluid = context.getLevel().getFluidState(blockpos);
        BlockState newState = defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
        Direction direction = getDirectionForPlacement(context);

        return newState.setValue(SLAB_TYPE, Type.getTypeFromDirection(direction));
    }

    private Direction getDirectionForPlacement(BlockItemUseContext context) {
        Direction direction = context.getClickedFace();
        if(direction.getAxis() != Direction.Axis.Y)
            return direction;

        BlockPos pos = context.getClickedPos();
        Vector3d vec = context.getClickLocation().subtract(new Vector3d(pos.getX(), pos.getY(), pos.getZ())).subtract(0.5, 0, 0.5);
        double angle = Math.atan2(vec.x, vec.z) * -180.0 / Math.PI;
        return Direction.fromYRot(angle).getOpposite();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
        ItemStack itemstack = useContext.getItemInHand();
        Type type = state.getValue(SLAB_TYPE);
        Direction direction = type.getDirection();

        return type != Type.DOUBLE && itemstack.getItem() == asItem() && useContext.replacingClickedOnBlock() &&
                (useContext.getClickedFace() == direction && getDirectionForPlacement(useContext) == direction);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.getValue(SLAB_TYPE) != Type.DOUBLE && IWaterLoggable.super.placeLiquid(world, pos, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(IBlockReader world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.getValue(SLAB_TYPE) != Type.DOUBLE && IWaterLoggable.super.canPlaceLiquid(world, pos, state, fluid);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
        if(state.getValue(WATERLOGGED))
            world.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return type == PathType.WATER && worldIn.getFluidState(pos).is(FluidTags.WATER);
    }

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(SLAB_TYPE, WATERLOGGED);
    }


    public enum Type implements IStringSerializable {

        NORTH(Direction.NORTH),
        EAST(Direction.EAST),
        SOUTH(Direction.SOUTH),
        WEST(Direction.WEST),
        DOUBLE(null);

        Type(@Nullable Direction direction) {
            this.name = direction == null ? "double" : direction.toString();
            this.direction = direction;
        }

        private final Direction direction;
        private final String name;

        @Override
        public String getSerializedName() {
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
