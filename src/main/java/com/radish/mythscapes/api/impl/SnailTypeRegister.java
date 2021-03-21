package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.impl.snail_types.*;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public final class SnailTypeRegister {

    public static final SnailTypeRegister INSTANCE = new SnailTypeRegister();

    private final LinkedHashMap<ResourceLocation, ISnailType> types = new LinkedHashMap<>();
    private final List<SnailSpawnEntry> spawnEntries = new ArrayList<>();
    private final List<ResourceLocation> spawnBiomes = new ArrayList<>();

    private final Random random = new Random();
    private boolean validated = true;

    // Types
    public static final ISnailType JUNGLE = new Jungle();
    public static final ISnailType BEJEWELED = new Bejeweled();
    public static final ISnailType FLOWER_FOREST = new FlowerForest();
    public static final ISnailType BOG = new Bog();
    public static final ISnailType MUSHROOM = new Mushroom();
    public static final ISnailType ROOFED_FOREST = new RoofedForest();
    public static final ISnailType SWAMP = new Swamp();

    private SnailTypeRegister() {
    }


    /**
     * Registering our own snail types here
     */
    public void registerInternal() {
        register(JUNGLE);
        register(BEJEWELED);
        register(FLOWER_FOREST);
        register(BOG);
        register(MUSHROOM);
        register(ROOFED_FOREST);
        register(SWAMP);

        registerSpawnEntry(JUNGLE, Biomes.JUNGLE.location(), 100);
        registerSpawnEntry(JUNGLE, Biomes.JUNGLE_EDGE.location(), 100);
        registerSpawnEntry(JUNGLE, Biomes.JUNGLE_HILLS.location(), 100);
        registerSpawnEntry(JUNGLE, Biomes.MODIFIED_JUNGLE.location(), 100);
        registerSpawnEntry(JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE.location(), 100);
        registerSpawnEntry(BEJEWELED, Biomes.BEACH.location(), 60);
        registerSpawnEntry(FLOWER_FOREST, Biomes.FLOWER_FOREST.location(), 100);
        registerSpawnEntry(BOG, Biomes.STONE_SHORE.location(), 100);
        registerSpawnEntry(MUSHROOM, Biomes.MUSHROOM_FIELDS.location(), 100);
        registerSpawnEntry(MUSHROOM, Biomes.MUSHROOM_FIELD_SHORE.location(), 100);
        registerSpawnEntry(ROOFED_FOREST, Biomes.FOREST.location(), 100);
        registerSpawnEntry(SWAMP, Biomes.SWAMP.location(), 100);
        registerSpawnEntry(SWAMP, Biomes.SWAMP_HILLS.location(), 100);
    }

    public LinkedHashMap<ResourceLocation, ISnailType> getRegistry() {
        return this.types;
    }

    public List<SnailSpawnEntry> getSpawnEntries() {
        return this.spawnEntries;
    }

    public boolean isSpawnBiome(ResourceLocation biomeName) {
        for (ResourceLocation resourceLocation : this.spawnBiomes) {
            if (resourceLocation.equals(biomeName))
                return true;
        }
        return false;
    }

    public void register(ISnailType iSnailType) {
        if (acceptingNew())
            this.types.putIfAbsent(iSnailType.getName(), iSnailType);
        else {
            Mythscapes.LOGGER.warn("Tried to register snail type after the register had been invalidated.");
        }
    }

    public void registerSpawnEntry(ISnailType snailType, ResourceLocation biomeName, int weight) {
        if (acceptingNew()) {
            this.spawnEntries.add(new SnailSpawnEntry(biomeName, snailType, weight));
            boolean addBiome = true;

            for (ResourceLocation resourceLocation : this.spawnBiomes) {
                if (resourceLocation.equals(biomeName)) {
                    addBiome = false;
                    break;
                }
            }
            if (addBiome)
                this.spawnBiomes.add(biomeName);
        }
        else {
            Mythscapes.LOGGER.warn("Tried to register a snail spawn entry after the register had been invalidated.");
        }
    }

    public void invalidate() {
        this.validated = false;
    }

    public boolean acceptingNew() {
        return this.validated;
    }

    public static String getTranslationKey(ISnailType snailType) {
        return Mythscapes.MODID + ".snail_type." + snailType.getName().getNamespace() + "." + snailType.getName().getPath();
    }

    public ISnailType getRandom() {
        return getRegistry().get(this.types.keySet().toArray(new ResourceLocation[0])[random.nextInt(this.types.size())]);
    }

    public ISnailType getFromName(ResourceLocation name) {
        return getRegistry().getOrDefault(name, JUNGLE);
    }

    public ISnailType getFromName(String name) {
        return getFromName(new ResourceLocation(name));
    }

    public ISnailType getWeightedForBiome(Biome biome) {
        ResourceLocation biomeName = biome.getRegistryName();

        if (biomeName == null || !this.isSpawnBiome(biomeName))
            return this.getRandom();

        List<SnailSpawnEntry> spawnEntries = this.spawnEntries.stream().filter((spawnEntry) -> spawnEntry.getBiomeLocation().equals(biomeName)).collect(Collectors.toList());
        return WeightedRandom.getRandomItem(this.random, spawnEntries).getSnailType();
    }

    @Nullable
    public ISnailType getFromNameOrNull(String name) {
        if (name == null || name.isEmpty())
            return null;
        return this.types.getOrDefault(new ResourceLocation(name), null);
    }
}
