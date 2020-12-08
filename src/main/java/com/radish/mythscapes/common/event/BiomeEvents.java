package com.radish.mythscapes.common.event;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.radish.mythscapes.common.misc.Util.areBiomesEqual;
import static com.radish.mythscapes.common.register.MythEntities.*;

public class BiomeEvents {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void changeBiomeSpawns(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (biomeName != null) {

            if (areBiomesEqual(biomeName, Biomes.SAVANNA) || areBiomesEqual(biomeName, Biomes.SHATTERED_SAVANNA)) {
                addBiomeSpawn(event, LION.get(), EntityClassification.CREATURE, 7, 2, 4);
            }
            else if (areBiomesEqual(biomeName, Biomes.WARM_OCEAN) || areBiomesEqual(biomeName, Biomes.LUKEWARM_OCEAN)) {
                addBiomeSpawn(event, POND_SERPENT.get(), EntityClassification.WATER_CREATURE, 3, 1, 2);
            }
            else if (areBiomesEqual(biomeName, Biomes.OCEAN) || areBiomesEqual(biomeName, Biomes.DEEP_OCEAN)) {
                addBiomeSpawn(event, POND_SERPENT.get(), EntityClassification.WATER_CREATURE, 3, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.FROZEN_OCEAN) || areBiomesEqual(biomeName, Biomes.FROZEN_RIVER) || areBiomesEqual(biomeName, Biomes.DEEP_FROZEN_OCEAN)) {
                addBiomeSpawn(event, FISHBONES.get(), EntityClassification.MONSTER, 95, 3, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.ICE_SPIKES)) {
                addBiomeSpawn(event, FISHBONES.get(), EntityClassification.MONSTER, 95, 3, 5);
                removeBiomeSpawn(event, EntityType.ZOMBIE, EntityClassification.MONSTER);
                removeBiomeSpawn(event, EntityType.ZOMBIE_VILLAGER, EntityClassification.MONSTER);
            }
            else if (areBiomesEqual(biomeName, Biomes.JUNGLE) || areBiomesEqual(biomeName, Biomes.JUNGLE_EDGE) || areBiomesEqual(biomeName, Biomes.JUNGLE_HILLS) || areBiomesEqual(biomeName, Biomes.MODIFIED_JUNGLE) || areBiomesEqual(biomeName, Biomes.MODIFIED_JUNGLE_EDGE)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.BAMBOO_JUNGLE) || areBiomesEqual(biomeName, Biomes.BAMBOO_JUNGLE_HILLS)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.MUSHROOM_FIELDS)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.FLOWER_FOREST)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 90, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.FOREST) || areBiomesEqual(biomeName, Biomes.BIRCH_FOREST)) {
                addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 90, 2, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.TAIGA) || areBiomesEqual(biomeName, Biomes.TAIGA_HILLS) || areBiomesEqual(biomeName, Biomes.TAIGA_MOUNTAINS)) {
                addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 90, 2, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.SNOWY_TUNDRA)) {
                addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 90, 2, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.BEACH)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 55, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.SWAMP) || areBiomesEqual(biomeName, Biomes.SWAMP_HILLS)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
        }
    }

    private static void addBiomeSpawn(BiomeLoadingEvent event, EntityType<?> entityType, EntityClassification entityClassification, int weight, int minCount, int maxCount) {
        if (minCount > maxCount) {
            throw new IllegalArgumentException("Tried to register biome entity spawn with min count greater than max count. This can't be right?");
        }
        event.getSpawns().getSpawner(entityClassification).add(new MobSpawnInfo.Spawners(entityType, weight, minCount, maxCount));
    }

    private static void removeBiomeSpawn(BiomeLoadingEvent event, EntityType<?> entityType, EntityClassification entityClassification) {
        event.getSpawns().getSpawner(entityClassification).removeIf((spawners) -> spawners.type == entityType);
    }
}
