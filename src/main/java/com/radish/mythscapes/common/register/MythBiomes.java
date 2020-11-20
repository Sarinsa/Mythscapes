package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Mythscapes.MODID);

    //public static final RegistryObject<Biome> STATIC_FOREST = BIOMES.register("test_biome", StaticForest::new);



    public static void addBiomes() {
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(STATIC_FOREST.get(), 10));

        //BiomeManager.addSpawnBiome(STATIC_FOREST.get());
    }

    // TODO - Fix this broken ass mess
    /*
    public static void setBiomeEntitySpawns() {

        // Lion
        addEntitySpawn(Biomes.SAVANNA, CREATURE, MythEntities.LION, 7, 2, 4);
        addEntitySpawn(Biomes.SHATTERED_SAVANNA, CREATURE, MythEntities.LION, 7, 2, 4);

        // Pond Serpent
        addEntitySpawn(Biomes.LUKEWARM_OCEAN, WATER_CREATURE, MythEntities.POND_SERPENT, 3, 1, 2);
        addEntitySpawn(Biomes.WARM_OCEAN, WATER_CREATURE, MythEntities.POND_SERPENT, 3, 1, 2);

        // Fishbones
        addEntitySpawn(Biomes.FROZEN_OCEAN, MONSTER, MythEntities.FISHBONES, 95, 3, 5);
        addEntitySpawn(Biomes.DEEP_FROZEN_OCEAN, MONSTER, MythEntities.FISHBONES, 95, 3, 5);
        addEntitySpawn(Biomes.FROZEN_RIVER, MONSTER, MythEntities.FISHBONES, 95, 3, 5);
        addEntitySpawn(Biomes.ICE_SPIKES, MONSTER, MythEntities.FISHBONES, 100, 2, 5);

        // Pygmy snail
        addEntitySpawn(Biomes.JUNGLE, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.JUNGLE_EDGE, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.JUNGLE_HILLS, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.MODIFIED_JUNGLE, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.MODIFIED_JUNGLE_EDGE, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.BAMBOO_JUNGLE, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.BAMBOO_JUNGLE_HILLS, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.MUSHROOM_FIELDS, CREATURE, MythEntities.PYGMY_SNAIL, 60, 1, 3);
        addEntitySpawn(Biomes.FLOWER_FOREST, CREATURE, MythEntities.PYGMY_SNAIL, 90, 1, 3);
        addEntitySpawn(Biomes.BEACH, CREATURE, MythEntities.PYGMY_SNAIL, 60, 1, 3);
        addEntitySpawn(Biomes.SWAMP, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);
        addEntitySpawn(Biomes.SWAMP_HILLS, CREATURE, MythEntities.PYGMY_SNAIL, 70, 1, 3);

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

     */
}
