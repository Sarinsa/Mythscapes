package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import mekanism.api.functions.TriConsumer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class MythItemTags {

    public static final ITag.INamedTag<Item> WOLT_LOGS = modTag("wolt_logs");
    public static final ITag.INamedTag<Item> PRISMARINE = modTag("prismarine");

    public static final Tags.IOptionalNamedTag<Item> VERTICAL_SLAB = modTag("vertical_slab", "quark");
    public static final Tags.IOptionalNamedTag<Item> PLANKS_VERTICAL_SLAB = modTag("planks_vertical_slab", "quark");
    public static final Tags.IOptionalNamedTag<Item> LADDERS = modTag("ladders", "quark");


    private static ITag.INamedTag<Item> forgeTag(String name) {
        return ItemTags.makeWrapperTag("forge:" + name);
    }

    private static ITag.INamedTag<Item> modTag(String name) {
        return ItemTags.makeWrapperTag(Mythscapes.resourceLoc(name).toString());
    }

    private static Tags.IOptionalNamedTag<Item> modTag(String name, String modid) {
        return ItemTags.createOptional(new ResourceLocation(modid, name));
    }
}
