package com.radish.mythscapes.common.worldgen;

import com.radish.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.radish.mythscapes.common.register.MythBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;

public class MythConfiguredFeatures {

    public static void register() {
        FeatureConfigs.registerConfigs();
        registerFeatures();
    }

    public static ConfiguredFeature<?, ?> PATCH_CHARGED_DANDELIONS;

    private static void registerFeatures() {
        PATCH_CHARGED_DANDELIONS = register("patch_charged_dandelion", Feature.RANDOM_PATCH.configured(FeatureConfigs.PATCH_CHARGED_DANDELIONS_CONFIG));
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }

    public static class FeatureConfigs {
        public static BlockClusterFeatureConfig PATCH_CHARGED_DANDELIONS_CONFIG;

        private static void registerConfigs() {
            PATCH_CHARGED_DANDELIONS_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(MythBlocks.CHARGED_DANDELION.get().defaultBlockState().setValue(ChargedDandelionBlock.AGE, 5)), SimpleBlockPlacer.INSTANCE).tries(32).build();
        }
    }
}
