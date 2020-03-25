package com.mythscapes.register;

import com.mythscapes.common.biomes.BaseBiome;
import com.mythscapes.common.biomes.TestBiome;
import com.mythscapes.core.Mythscapes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class MythBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Mythscapes.MODID);
    public static List<BaseBiome> biome_list = new ArrayList<>();



    public static final RegistryObject<Biome> TEST_BIOME = BIOMES.register("test_biome", TestBiome::new);



    public static void addBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TEST_BIOME.get(), 20));
    }
}
