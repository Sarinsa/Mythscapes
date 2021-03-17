package com.radish.mythscapes.datagen.loot_table_providers;

import com.radish.mythscapes.common.blocks.StaticCottonPilesBlock;
import com.radish.mythscapes.common.blocks.compat.VerticalSlabBlock;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraftforge.common.Tags;

import java.util.HashSet;
import java.util.Set;

public class MythBlockLootTableProvider extends BlockLootTables {

    // Vanilla
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.invert();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void add(Block block, LootTable.Builder table) {
        super.add(block, table);
        this.knownBlocks.add(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }

    @Override
    @SuppressWarnings("all")
    protected void addTables() {
        this.add(MythBlocks.GILDED_GALVITE.get(), (block) -> {
            return createSilkTouchDispatchTable(block, applyExplosionCondition(block, ItemLootEntry.lootTableItem(Items.GOLD_NUGGET)
                    .apply(SetCount.setCount(RandomValueRange.between(2.0F, 5.0F)))
                    .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.1F, 0.14285715F, 0.25F, 1.0F))
                    .otherwise(ItemLootEntry.lootTableItem(block))));
        });

        this.add(MythBlocks.BEJEWELED_GALVITE.get(), (block) ->
                createOreDrop(block, Items.DIAMOND));

        this.add(MythBlocks.POWERED_GALVITE.get(), (block) ->
                createSilkTouchDispatchTable(block, applyExplosionDecay(block, ItemLootEntry.lootTableItem(Items.REDSTONE)
                        .apply(SetCount.setCount(RandomValueRange.between(4.0F, 5.0F)))
                        .apply(ApplyBonus.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));

        this.dropSelf(MythBlocks.GALVITE.get());
        this.add(MythBlocks.GALVITE_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.GALVITE_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.GALVITE_STAIRS.get());
        this.dropSelf(MythBlocks.GALVITE_WALL.get());
        this.dropSelf(MythBlocks.POLISHED_GALVITE.get());
        this.add(MythBlocks.POLISHED_GALVITE_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.POLISHED_GALVITE_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.POLISHED_GALVITE_STAIRS.get());
        this.dropSelf(MythBlocks.POLISHED_GALVITE_WALL.get());
        this.dropSelf(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE.get());
        this.dropSelf(MythBlocks.POLISHED_GALVITE_BUTTON.get());
        this.dropSelf(MythBlocks.POLISHED_GALVITE_BRICKS.get());
        this.add(MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.POLISHED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.POLISHED_GALVITE_BRICK_WALL.get());
        this.dropSelf(MythBlocks.CHISELED_POLISHED_GALVITE.get());
        this.dropSelf(MythBlocks.GALVITE_SHINGLES.get());
        this.add(MythBlocks.GALVITE_SHINGLE_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.GALVITE_SHINGLE_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.GALVITE_SHINGLE_STAIRS.get());
        this.dropSelf(MythBlocks.GILDED_GALVITE_BRICKS.get());
        this.add(MythBlocks.GILDED_GALVITE_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.GILDED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.GILDED_GALVITE_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.GILDED_GALVITE_BRICK_WALL.get());
        this.dropSelf(MythBlocks.BEJEWELED_GALVITE_BRICKS.get());
        this.add(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL.get());
        this.dropSelf(MythBlocks.POWERED_GALVITE_BRICKS.get());
        this.add(MythBlocks.POWERED_GALVITE_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.POWERED_GALVITE_BRICK_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.POWERED_GALVITE_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.POWERED_GALVITE_BRICK_WALL.get());

        this.dropSelf(MythBlocks.TROLLSTONE.get());
        this.add(MythBlocks.TROLLSTONE_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.TROLLSTONE_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.TROLLSTONE_STAIRS.get());
        this.dropSelf(MythBlocks.TROLLSTONE_WALL.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE.get());
        this.add(MythBlocks.POLISHED_TROLLSTONE_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.POLISHED_TROLLSTONE_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_STAIRS.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_WALL.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_BUTTON.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_BRICKS.get());
        this.add(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL.get());
        this.dropSelf(MythBlocks.POLISHED_TROLLSTONE_PILLAR.get());
        this.dropSelf(MythBlocks.CHISELED_POLISHED_TROLLSTONE.get());

        this.dropSelf(MythBlocks.WOLT_LOG.get());
        this.dropSelf(MythBlocks.WOLT_WOOD.get());
        this.dropSelf(MythBlocks.WOLT_LOG_STRIPPED.get());
        this.dropSelf(MythBlocks.WOLT_WOOD_STRIPPED.get());
        this.dropSelf(MythBlocks.WOLT_PLANKS.get());
        this.dropSelf(MythBlocks.WOLT_VERTICAL_PLANKS.get());
        this.add(MythBlocks.WOLT_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.WOLT_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.WOLT_STAIRS.get());
        this.dropSelf(MythBlocks.WOLT_FENCE.get());
        this.dropSelf(MythBlocks.WOLT_FENCE_GATE.get());
        this.dropSelf(MythBlocks.WOLT_PRESSURE_PLATE.get());
        this.dropSelf(MythBlocks.WOLT_BUTTON.get());
        this.dropSelf(MythBlocks.WOLT_SIGN.get());
        this.add(MythBlocks.WOLT_DOOR.get(), BlockLootTables::createDoorTable);
        this.dropSelf(MythBlocks.WOLT_TRAPDOOR.get());
        this.dropSelf(MythBlocks.WOLT_LADDER.get());
        this.add(MythBlocks.WOLT_BOOKSHELF.get(), (bookshelf) -> {
            return createSingleItemTableWithSilkTouch(bookshelf, Items.BOOK, ConstantRange.exactly(3));
        });
        this.dropSelf(MythBlocks.WOLT_POST.get());
        this.add(MythBlocks.WOLT_CHEST.get(), BlockLootTables::createNameableBlockEntityTable);
        this.add(MythBlocks.WOLT_TRAPPED_CHEST.get(), BlockLootTables::createNameableBlockEntityTable);
        this.dropSelf(MythBlocks.WOLT_HEDGE.get());
        this.dropSelf(MythBlocks.WOLT_SAPLING.get());
        this.add(MythBlocks.WOLT_LEAVES.get(), (block) -> createLeavesDrops(block, MythBlocks.WOLT_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));
        this.dropSelf(MythBlocks.WOLT_LEAF_CARPET.get());

        /*
        this.dropSelf(MythBlocks.VIRIDIAN_STEM.get());
        this.dropSelf(MythBlocks.VIRIDIAN_WOOD.get());
        this.dropSelf(MythBlocks.VIRIDIAN_STEM_STRIPPED.get());
        this.dropSelf(MythBlocks.VIRIDIAN_WOOD_STRIPPED.get());
        this.dropSelf(MythBlocks.VIRIDIAN_PLANKS.get());
        this.dropSelf(MythBlocks.VIRIDIAN_SLAB.get());
        this.dropSelf(MythBlocks.VIRIDIAN_STAIRS.get());
        this.dropSelf(MythBlocks.VIRIDIAN_FENCE.get());
        this.dropSelf(MythBlocks.VIRIDIAN_FENCE_GATE.get());
        this.dropSelf(MythBlocks.VIRIDIAN_PRESSURE_PLATE.get());
        this.dropSelf(MythBlocks.VIRIDIAN_BUTTON.get());
         */

        this.dropSelf(MythBlocks.CHARGED_DANDELION.get());
        this.dropSelf(MythBlocks.WOLT_POWDER_BLOCK.get());
        this.dropSelf(MythBlocks.GOLDEN_WOLT_POWDER_BLOCK.get());
        this.dropSelf(MythBlocks.BIOBULB_CLUSTER.get());
        this.dropSelf(MythBlocks.ROASTED_BIOBULB_CLUSTER.get());
        this.dropSelf(MythBlocks.BIOBULB_LAMP.get());
        this.dropSelf(MythBlocks.STATIC_COTTON_BLOCK.get());
        this.dropSelf(MythBlocks.WOLT_POWDER_BLOCK.get());

        // This is an adjustment of the snow layers block's loot table. How the frick does the
        // people at Mojang write this stuff without having their brains explode?
        this.add(MythBlocks.STATIC_COTTON_PILES.get(), (block) -> LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(EntityHasProperty.entityPresent(LootContext.EntityTarget.THIS))
                        .add(AlternativesLootEntry.alternatives(AlternativesLootEntry.alternatives(ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 1))), ((StandaloneLootEntry.Builder)ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 2))))
                                .apply(SetCount.setCount(ConstantRange.exactly(2))), ((StandaloneLootEntry.Builder)ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StaticCottonPilesBlock.LAYERS, 3))))
                                .apply(SetCount.setCount(ConstantRange.exactly(3))), ((StandaloneLootEntry.Builder)ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(StaticCottonPilesBlock.LAYERS, 4))))
                                .apply(SetCount.setCount(ConstantRange.exactly(4))), ((StandaloneLootEntry.Builder)ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 5))))
                                .apply(SetCount.setCount(ConstantRange.exactly(5))), ((StandaloneLootEntry.Builder)ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StaticCottonPilesBlock.LAYERS, 6))))
                                .apply(SetCount.setCount(ConstantRange.exactly(6))), ((StandaloneLootEntry.Builder)ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 7))))
                                .apply(SetCount.setCount(ConstantRange.exactly(7))), ItemLootEntry.lootTableItem(MythItems.STATIC_COTTON.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(8))))
                                .when(NO_SILK_TOUCH), AlternativesLootEntry.alternatives(ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 1))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(2)))
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 2))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(3)))
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 3))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(4)))
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 4))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(5)))
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 5))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(6)))
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 6))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get())
                                .apply(SetCount.setCount(ConstantRange.exactly(7)))
                                .when(BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(StaticCottonPilesBlock.LAYERS, 7))), ItemLootEntry.lootTableItem(MythBlocks.STATIC_COTTON_PILES.get()))))));

        this.dropSelf(MythBlocks.LAUNCHER_RAIL.get());
        this.dropSelf(MythBlocks.SNAIL_SHELL_BLOCK.get());
        this.dropSelf(MythBlocks.SNAIL_SHELL_BRICKS.get());
        this.add(MythBlocks.SNAIL_SHELL_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.SNAIL_SHELL_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.SNAIL_SHELL_BRICK_WALL.get());
        this.dropSelf(MythBlocks.BEJEWELED_SNAIL_SHELL_BLOCK.get());
        this.dropSelf(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get());
        this.add(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB.get(), BlockLootTables::createSlabItemTable);
        this.add(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(), MythBlockLootTableProvider::droppingVerticalSlab);
        this.dropSelf(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_STAIRS.get());
        this.dropSelf(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_WALL.get());
    }

    protected static LootTable.Builder droppingVerticalSlab(Block slab) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(applyExplosionDecay(slab, ItemLootEntry.lootTableItem(slab)
                                .apply(SetCount.setCount(ConstantRange.exactly(2))
                                        .when(BlockStateProperty.hasBlockStateProperties(slab)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(VerticalSlabBlock.SLAB_TYPE, VerticalSlabBlock.Type.DOUBLE)))))));
    }
}
