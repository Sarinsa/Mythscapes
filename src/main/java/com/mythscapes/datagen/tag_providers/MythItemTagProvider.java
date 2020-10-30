package com.mythscapes.datagen.tag_providers;

import com.mythscapes.common.tags.MythItemTags;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythItems;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythItemTagProvider extends ItemTagsProvider {

    public MythItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        // All prismarine
        this.getOrCreateBuilder(MythItemTags.PRISMARINE).add(
                Items.PRISMARINE_BRICKS,
                Items.PRISMARINE_BRICK_SLAB,
                Items.PRISMARINE_BRICK_STAIRS,
                Items.PRISMARINE_CRYSTALS,
                Items.PRISMARINE_SHARD,
                Items.PRISMARINE_SLAB,
                Items.PRISMARINE_STAIRS,
                Items.PRISMARINE_WALL,
                Items.DARK_PRISMARINE,
                Items.DARK_PRISMARINE_SLAB,
                Items.DARK_PRISMARINE_STAIRS
        )
                .addOptionalTag(Tags.Items.DUSTS_PRISMARINE.getName())
                .addOptionalTag(Tags.Items.GEMS_PRISMARINE.getName());

        this.getOrCreateBuilder(ItemTags.BOATS).add(
                MythItems.WOLT_BOAT.get()
                //MythItems.VIRIDIAN_BOAT.get()
        );
    }
}
