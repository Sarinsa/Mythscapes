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
        this.setDefaultState(this.getDefaultState().with(EXTEND, false));
    }

    @Override
    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
        return state.getBlock().isIn(MythBlockTags.HEDGES);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return facing == Direction.UP && !state.get(WATERLOGGED) && plantable.getPlantType(world, pos) == PlantType.PLAINS;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IBlockReader world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockPos down = blockpos.down();
        BlockState downState = world.getBlockState(down);

        return super.getStateForPlacement(context).with(EXTEND, downState.getBlock().isIn(MythBlockTags.HEDGES));
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return facing == Direction.DOWN ? stateIn.with(EXTEND, facingState.getBlock().isIn(MythBlockTags.HEDGES)) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(EXTEND);
    }
}
