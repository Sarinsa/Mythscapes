package com.mythscapes.common.tags;

import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.Tag;

public class MythEntityTags {

    public static Tag<EntityType<?>> DIES_IN_SULFUR = EntityTypeTags.getCollection().getOrCreate(new ModResourceLocation("dies_in_sulfur"));
    public static Tag<EntityType<?>> ELECTRIC = EntityTypeTags.getCollection().getOrCreate(new ModResourceLocation("electric"));
}
