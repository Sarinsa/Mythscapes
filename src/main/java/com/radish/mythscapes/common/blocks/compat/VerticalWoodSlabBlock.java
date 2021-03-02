package com.radish.mythscapes.common.blocks.compat;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class VerticalWoodSlabBlock extends VerticalSlabBlock {

    private final int flammability;
    private final int fireSpread;

    public VerticalWoodSlabBlock(Properties properties) {
        this(properties, 5, 5);
    }

    public VerticalWoodSlabBlock(Properties properties, int flammability, int fireSpread) {
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
