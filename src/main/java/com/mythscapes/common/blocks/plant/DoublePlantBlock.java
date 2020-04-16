package com.mythscapes.common.blocks.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Only used for the blister berry bush thus far.
 */
public class DoublePlantBlock extends BushBlock {

    public static final BooleanProperty TOP_BLOCK = BooleanProperty.create("top_block");

    public DoublePlantBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(TOP_BLOCK, false));
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        if (state.get(TOP_BLOCK)) {
            return block == this && !state.get(TOP_BLOCK);
        }
        else {
            return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND;
        }
    }


    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(TOP_BLOCK);
    }
}
