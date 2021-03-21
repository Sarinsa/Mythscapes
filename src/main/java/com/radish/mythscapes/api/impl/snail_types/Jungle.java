package com.radish.mythscapes.api.impl.snail_types;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.util.ResourceLocation;

public class Jungle implements ISnailType {

    private static final ResourceLocation TEXTURE = TextureUtil.entityTexture("pygmy_snail/jungle");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("jungle");

    @Override
    public ResourceLocation getSnailTexture() {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getName() {
        return NAME;
    }
}
