package com.radish.mythscapes.common.blocks.wood;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;

public class ModSaplingBlock extends SaplingBlock {

    public ModSaplingBlock(Tree tree, Properties properties) {
        super(tree, properties);
    }

    // For saplings that doesn't have proper
    // tree gen yet.
    public ModSaplingBlock(Properties properties) {
        super(new OakTree(), properties);
    }
}
