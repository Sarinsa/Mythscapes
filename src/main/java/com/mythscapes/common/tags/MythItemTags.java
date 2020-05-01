package com.mythscapes.common.tags;

import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;

public class MythItemTags {

    public static final Tag<Item> PRISMARINE = tag("prismarine");

    private static Tag<Item> tag(String name) {
        return new ItemTags.Wrapper(new ModResourceLocation(name));
    }
}
