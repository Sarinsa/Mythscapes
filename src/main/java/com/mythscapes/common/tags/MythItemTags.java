package com.mythscapes.common.tags;

import com.mythscapes.core.Mythscapes;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class MythItemTags {

    public static final ITag.INamedTag<Item> PRISMARINE = modTag("prismarine");

    private static ITag.INamedTag<Item> forgeTag(String name) {
        return ItemTags.makeWrapperTag("forge:" + name);
    }

    private static ITag.INamedTag<Item> modTag(String name) {
        return ItemTags.makeWrapperTag(Mythscapes.resourceLoc(name).toString());
    }
}
