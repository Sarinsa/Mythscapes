package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.ISnailType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;

public final class SnailSpawnEntry extends WeightedRandom.Item {

    private final ResourceLocation biomeName;
    private final ISnailType snailType;

    public SnailSpawnEntry(ResourceLocation biomeName, ISnailType snailType, int spawnChance) {
        super(spawnChance);
        this.biomeName = biomeName;
        this.snailType = snailType;
    }

    public ISnailType getSnailType() {
        return this.snailType;
    }

    public ResourceLocation getBiomeLocation() {
        return this.biomeName;
    }
}
