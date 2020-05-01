package com.mythscapes.common.tags;

import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;

public class MythEntityTags {

    public static final Tag<EntityType<?>> DIES_IN_SULFUR = tag("dies_in_sulfur");
    public static final Tag<EntityType<?>> ELECTRIC = tag("electric");

    private static Tag<EntityType<?>> tag(String name) {
        return new EntityTypeTags.Wrapper(new ModResourceLocation(name));
    }
}
