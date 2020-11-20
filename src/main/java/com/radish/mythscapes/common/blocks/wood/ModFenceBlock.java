package com.radish.mythscapes.common.blocks.wood;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ModFenceBlock extends FenceBlock {

    private final int flammability;
    private final int fireSpread;

    public ModFenceBlock(Properties properties) {
        super(properties);
        // Default values
        this.flammability = 5;
        this.fireSpread = 5;
    }

    public ModFenceBlock(Properties properties, int flammability, int fireSpread) {
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
