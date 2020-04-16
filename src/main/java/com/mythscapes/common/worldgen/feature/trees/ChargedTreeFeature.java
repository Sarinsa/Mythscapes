package com.mythscapes.common.worldgen.feature.trees;

import com.mojang.datafixers.Dynamic;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.function.Function;

public class ChargedTreeFeature extends TreeFeature {

    public ChargedTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> function) {
        super(function);
    }
}
