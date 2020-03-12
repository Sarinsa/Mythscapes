package com.mythscapes.common.blocks;

import com.mythscapes.register.MythBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlisterBerryBushTopBlock extends DoublePlantBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 1);
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public BlisterBerryBushTopBlock(Properties properties, Block mainPlantBlock) {
        super(properties, mainPlantBlock);
        this.setDefaultState(this.getDefaultState().with(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        Block block = state.getBlock();
        return block == MythBlocks.BLISTER_BERRY_BUSH.get();
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
