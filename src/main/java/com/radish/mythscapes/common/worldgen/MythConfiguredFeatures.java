package com.radish.mythscapes.common.worldgen;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.worldgen.feature.config.WoltTreeFeatureConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.LakeLava;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class MythConfiguredFeatures {

    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> WOLT_TREE;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> WOLT_TREE_BEES;
    public static ConfiguredFeature<?, ?> STATIC_PLAINS_TREES;

    public static ConfiguredFeature<?, ?> PATCH_CHARGED_DANDELIONS;

    public static ConfiguredFeature<?, ?> GALVITE_GOLD_ORE;
    public static ConfiguredFeature<?, ?> GALVITE_DIAMOND_ORE;
    public static ConfiguredFeature<?, ?> GALVITE_REDSTONE_ORE;


    public static void registerFeatures() {
        WOLT_TREE = register("wolt_tree", Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(MythBlocks.WOLT_LOG.get().defaultBlockState()), new SimpleBlockStateProvider(MythBlocks.WOLT_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build()));
        WOLT_TREE_BEES = register("wolt_tree_bees", Feature.TREE.configured(WOLT_TREE.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_0002))));
        STATIC_PLAINS_TREES = register("static_plains_trees", WOLT_TREE_BEES.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.07F, 1))));

        PATCH_CHARGED_DANDELIONS = register("patch_charged_dandelion", Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(MythBlocks.CHARGED_DANDELION.get().defaultBlockState().setValue(ChargedDandelionBlock.AGE, 5)), SimpleBlockPlacer.INSTANCE).tries(32).build()));

        GALVITE_GOLD_ORE = register("galvite_gold_ore", Feature.ORE.configured(new OreFeatureConfig(MythFillerBlockTypes.GALVITE, MythBlocks.GILDED_GALVITE.get().defaultBlockState(), 9)).range(32).squared().count(2));
        GALVITE_DIAMOND_ORE = register("galvite_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(MythFillerBlockTypes.GALVITE, MythBlocks.BEJEWELED_GALVITE.get().defaultBlockState(), 8)).range(16).squared());
        GALVITE_REDSTONE_ORE = register("galvite_redstone_ore", Feature.ORE.configured(new OreFeatureConfig(MythFillerBlockTypes.GALVITE, MythBlocks.POWERED_GALVITE.get().defaultBlockState(), 8)).range(16).squared().count(8));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Mythscapes.resourceLoc(name).toString(), configuredFeature);
    }
}
