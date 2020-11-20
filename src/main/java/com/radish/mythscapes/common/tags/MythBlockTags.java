package com.radish.mythscapes.common.tags;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

public class MythBlockTags {

    private static ITag.INamedTag<Block> forgeTag(String name) {
        return BlockTags.makeWrapperTag("forge:" + name);
    }

    private static ITag.INamedTag<Block> modTag(String name) {
        return BlockTags.makeWrapperTag(Mythscapes.resourceLoc(name).toString());
    }
}
