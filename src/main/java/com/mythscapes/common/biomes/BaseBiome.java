package com.mythscapes.common.biomes;

import com.mythscapes.register.MythBiomes;
import net.minecraft.world.biome.Biome;

public abstract class BaseBiome extends Biome {

    public BaseBiome(Builder builder) {
        super(builder);
        MythBiomes.biome_list.add(this);
    }

    // When extending this class, entity spawns
    // should be added in this method which is
    // called during FMLServerAboutToStartEvent.
    // (Biomes are registered before entities, so
    // any attempts to add entity spawns in the
    // biome constructor will give a NullPointer)
    public abstract void addEntitySpawns();
}
