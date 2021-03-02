package com.radish.mythscapes.common.blocks.compat;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

/**
 * Quark Wood Post block
 */
public class WoodPostBlock extends Block implements IWaterLoggable {

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    private static final VoxelShape SHAPE_X = Block.makeCuboidShape(0.0F, 6.0F, 6.0F, 16.0F, 10.0F, 10.0F);
    private static final VoxelShape SHAPE_Y = Block.makeCuboidShape(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F);
    private static final VoxelShape SHAPE_Z = Block.makeCuboidShape(6.0F, 6.0F, 0.0F, 10.0F, 10.0F, 16.0F);

    private static final BooleanProperty[] CHAINED = {
            BooleanProperty.create("chain_down"),
            BooleanProperty.create("chain_up"),
            BooleanProperty.create("chain_north"),
            BooleanProperty.create("chain_south"),
            BooleanProperty.create("chain_west"),
            BooleanProperty.create("chain_east")
    };

    public WoodPostBlock(Properties properties) {
        super(properties);
        BlockState defaultState = this.stateContainer.getBaseState().with(WATERLOGGED, false).with(AXIS, Direction.Axis.Y);

        for (BooleanProperty property : CHAINED) {
            defaultState = defaultState.with(property, false);
        }
        this.setDefaultState(defaultState);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch (state.get(AXIS)) {
            case X:
                return SHAPE_X;
            default:
            case Y:
                return SHAPE_Y;
            case Z:
                return SHAPE_Z;
        }
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack itemStack, ToolType toolType) {
        if (toolType == ToolType.AXE && AxeItem.BLOCK_STRIPPING_MAP.containsKey(this)) {
            if (state.isIn(AxeItem.BLOCK_STRIPPING_MAP.get(this))) {
                BlockState newState = AxeItem.BLOCK_STRIPPING_MAP.get(this).getDefaultState();

                return newState.with(WATERLOGGED, state.get(WATERLOGGED)).with(AXIS, state.get(AXIS));
            }
        }
        return super.getToolModifiedState(state, world, pos, player, itemStack, toolType);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, world, pos, block, fromPos, isMoving);

        BlockState newState = this.getStateForPlacing(world, pos, state.get(AXIS));

        if(!newState.equals(state))
            world.setBlockState(pos, newState);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getStateForPlacing(context.getWorld(), context.getPos(), context.getFace().getAxis());
    }

    private BlockState getStateForPlacing(World world, BlockPos pos, Direction.Axis axis) {
        BlockState state = getDefaultState().with(WATERLOGGED, world.getFluidState(pos).getFluid() == Fluids.WATER).with(AXIS, axis);

        for(Direction direction : Direction.values()) {
            if(direction.getAxis() == axis) {
                continue;
            }

            BlockState sideState = world.getBlockState(pos.offset(direction));
            if((sideState.getBlock() instanceof ChainBlock && sideState.get(BlockStateProperties.AXIS) == direction.getAxis()) || (direction == Direction.DOWN && sideState.getBlock() instanceof LanternBlock && sideState.get(LanternBlock.HANGING))) {
                BooleanProperty prop = CHAINED[direction.ordinal()];
                state = state.with(prop, true);
            }
        }
        return state;
    }


    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader world, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS);

        for (BooleanProperty property : CHAINED)
            builder.add(property);
    }
}
