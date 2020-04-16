package com.mythscapes.common.tags;

import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;

public class MythFluidTags {

    public static final Tag<Fluid> CONDUCTIVE = FluidTags.getCollection().getOrCreate(new ModResourceLocation("conductive"));
    public static final Tag<Fluid> SULFUR = FluidTags.getCollection().getOrCreate(new ModResourceLocation("sulfur"));
}
