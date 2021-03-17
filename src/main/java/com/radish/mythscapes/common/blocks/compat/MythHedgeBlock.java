package com.radish.mythscapes.common.blocks.compat;

import com.radish.mythscapes.common.blocks.wood.ModWoodFenceBlock;
import com.radish.mythscapes.common.tags.MythBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class MythHedgeBlock extends ModWoodFenceBlock {

    public static final BooleanProperty EXTEND = BooleanProperty.create("extend");

    public MythHedgeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(EXTEND, false));
    }

    @Override
    public boolean connectsTo(BlockState state, boolean isSideSolid, Direction direction) {
        return state.getBlock().is(MythBlockTags.HEDGES);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return facing == Direction.UP && !state.getValue(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockPos down = blockpos.below();
        BlockState downState = world.getBlockState(down);

        return super.getStateForPlacement(context).setValue(EXTEND, downState.getBlock().is(MythBlockTags.HEDGES));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }
        return facing == Direction.DOWN ? stateIn.setValue(EXTEND, facingState.getBlock().is(MythBlockTags.HEDGES)) : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
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
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(EXTEND);
    }
}
