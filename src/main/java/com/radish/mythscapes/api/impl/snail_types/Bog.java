package com.radish.mythscapes.api.impl.snail_types;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;

import java.util.List;

public class Bog implements ISnailType {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/pygmy_snail/bog.png");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("bog");

    @Override
    public ResourceLocation getSnailTexture() {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getName() {
        return NAME;
    }

    @Override
    public List<ResourceLocation> getSpawnBiomes() {
        return ImmutableList.of(Biomes.STONE_SHORE.getLocation());
    }
}
