package com.mythscapes.misc;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;

public class MythTags {

    public static Tag<Fluid> CONDUCTIVE = FluidTags.getCollection().getOrCreate(new ModResourceLocation("conductive"));
}
