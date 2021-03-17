package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.config.MythConfig;
import com.radish.mythscapes.common.register.registry.BiomeDeferredRegister;
import com.radish.mythscapes.common.register.registry.BiomeRegistryObject;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.function.Supplier;

public class MythBiomes {

    public static final BiomeDeferredRegister BIOMES = new BiomeDeferredRegister();

    public static final BiomeRegistryObject STATIC_FIELDS = BIOMES.register("static_fields", MythBiomes::createStaticForest, 2);


    public static void registerBiomeInfo() {
        registerBiomes();
        addBiomesToDictionary();
    }

    private static void registerBiomes() {
        registerBiome(STATIC_FIELDS, BiomeManager.BiomeType.COOL);
    }

    private static void addBiomesToDictionary() {
        addBiomeToDictionary(STATIC_FIELDS, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.OVERWORLD);
    }

    //
    //  BIOMES
    //
    private static Biome createStaticForest() {
        return new Biome.Builder()
                .biomeCategory(Biome.Category.PLAINS)
                .depth(0.125F)
                .scale(0.05F)
                .downfall(1.0F)
                .temperature(0.6F)
                .precipitation(Biome.RainType.RAIN)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .specialEffects(new BiomeAmbience.Builder()
                        .fogColor(0xCEFFEF)
                        .waterColor(0x26D0FF)
                        .waterFogColor(0x26D0FF)
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .skyColor(0x7FFFD2)
                        .grassColorOverride(0x00D3B6)
                        .build())
                .mobSpawnSettings(new MobSpawnInfo.Builder().build())
                .generationSettings(new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS).build())
                .build();
        }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float modifier = temperature / 3.0F;
        modifier = MathHelper.clamp(modifier, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - modifier * 0.05F, 0.5F + modifier * 0.1F, 1.0F);
    }

    private static void registerBiome(BiomeRegistryObject biomeRegistryObject, BiomeManager.BiomeType biomeType) {
        int weight = MythConfig.COMMON.getBiomeWeight(biomeRegistryObject);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biomeRegistryObject.getRegistryKey(), weight));
    }

    private static void addBiomeToDictionary(BiomeRegistryObject biomeRegistryObject, BiomeDictionary.Type... dictionaryTypes) {
        BiomeDictionary.addTypes(biomeRegistryObject.getRegistryKey(), dictionaryTypes);
    }
}
