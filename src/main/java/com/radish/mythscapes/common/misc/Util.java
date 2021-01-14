package com.radish.mythscapes.common.misc;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

public class Util {

    public static boolean areBiomesEqual(Biome biome, RegistryKey<Biome> registryKey) {
        if (biome.getRegistryName() == null) {
            return false;
        }
        return biome.getRegistryName().equals(registryKey.getLocation());
    }

    public static boolean areBiomesEqual(ResourceLocation biomeName, RegistryKey<Biome> registryKey) {
        return biomeName.equals(registryKey.getLocation());
    }

    public static boolean hasDictType(Biome biome, BiomeDictionary.Type type) {
        if (biome.getRegistryName() == null)
            return false;
        return BiomeDictionary.hasType(RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, biome.getRegistryName()), type);
    }

    public static boolean hasDictType(ResourceLocation biomeName, BiomeDictionary.Type type) {
        return BiomeDictionary.hasType(RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, biomeName), type);
    }
}
