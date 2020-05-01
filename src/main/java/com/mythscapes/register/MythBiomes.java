package com.mythscapes.register;

import com.mythscapes.common.biomes.BaseBiome;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.entity.EntityClassification.*;

public class MythBiomes {

    public static List<BaseBiome> biome_list = new ArrayList<>();
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Mythscapes.MODID);

    //public static final RegistryObject<Biome> STATIC_FOREST = BIOMES.register("test_biome", StaticForest::new);




    public static void addBiomes() {
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(STATIC_FOREST.get(), 10));

        //BiomeManager.addSpawnBiome(STATIC_FOREST.get());
    }

    public static void setBiomeEntitySpawns() {
        biome_list.forEach(BaseBiome::addEntitySpawns);
        // Pond Serpent
        addEntitySpawn(Biomes.LUKEWARM_OCEAN, WATER_CREATURE, MythEntities.POND_SERPENT, 3, 1, 2);
        addEntitySpawn(Biomes.WARM_OCEAN, WATER_CREATURE, MythEntities.POND_SERPENT, 3, 1, 2);

        // Fishbones
        addEntitySpawn(Biomes.FROZEN_OCEAN, MONSTER, MythEntities.FISHBONES, 90, 1, 4);
        addEntitySpawn(Biomes.DEEP_FROZEN_OCEAN, MONSTER, MythEntities.FISHBONES, 90, 1, 4);
        addEntitySpawn(Biomes.FROZEN_RIVER, MONSTER, MythEntities.FISHBONES, 90, 1, 4);
        addEntitySpawn(Biomes.ICE_SPIKES, MONSTER, MythEntities.FISHBONES, 90, 1, 4);

        // Removed entries
        removeEntitySpawn(Biomes.ICE_SPIKES, MONSTER, EntityType.ZOMBIE);
        removeEntitySpawn(Biomes.ICE_SPIKES, MONSTER, EntityType.ZOMBIE_VILLAGER);
    }

    private static void addEntitySpawn(Biome biome, EntityClassification entityClassification, EntityType<?> entityType, int weight, int minGroup, int maxGroup) {
        biome.getSpawns(entityClassification).add(new Biome.SpawnListEntry(entityType, weight, minGroup, maxGroup));
    }

    private static void addEntitySpawn(Biome biome, EntityClassification entityClassification, Supplier<? extends EntityType<?>> entityType, int weight, int minGroup, int maxGroup) {
        addEntitySpawn(biome, entityClassification, entityType.get(), weight, minGroup, maxGroup);
    }

    private static void removeEntitySpawn(Biome biome, EntityClassification entityClassification, EntityType<?> typeToRemove) {
        if (!biome.getSpawns(entityClassification).isEmpty()) {
            biome.getSpawns(entityClassification).removeIf(spawn -> spawn.entityType == typeToRemove);
        }
    }
}
