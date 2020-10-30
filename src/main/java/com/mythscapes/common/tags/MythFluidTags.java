package com.mythscapes.common.tags;

import com.mythscapes.core.Mythscapes;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;

public class MythFluidTags {

    public static final ITag.INamedTag<Fluid> CONDUCTIVE = modTag("conductive");
    public static final ITag.INamedTag<Fluid> SULFUR = modTag("sulfur");

    private static ITag.INamedTag<Fluid> forgeTag(String name) {
        return FluidTags.makeWrapperTag("forge:" + name);
    }

    private static ITag.INamedTag<Fluid> modTag(String name) {
        return FluidTags.makeWrapperTag(Mythscapes.resourceLoc(name).toString());
    }
}
