package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class MythBlockTags {

    public static void init() {}

    public static final Tags.IOptionalNamedTag<Block> SALT_BLOCKS = forgeTag("salt_blocks");

    private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
        return BlockTags.createOptional(new ResourceLocation("forge", name));
    }

    private static ITag.INamedTag<Block> modTag(String name) {
        return BlockTags.makeWrapperTag(Mythscapes.resourceLoc(name).toString());
    }
}
