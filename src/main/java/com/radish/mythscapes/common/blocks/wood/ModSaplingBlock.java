package com.radish.mythscapes.common.blocks.wood;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.OakTree;

public class ModSaplingBlock extends SaplingBlock {

    public ModSaplingBlock(Properties properties) {
        super(new OakTree(), properties);
    }
}
