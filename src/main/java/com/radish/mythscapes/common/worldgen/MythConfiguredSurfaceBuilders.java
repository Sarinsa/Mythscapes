package com.radish.mythscapes.common.worldgen;

import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythSurfaceBuilders;
import com.radish.mythscapes.common.worldgen.surface_builder.DefaultBlockSurfaceBuilderConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import java.util.function.Supplier;

public class MythConfiguredSurfaceBuilders {

    public static ConfiguredSurfaceBuilder<DefaultBlockSurfaceBuilderConfig> STATIC;

    private static void registerSurfaceBuilders() {
        STATIC = register("static", create(MythSurfaceBuilders.DEFAULT_BLOCK_SURFACE_BUILDER, Configs.STATIC_CONFIG));
    }

    public static void register() {
        Configs.initConfigs();
        registerSurfaceBuilders();
    }

    private static <T extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<T> create(Supplier<SurfaceBuilder<T>> supplier, T config) {
        return new ConfiguredSurfaceBuilder<>(supplier.get(), config);
    }

    private static <T extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<T> register(String name, ConfiguredSurfaceBuilder<T> configuredSurfaceBuilder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, name + "_surface_builder", configuredSurfaceBuilder);
    }

    private static class Configs {

        private static DefaultBlockSurfaceBuilderConfig STATIC_CONFIG;

        private static void initConfigs() {
            STATIC_CONFIG = new DefaultBlockSurfaceBuilderConfig(Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState(), MythBlocks.GALVITE.get().defaultBlockState());
        }
    }
}
