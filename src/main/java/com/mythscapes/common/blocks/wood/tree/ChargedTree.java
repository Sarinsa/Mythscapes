package com.mythscapes.common.blocks.wood.tree;

import com.mythscapes.common.worldgen.feature.trees.ChargedTreeFeature;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class ChargedTree extends Tree {



    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
        //return new ChargedTreeFeature();
        return null;
    }
}
