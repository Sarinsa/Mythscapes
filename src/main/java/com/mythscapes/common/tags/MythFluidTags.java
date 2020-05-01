package com.mythscapes.common.tags;

import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;

public class MythFluidTags {

    public static final Tag<Fluid> CONDUCTIVE = tag("conductive");
    public static final Tag<Fluid> SULFUR = tag("sulfur");

    private static Tag<Fluid> tag(String name) {
        return new FluidTags.Wrapper(new ModResourceLocation(name));
    }
}
