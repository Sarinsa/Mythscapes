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

    private static final VoxelShape SHAPE_X = Block.box(0.0F, 6.0F, 6.0F, 16.0F, 10.0F, 10.0F);
    private static final VoxelShape SHAPE_Y = Block.box(6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 10.0F);
    private static final VoxelShape SHAPE_Z = Block.box(6.0F, 6.0F, 0.0F, 10.0F, 10.0F, 16.0F);

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
        BlockState defaultState = this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(AXIS, Direction.Axis.Y);

        for (BooleanProperty property : CHAINED) {
            defaultState = defaultState.setValue(property, false);
        }
        this.registerDefaultState(defaultState);
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
        switch (state.getValue(AXIS)) {
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
        if (toolType == ToolType.AXE && AxeItem.STRIPABLES.containsKey(this)) {
            if (state.is(AxeItem.STRIPABLES.get(this))) {
                BlockState newState = AxeItem.STRIPABLES.get(this).defaultBlockState();

                return newState.setValue(WATERLOGGED, state.getValue(WATERLOGGED)).setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, world, pos, player, itemStack, toolType);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, world, pos, block, fromPos, isMoving);

        BlockState newState = this.getStateForPlacing(world, pos, state.getValue(AXIS));

        if(!newState.equals(state))
            world.setBlockAndUpdate(pos, newState);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getStateForPlacing(context.getLevel(), context.getClickedPos(), context.getClickedFace().getAxis());
    }

    private BlockState getStateForPlacing(World world, BlockPos pos, Direction.Axis axis) {
        BlockState state = defaultBlockState().setValue(WATERLOGGED, world.getFluidState(pos).getType() == Fluids.WATER).setValue(AXIS, axis);

        for(Direction direction : Direction.values()) {
            if(direction.getAxis() == axis) {
                continue;
            }

            BlockState sideState = world.getBlockState(pos.relative(direction));
            if((sideState.getBlock() instanceof ChainBlock && sideState.getValue(BlockStateProperties.AXIS) == direction.getAxis()) || (direction == Direction.DOWN && sideState.getBlock() instanceof LanternBlock && sideState.getValue(LanternBlock.HANGING))) {
                BooleanProperty prop = CHAINED[direction.ordinal()];
                state = state.setValue(prop, true);
            }
        }
        return state;
    }


    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader world, BlockPos pos) {
        return !state.getValue(WATERLOGGED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, AXIS);

        for (BooleanProperty property : CHAINED)
            builder.add(property);
    }
}
