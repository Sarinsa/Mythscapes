package com.radish.mythscapes.datagen.loot_table_providers;

import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;

import java.util.HashSet;
import java.util.Set;

public class MythEntityLootTableProvider extends EntityLootTables {

    private final Set<EntityType<?>> knownEntities = new HashSet<>();

    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        return this.knownEntities;
    }

    @Override
    protected void registerLootTable(EntityType<?> type, LootTable.Builder table) {
        super.registerLootTable(type, table);
        this.knownEntities.add(type);
    }

    @Override
    protected void addTables() {
        this.registerLootTable(MythEntities.FISHBONES.get(), LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(Items.BONE_MEAL)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 3.0F))))
                        .addEntry(ItemLootEntry.builder(Items.ICE)
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 2.0F))))
                        .addEntry(ItemLootEntry.builder(Items.PACKED_ICE)
                                .acceptCondition(RandomChance.builder(0.20F))
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))
                        .addEntry(ItemLootEntry.builder(Items.BLUE_ICE)
                                .acceptCondition(RandomChance.builder(0.05F))
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F)))
                                .acceptFunction(LootingEnchantBonus.builder(RandomValueRange.of(0.0F, 1.0F))))));

        this.registerLootTable(MythEntities.POND_SERPENT.get(), LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(Items.BONE_MEAL)
                                .acceptCondition(RandomChance.builder(0.20F)))
                        .addEntry(ItemLootEntry.builder(MythItems.POND_SERPENT.get()))));

        this.registerLootTable(MythEntities.LION.get(),LootTable.builder()
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(MythItems.LION_FUR.get())
                                .acceptFunction(SetCount.builder(RandomValueRange.of(0.0F, 1.0F))))));

        this.registerLootTable(MythEntities.PYGMY_SNAIL.get(), LootTable.builder());
    }
}
