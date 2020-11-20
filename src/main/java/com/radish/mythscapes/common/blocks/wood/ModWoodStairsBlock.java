package com.radish.mythscapes.common.blocks.wood;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class ModWoodStairsBlock extends StairsBlock {

    private final int flammability;
    private final int fireSpread;

    public ModWoodStairsBlock(Supplier<BlockState> stateSupplier, AbstractBlock.Properties properties) {
        super(stateSupplier, properties);
        // Default values
        this.flammability = 5;
        this.fireSpread = 5;
    }

    public ModWoodStairsBlock(Supplier<BlockState> stateSupplier, AbstractBlock.Properties properties, int flammability, int fireSpread) {
        super(stateSupplier, properties);
        this.flammability = flammability;
        this.fireSpread = fireSpread;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.flammability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return this.fireSpread;
    }
}
