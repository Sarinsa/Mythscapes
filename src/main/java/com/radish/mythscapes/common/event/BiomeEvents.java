package com.radish.mythscapes.common.event;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static com.radish.mythscapes.common.misc.Util.areBiomesEqual;
import static com.radish.mythscapes.common.misc.Util.hasDictType;
import static com.radish.mythscapes.common.register.MythEntities.*;

public class BiomeEvents {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void changeBiomeSpawns(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (biomeName != null) {
            if (hasDictType(biomeName, BiomeDictionary.Type.OVERWORLD)) {
                // Pygmy snail
                boolean exit = false;
                for (List<ISnailType> list : Mythscapes.getInstance().getSnailTypeRegister().getSpawnBiomes().values()) {
                    for (ISnailType snailType : list) {
                        if (snailType.getSpawnBiomes().contains(biomeName)) {
                            addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 72, 1, 4);
                            exit = true;
                            break;
                        }
                    }
                    if (exit)
                        break;
                }

                if (areBiomesEqual(biomeName, Biomes.SAVANNA) || areBiomesEqual(biomeName, Biomes.SHATTERED_SAVANNA)) {
                    addBiomeSpawn(event, LION.get(), EntityClassification.CREATURE, 9, 2, 4);
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
                else if (areBiomesEqual(biomeName, Biomes.FOREST) || areBiomesEqual(biomeName, Biomes.BIRCH_FOREST)) {
                    addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 12, 2, 5);
                }
                else if (areBiomesEqual(biomeName, Biomes.TAIGA) || areBiomesEqual(biomeName, Biomes.TAIGA_HILLS) || areBiomesEqual(biomeName, Biomes.TAIGA_MOUNTAINS)) {
                    addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 12, 2, 5);
                }
                else if (areBiomesEqual(biomeName, Biomes.SNOWY_TUNDRA)) {
                    addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 12, 2, 5);
                }
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