package com.mythscapes.misc;

import com.mythscapes.core.Mythscapes;
import net.minecraft.util.ResourceLocation;

public class ModResourceLocation extends ResourceLocation {

    protected ModResourceLocation(String path) {
        super(Mythscapes.MODID, path);
    }
}
