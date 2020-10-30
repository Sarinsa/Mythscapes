package com.mythscapes.datagen.loot_table_providers;

import com.mythscapes.common.blocks.StaticCottonPilesBlock;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.SetCount;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class MythBlockLootTableProvider extends BlockLootTables {

    // Vanilla
    private static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
    private static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
    private static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
    private static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void registerLootTable(Block block, LootTable.Builder table) {
        super.registerLootTable(block, table);
        this.knownBlocks.add(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }

    @Override
    @SuppressWarnings("all")
    protected void addTables() {
        this.registerLootTable(MythBlocks.GILDED_GALVITE.get(), (block) ->
                droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.GOLD_NUGGET)
                        .acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F)))
                        .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));

        this.registerLootTable(MythBlocks.BEJEWELED_GALVITE.get(), (block) ->
                droppingItemWithFortune(block, Items.DIAMOND));

        this.registerLootTable(MythBlocks.POWERED_GALVITE.get(), (block) ->
                droppingWithSilkTouch(block, withExplosionDecay(block, ItemLootEntry.builder(Items.REDSTONE)
                        .acceptFunction(SetCount.builder(RandomValueRange.of(4.0F, 5.0F)))
                        .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE)))));

        this.registerDropSelfLootTable(MythBlocks.GALVITE.get());
        this.registerDropSelfLootTable(MythBlocks.GALVITE_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.GALVITE_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.GALVITE_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_BUTTON.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_BRICKS.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_GALVITE_BRICK_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.CHISELED_POLISHED_GALVITE.get());
        this.registerDropSelfLootTable(MythBlocks.GALVITE_SHINGLES.get());
        this.registerDropSelfLootTable(MythBlocks.GALVITE_SHINGLE_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.GALVITE_SHINGLE_STAIRS.get());

        this.registerDropSelfLootTable(MythBlocks.GILDED_GALVITE_BRICKS.get());
        this.registerDropSelfLootTable(MythBlocks.GILDED_GALVITE_BRICK_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.GILDED_GALVITE_BRICK_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.GILDED_GALVITE_BRICK_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.BEJEWELED_GALVITE_BRICKS.get());
        this.registerDropSelfLootTable(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.POWERED_GALVITE_BRICKS.get());
        this.registerDropSelfLootTable(MythBlocks.POWERED_GALVITE_BRICK_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.POWERED_GALVITE_BRICK_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.POWERED_GALVITE_BRICK_WALL.get());

        this.registerDropSelfLootTable(MythBlocks.TROLLSTONE.get());
        this.registerDropSelfLootTable(MythBlocks.TROLLSTONE_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.TROLLSTONE_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.TROLLSTONE_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_BUTTON.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_BRICKS.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL.get());
        this.registerDropSelfLootTable(MythBlocks.POLISHED_TROLLSTONE_PILLAR.get());
        this.registerDropSelfLootTable(MythBlocks.CHISELED_POLISHED_TROLLSTONE.get());

        this.registerLootTable(MythBlocks.WOLT_LEAVES.get(), (block) -> droppingWithChancesAndSticks(block, MythBlocks.WOLT_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES));

        this.registerDropSelfLootTable(MythBlocks.WOLT_LOG.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_WOOD.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_LOG_STRIPPED.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_WOOD_STRIPPED.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_PLANKS.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_FENCE.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_FENCE_GATE.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_PRESSURE_PLATE.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_BUTTON.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_DOOR.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_TRAPDOOR.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_SAPLING.get());

        /*
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_STEM.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_WOOD.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_STEM_STRIPPED.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_WOOD_STRIPPED.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_PLANKS.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_SLAB.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_STAIRS.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_FENCE.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_FENCE_GATE.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_PRESSURE_PLATE.get());
        this.registerDropSelfLootTable(MythBlocks.VIRIDIAN_BUTTON.get());
         */

        this.registerDropSelfLootTable(MythBlocks.CHARGED_DANDELION.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_POWDER_BLOCK.get());
        this.registerDropSelfLootTable(MythBlocks.GOLDEN_WOLT_POWDER_BLOCK.get());
        this.registerDropSelfLootTable(MythBlocks.BIOBULB_CLUSTER.get());
        this.registerDropSelfLootTable(MythBlocks.ROASTED_BIOBULB_CLUSTER.get());
        this.registerDropSelfLootTable(MythBlocks.BIOBULB_LAMP.get());
        this.registerDropSelfLootTable(MythBlocks.STATIC_COTTON_BLOCK.get());
        this.registerDropSelfLootTable(MythBlocks.WOLT_POWDER_BLOCK.get());

        this.registerLootTable(MythBlocks.STATIC_COTTON_PILES.get(), (block) -> LootTable.builder()
                .addLootPool(LootPool.builder()
                        .acceptCondition(EntityHasProperty.builder(LootContext.EntityTarget.THIS))
                        .addEntry(AlternativesLootEntry.builder(AlternativesLootEntry.builder(ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 1))), ((StandaloneLootEntry.Builder)ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 2))))
                                .acceptFunction(SetCount.builder(ConstantRange.of(2))), ((StandaloneLootEntry.Builder)ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(StaticCottonPilesBlock.LAYERS, 3))))
                                .acceptFunction(SetCount.builder(ConstantRange.of(3))), ((StandaloneLootEntry.Builder)ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                        .withIntProp(StaticCottonPilesBlock.LAYERS, 4))))
                                .acceptFunction(SetCount.builder(ConstantRange.of(4))), ((StandaloneLootEntry.Builder)ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 5))))
                                .acceptFunction(SetCount.builder(ConstantRange.of(5))), ((StandaloneLootEntry.Builder)ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(StaticCottonPilesBlock.LAYERS, 6))))
                                .acceptFunction(SetCount.builder(ConstantRange.of(6))), ((StandaloneLootEntry.Builder)ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 7))))
                                .acceptFunction(SetCount.builder(ConstantRange.of(7))), ItemLootEntry.builder(MythItems.STATIC_COTTON.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(8))))
                                .acceptCondition(NO_SILK_TOUCH), AlternativesLootEntry.builder(ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 1))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(2)))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 2))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(3)))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 3))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(4)))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 4))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(5)))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 5))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(6)))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 6))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get())
                                .acceptFunction(SetCount.builder(ConstantRange.of(7)))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .fromProperties(StatePropertiesPredicate.Builder.newBuilder()
                                                .withIntProp(StaticCottonPilesBlock.LAYERS, 7))), ItemLootEntry.builder(MythBlocks.STATIC_COTTON_PILES.get()))))));

        this.registerDropSelfLootTable(MythBlocks.LAUNCHER_RAIL.get());
        this.registerDropSelfLootTable(MythBlocks.SNAIL_SHELL_BLOCK.get());
        this.registerDropSelfLootTable(MythBlocks.BEJEWELED_SNAIL_SHELL_BLOCK.get());
    }
}
