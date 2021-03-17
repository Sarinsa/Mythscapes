package com.radish.mythscapes.datagen.tag_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.tags.MythBlockTags;
import com.radish.mythscapes.common.tags.MythItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythItemTagProvider extends ItemTagsProvider {

    public MythItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.copyTags();

        this.tag(MythItemTags.WOLT_LOGS).add(
                MythItems.WOLT_LOG.get(),
                MythItems.WOLT_LOG_STRIPPED.get(),
                MythItems.WOLT_WOOD.get(),
                MythItems.WOLT_WOOD_STRIPPED.get()
        );

        this.tag(ItemTags.BOATS).add(
                MythItems.WOLT_BOAT.get()
        );

        this.tag(MythItemTags.PRISMARINE).add(
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
                .addOptionalTag(Tags.Items.GEMS_PRISMARINE.getName()
        );

        this.tag(Tags.Items.BOOKSHELVES).add(
                MythItems.WOLT_BOOKSHELF.get()
        );

        this.tag(MythItemTags.BOATABLE_CHESTS).add(
                MythItems.WOLT_CHEST.get()
        );
    }

    private void copyTags() {
        this.copy(Tags.Blocks.ORES_DIAMOND, Tags.Items.ORES_DIAMOND);
        this.copy(Tags.Blocks.ORES_GOLD, Tags.Items.ORES_GOLD);
        this.copy(Tags.Blocks.ORES_REDSTONE, Tags.Items.ORES_REDSTONE);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.STONE_BRICKS, ItemTags.STONE_BRICKS);
        this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
        this.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
        this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);
        this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
        this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        this.copy(BlockTags.DOORS, ItemTags.DOORS);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.SAND, ItemTags.SAND);
        this.copy(BlockTags.SLABS, ItemTags.SLABS);
        this.copy(BlockTags.WALLS, ItemTags.WALLS);
        this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
        this.copy(BlockTags.RAILS, ItemTags.RAILS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
        this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
        this.copy(BlockTags.FENCES, ItemTags.FENCES);
        this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
        this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);

        this.copy(MythBlockTags.VERTICAL_SLAB, MythItemTags.VERTICAL_SLAB);
        this.copy(MythBlockTags.PLANKS_VERTICAL_SLAB, MythItemTags.PLANKS_VERTICAL_SLAB);
        this.copy(MythBlockTags.LADDERS, MythItemTags.LADDERS);
        this.copy(MythBlockTags.HEDGES, MythItemTags.HEDGES);
    }
}
