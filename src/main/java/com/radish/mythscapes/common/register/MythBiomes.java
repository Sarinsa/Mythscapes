package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Mythscapes.MODID);


    public static void addBiomes() {
        // NOOP
    }
}
