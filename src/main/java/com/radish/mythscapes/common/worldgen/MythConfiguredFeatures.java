package com.radish.mythscapes.common.worldgen;

import com.radish.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.radish.mythscapes.common.register.MythBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class MythConfiguredFeatures {

    public static void register() {
        FeatureConfigs.registerConfigs();
        registerFeatures();
    }

    public static ConfiguredFeature<?, ?> PATCH_CHARGED_DANDELIONS;

    private static void registerFeatures() {
        PATCH_CHARGED_DANDELIONS = register("patch_charged_dandelion", Feature.RANDOM_PATCH.withConfiguration(FeatureConfigs.PATCH_CHARGED_DANDELIONS_CONFIG));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }

    public static class FeatureConfigs {
        public static BlockClusterFeatureConfig PATCH_CHARGED_DANDELIONS_CONFIG;

        private static void registerConfigs() {
            PATCH_CHARGED_DANDELIONS_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(MythBlocks.CHARGED_DANDELION.get().getDefaultState().with(ChargedDandelionBlock.AGE, 5)), SimpleBlockPlacer.PLACER).tries(32).build();
        }
    }
}
