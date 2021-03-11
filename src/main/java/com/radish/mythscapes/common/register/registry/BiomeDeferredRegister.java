package com.radish.mythscapes.common.register.registry;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.function.Supplier;

public class BiomeDeferredRegister extends WrapperDeferredRegister<Biome> {

    private final ArrayList<BiomeRegistryObject> registryObjects;

    public BiomeDeferredRegister() {
        super(ForgeRegistries.BIOMES);
        this.registryObjects = new ArrayList<>();
    }

    public BiomeRegistryObject register(String registryName, Supplier<Biome> biomeSupplier, int defaultBiomeWeight) {
        RegistryObject<Biome> registryObject = this.getInternal().register(registryName, biomeSupplier);
        BiomeRegistryObject biomeRegistryObject = new BiomeRegistryObject(registryObject, defaultBiomeWeight);
        this.registryObjects.add(biomeRegistryObject);
        return biomeRegistryObject;
    }

    public Iterable<BiomeRegistryObject> getRegistryObjects() {
        return this.registryObjects;
    }
}
