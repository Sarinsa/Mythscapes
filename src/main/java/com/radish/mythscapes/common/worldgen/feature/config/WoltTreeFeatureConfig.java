package com.radish.mythscapes.common.worldgen.feature.config;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.AbstractFeatureSizeType;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;

import java.util.List;

public class WoltTreeFeatureConfig extends BaseTreeFeatureConfig {

    public WoltTreeFeatureConfig(BlockStateProvider logProvider, BlockStateProvider leavesProvider, FoliagePlacer foliagePlacer, AbstractTrunkPlacer trunkPlacer, AbstractFeatureSizeType sizeType, List<TreeDecorator> treeDecorators, int maxWaterDepth, boolean ignoreVines, Heightmap.Type heightMapType) {
        super(logProvider, leavesProvider, foliagePlacer, trunkPlacer, sizeType, treeDecorators, maxWaterDepth, ignoreVines, heightMapType);
    }
}
