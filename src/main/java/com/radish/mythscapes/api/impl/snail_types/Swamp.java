package com.radish.mythscapes.api.impl.snail_types;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;

import java.util.List;

public class Swamp implements ISnailType {

    private static final ResourceLocation TEXTURE = TextureUtil.entityTexture("pygmy_snail/swamp");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("swamp");

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
        return ImmutableList.of(
                Biomes.SWAMP.getLocation(),
                Biomes.SWAMP_HILLS.getLocation()
        );
    }
}
