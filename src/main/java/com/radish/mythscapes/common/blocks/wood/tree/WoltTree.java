package com.radish.mythscapes.common.blocks.wood.tree;

import com.radish.mythscapes.common.worldgen.MythConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WoltTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean large) {
        return MythConfiguredFeatures.WOLT_TREE;
    }
}
