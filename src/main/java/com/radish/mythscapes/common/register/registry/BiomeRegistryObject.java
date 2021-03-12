package com.radish.mythscapes.common.register.registry;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

public class BiomeRegistryObject {

    private final RegistryObject<Biome> registryObject;
    private final int defaultBiomeWeight;

    public BiomeRegistryObject(RegistryObject<Biome> registryObject, int defaultBiomeWeight) {
        this.registryObject = registryObject;
        this.defaultBiomeWeight = defaultBiomeWeight;
    }

    public RegistryObject<Biome> getRegistryObject() {
        return this.registryObject;
    }

    public Biome get() {
        return this.registryObject.get();
    }

    public int getDefaultBiomeWeight() {
        return this.defaultBiomeWeight;
    }

    public RegistryKey<Biome> getRegistryKey() {
        return ((ForgeRegistry<Biome>) ForgeRegistries.BIOMES).getKey(((ForgeRegistry<Biome>)ForgeRegistries.BIOMES).getID(this.get()));
    }
}
