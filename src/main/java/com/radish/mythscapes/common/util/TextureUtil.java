package com.radish.mythscapes.common.util;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.util.ResourceLocation;

public class TextureUtil {

    public static ResourceLocation entityTexture(String subPath) {
        return Mythscapes.resourceLoc("textures/entity/" + subPath + ".png");
    }
}
