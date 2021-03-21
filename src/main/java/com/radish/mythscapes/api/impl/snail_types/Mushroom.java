package com.radish.mythscapes.api.impl.snail_types;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.util.ResourceLocation;

public class Mushroom implements ISnailType {

    private static final ResourceLocation TEXTURE = TextureUtil.entityTexture("pygmy_snail/mushroom");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("mushroom");

    @Override
    public ResourceLocation getSnailTexture() {
        return TEXTURE;
    }

    @Override
    public GrowthRenderType getGrowthRenderType() {
        return GrowthRenderType.BACK;
    }

    @Override
    public ResourceLocation getName() {
        return NAME;
    }
}
