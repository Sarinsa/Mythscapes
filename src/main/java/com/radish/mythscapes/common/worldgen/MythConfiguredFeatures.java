package com.radish.mythscapes.common.worldgen;

import com.radish.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.radish.mythscapes.common.register.MythBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class MythConfiguredFeatures {

    public static ConfiguredFeature<?, ?> PATCH_CHARGED_DANDELIONS;
    public static ConfiguredFeature<?, ?> GALVITE_GOLD_ORE;
    public static ConfiguredFeature<?, ?> GALVITE_DIAMOND_ORE;
    public static ConfiguredFeature<?, ?> GALVITE_REDSTONE_ORE;


    public static void registerFeatures() {
        PATCH_CHARGED_DANDELIONS = register("patch_charged_dandelion", Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(MythBlocks.CHARGED_DANDELION.get().defaultBlockState().setValue(ChargedDandelionBlock.AGE, 5)), SimpleBlockPlacer.INSTANCE).tries(32).build()));
        GALVITE_GOLD_ORE = register("galvite_gold_ore", Feature.ORE.configured(new OreFeatureConfig(MythFillerBlockTypes.GALVITE, MythBlocks.GILDED_GALVITE.get().defaultBlockState(), 9)).range(32).squared().count(2));
        GALVITE_DIAMOND_ORE = register("galvite_diamond_ore", Feature.ORE.configured(new OreFeatureConfig(MythFillerBlockTypes.GALVITE, MythBlocks.BEJEWELED_GALVITE.get().defaultBlockState(), 8)).range(16).squared());
        GALVITE_REDSTONE_ORE = register("galvite_redstone_ore", Feature.ORE.configured(new OreFeatureConfig(MythFillerBlockTypes.GALVITE, MythBlocks.POWERED_GALVITE.get().defaultBlockState(), 8)).range(16).squared().count(8));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
