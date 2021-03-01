package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.core.config.MythConfig;
import com.radish.mythscapes.common.worldgen.MythConfiguredFeatures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.function.Supplier;

public class MythBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Mythscapes.MODID);

    public static final RegistryObject<Biome> STATIC_FOREST = BIOMES.register("static_forest", MythBiomes::createStaticForest);


    public static void registerBiomeInfo() {
        registerBiomes();
        addBiomeToDictionary();
    }

    private static void registerBiomes() {
        MythConfig.Common config = MythConfig.COMMON;

        registerBiome(STATIC_FOREST, BiomeManager.BiomeType.COOL, config.getBiomeWeight(STATIC_FOREST));
    }

    private static void addBiomeToDictionary() {
        BiomeDictionary.addTypes(getRegistryKey(STATIC_FOREST), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.OVERWORLD);
    }

    private static void registerBiome(Supplier<Biome> biomeSupplier, BiomeManager.BiomeType biomeType, int weight) {
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(getRegistryKey(biomeSupplier), weight));
    }

    private static RegistryKey<Biome> getRegistryKey(Supplier<Biome> biomeSupplier) {
        return ((ForgeRegistry<Biome>)ForgeRegistries.BIOMES).getKey(((ForgeRegistry<Biome>)ForgeRegistries.BIOMES).getID(biomeSupplier.get()));
    }


    //
    //  BIOMES
    //
    private static Biome createStaticForest() {
        return new Biome.Builder()
                .category(Biome.Category.PLAINS)
                .depth(0.125F)
                .scale(0.05F)
                .downfall(1.0F)
                .temperature(0.6F)
                .precipitation(Biome.RainType.RAIN)
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .setFogColor(0xCEFFEF)
                        .setWaterColor(0x26D0FF)
                        .setWaterFogColor(0x26D0FF)
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .withSkyColor(0x7FFFD2)
                        .withGrassColor(0x00D3B6)
                        .build())
                .withMobSpawnSettings(new MobSpawnInfo.Builder().copy())
                .withGenerationSettings(new BiomeGenerationSettings.Builder().withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j).build())
                .build();
        }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float modifier = temperature / 3.0F;
        modifier = MathHelper.clamp(modifier, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - modifier * 0.05F, 0.5F + modifier * 0.1F, 1.0F);
    }
}
