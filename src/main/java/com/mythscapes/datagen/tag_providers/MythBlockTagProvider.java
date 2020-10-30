package com.mythscapes.datagen.tag_providers;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythBlockTagProvider extends BlockTagsProvider {

    public MythBlockTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(BlockTags.SLABS).add(
                MythBlocks.GALVITE_SLAB.get(),
                MythBlocks.POLISHED_GALVITE_SLAB.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.GILDED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.POWERED_GALVITE_BRICK_SLAB.get(),
                MythBlocks.TROLLSTONE_SLAB.get(),
                MythBlocks.POLISHED_TROLLSTONE_SLAB.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_SLABS).add(
                MythBlocks.WOLT_SLAB.get()
                //MythBlocks.VIRIDIAN_SLAB.get()
        );

        this.getOrCreateBuilder(BlockTags.STAIRS).add(
                MythBlocks.GALVITE_STAIRS.get(),
                MythBlocks.POLISHED_GALVITE_STAIRS.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.GILDED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.POWERED_GALVITE_BRICK_STAIRS.get(),
                MythBlocks.TROLLSTONE_STAIRS.get(),
                MythBlocks.POLISHED_TROLLSTONE_STAIRS.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_STAIRS).add(
                MythBlocks.WOLT_STAIRS.get()
                //MythBlocks.VIRIDIAN_STAIRS.get()
        );

        this.getOrCreateBuilder(BlockTags.WALLS).add(
                MythBlocks.GALVITE_WALL.get(),
                MythBlocks.POLISHED_GALVITE_WALL.get(),
                MythBlocks.POLISHED_GALVITE_BRICK_WALL.get(),
                MythBlocks.GILDED_GALVITE_BRICK_WALL.get(),
                MythBlocks.BEJEWELED_GALVITE_BRICK_WALL.get(),
                MythBlocks.POWERED_GALVITE_BRICK_WALL.get(),
                MythBlocks.TROLLSTONE_WALL.get(),
                MythBlocks.POLISHED_TROLLSTONE_WALL.get(),
                MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL.get()
        );

        this.getOrCreateBuilder(BlockTags.BUTTONS).add(
                MythBlocks.POLISHED_GALVITE_BUTTON.get(),
                MythBlocks.POLISHED_TROLLSTONE_BUTTON.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_BUTTONS).add(
                MythBlocks.WOLT_BUTTON.get()
                //MythBlocks.VIRIDIAN_BUTTON.get()
        );

        this.getOrCreateBuilder(BlockTags.WOODEN_FENCES).add(
                MythBlocks.WOLT_FENCE.get()
                //MythBlocks.VIRIDIAN_FENCE.get()
        );

        this.getOrCreateBuilder(Tags.Blocks.FENCE_GATES_WOODEN).add(
                MythBlocks.WOLT_FENCE_GATE.get()
                //MythBlocks.VIRIDIAN_FENCE_GATE.get()
        );
    }
}
