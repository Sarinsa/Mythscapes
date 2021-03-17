package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class MythEntityTags {

    public static void init() {}

    public static final ITag.INamedTag<EntityType<?>> DIES_IN_SULFUR = mythTag("dies_in_sulfur");
    public static final ITag.INamedTag<EntityType<?>> ELECTRIC = mythTag("electric");
    public static final ITag.INamedTag<EntityType<?>> LION_PREY = mythTag("lion_prey");
    public static final ITag.INamedTag<EntityType<?>> SWOOSH_WHITELIST = mythTag("swoosh_whitelist");

    private static ITag.INamedTag<EntityType<?>> forgeTag(String name) {
        return EntityTypeTags.bind(new ResourceLocation("forge", name).toString());
    }

    private static ITag.INamedTag<EntityType<?>> modTag(String name, String modid) {
        return EntityTypeTags.bind(new ResourceLocation(modid, name).toString());
    }

    private static ITag.INamedTag<EntityType<?>> mythTag(String name) {
        return EntityTypeTags.bind(Mythscapes.resourceLoc(name).toString());
    }
}
