package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;

public class MythEntityTags {

    public static final ITag.INamedTag<EntityType<?>> DIES_IN_SULFUR = modTag("dies_in_sulfur");
    public static final ITag.INamedTag<EntityType<?>> ELECTRIC = modTag("electric");
    public static final ITag.INamedTag<EntityType<?>> LION_PREY = modTag("lion_prey");

    private static ITag.INamedTag<EntityType<?>> forgeTag(String name) {
        return EntityTypeTags.getTagById("forge:" + name);
    }

    private static ITag.INamedTag<EntityType<?>> modTag(String name) {
        return EntityTypeTags.getTagById(Mythscapes.resourceLoc(name).toString());
    }
}
