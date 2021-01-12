package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.api.impl.snail_types.*;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class SnailTypeRegister {

    private static final LinkedHashMap<ResourceLocation, ISnailType> SNAIL_TYPES = new LinkedHashMap<>();
    private static final HashMap<ResourceLocation, List<ISnailType>> SNAIL_SPAWNS = new HashMap<>();
    private static final Random random = new Random();
    private boolean validated = true;

    // Types
    public static final ISnailType JUNGLE = new Jungle();
    public static final ISnailType BEJEWELED = new Bejeweled();
    public static final ISnailType FLOWER_FOREST = new FlowerForest();
    public static final ISnailType BOG = new Bog();
    public static final ISnailType MUSHROOM = new Mushroom();
    public static final ISnailType ROOFED_FOREST = new RoofedForest();
    public static final ISnailType SWAMP = new Swamp();

    public LinkedHashMap<ResourceLocation, ISnailType> getRegistry() {
        return SNAIL_TYPES;
    }

    public Map<ResourceLocation, List<ISnailType>> getSpawnBiomes() {
        return SNAIL_SPAWNS;
    }

    /**
     * Register our own snail types here
     */
    public void registerInternal() {
        register(JUNGLE);
        register(BEJEWELED);
        register(FLOWER_FOREST);
        register(BOG);
        register(MUSHROOM);
        register(ROOFED_FOREST);
        register(SWAMP);
    }

    public void register(ISnailType iSnailType) {
        if (acceptingNew())
            SNAIL_TYPES.putIfAbsent(iSnailType.getName(), iSnailType);
        else {
            Mythscapes.LOGGER.warn("Tried to register snail type after the register had been invalidated.");
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

    public static ISnailType getRandom() {
        return SNAIL_TYPES.get(SNAIL_TYPES.keySet().toArray(new ResourceLocation[0])[random.nextInt(SNAIL_TYPES.size())]);
    }

    public static ISnailType getFromName(ResourceLocation name) {
        return SNAIL_TYPES.getOrDefault(name, SNAIL_TYPES.get(Mythscapes.resourceLoc("jungle")));
    }

    public static ISnailType getFromName(String name) {
        return getFromName(new ResourceLocation(name));
    }

    @Nullable
    public static ISnailType getFromNameOrNull(String name) {
        if (name == null || name.isEmpty())
            return null;
        return SNAIL_TYPES.getOrDefault(new ResourceLocation(name), null);
    }

    public void setupSnailSpawns() {
        this.getRegistry().values().forEach(snailType -> {
            snailType.getSpawnBiomes().forEach(resourceLoc -> {
                if (!SNAIL_SPAWNS.containsKey(resourceLoc)) {
                    SNAIL_SPAWNS.put(resourceLoc, new ArrayList<>());
                }
                SNAIL_SPAWNS.get(resourceLoc).add(snailType);
            });
        });
    }
}
