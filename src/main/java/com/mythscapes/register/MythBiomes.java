package com.mythscapes.register;

import com.mythscapes.common.biomes.BaseBiome;
import com.mythscapes.core.Mythscapes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class MythBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Mythscapes.MODID);
    public static List<BaseBiome> biome_list = new ArrayList<>();
}
