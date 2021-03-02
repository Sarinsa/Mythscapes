package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;

public class MythFluidTags {

    public static final ITag.INamedTag<Fluid> CONDUCTIVE = mythTag("conductive");
    public static final ITag.INamedTag<Fluid> SULFUR = mythTag("sulfur");
    public static final ITag.INamedTag<Fluid> SWIMMABLE = mythTag("swimmable");

    private static ITag.INamedTag<Fluid> forgeTag(String name) {
        return FluidTags.makeWrapperTag("forge:" + name);
    }

    private static ITag.INamedTag<Fluid> mythTag(String name) {
        return FluidTags.makeWrapperTag(Mythscapes.resourceLoc(name).toString());
    }
}
