package com.mythscapes.common.blocks.wood;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;

public class ModSaplingBlock extends SaplingBlock {

    public ModSaplingBlock(Properties properties) {
        super(new OakTree(), properties);
    }
}
