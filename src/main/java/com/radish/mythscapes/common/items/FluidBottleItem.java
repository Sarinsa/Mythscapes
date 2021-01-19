package com.radish.mythscapes.common.items;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

public class FluidBottleItem extends Item {

    private final ITag.INamedTag<Fluid> fluidTag;

    public FluidBottleItem(ITag.INamedTag<Fluid> fluidTag, Properties properties) {
        super(properties);
        this.fluidTag = fluidTag;
    }
}
