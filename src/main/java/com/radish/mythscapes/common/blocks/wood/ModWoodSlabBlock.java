package com.radish.mythscapes.common.blocks.wood;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ModWoodSlabBlock extends SlabBlock {

    private final int flammability;
    private final int fireSpread;

    public ModWoodSlabBlock(AbstractBlock.Properties properties) {
        super(properties);
        // Default values
        this.flammability = 5;
        this.fireSpread = 5;
    }

    public ModWoodSlabBlock(AbstractBlock.Properties properties, int flammability, int fireSpread) {
        super(properties);
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
