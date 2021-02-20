package com.radish.mythscapes.common.blocks.plant;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ModLeavesBlock extends LeavesBlock {

    private final int flammability;
    private final int fireSpread;

    public ModLeavesBlock(Properties properties) {
        this(properties, 60, 30);
        // Default values
    }

    public ModLeavesBlock(Properties properties, int flammability, int fireSpread) {
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
