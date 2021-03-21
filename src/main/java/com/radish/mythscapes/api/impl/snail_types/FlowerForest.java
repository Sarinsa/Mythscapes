package com.radish.mythscapes.api.impl.snail_types;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.util.ResourceLocation;

public class FlowerForest implements ISnailType {

    private static final ResourceLocation TEXTURE = TextureUtil.entityTexture("pygmy_snail/flower_forest");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("flower_forest");

    @Override
    public ResourceLocation getSnailTexture() {
        return TEXTURE;
    }

    @Override
    public GrowthRenderType getGrowthRenderType() {
        return GrowthRenderType.BOTH;
    }

    @Override
    public ResourceLocation getName() {
        return NAME;
    }
}
