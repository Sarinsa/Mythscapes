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
    protected void add(EntityType<?> type, LootTable.Builder table) {
        super.add(type, table);
        this.knownEntities.add(type);
    }

    @Override
    protected void addTables() {
        this.add(MythEntities.FISHBONES.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(Items.BONE_MEAL)
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 3.0F))))
                        .add(ItemLootEntry.lootTableItem(Items.ICE)
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 2.0F))))
                        .add(ItemLootEntry.lootTableItem(Items.PACKED_ICE)
                                .when(RandomChance.randomChance(0.20F))
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))
                        .add(ItemLootEntry.lootTableItem(Items.BLUE_ICE)
                                .when(RandomChance.randomChance(0.05F))
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F))))));

        this.add(MythEntities.POND_SERPENT.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(Items.BONE_MEAL)
                                .when(RandomChance.randomChance(0.20F)))
                        .add(ItemLootEntry.lootTableItem(MythItems.POND_SERPENT.get()))));

        this.add(MythEntities.LION.get(),LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(MythItems.LION_FUR.get())
                                .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F))))));

        this.add(MythEntities.PYGMY_SNAIL.get(), LootTable.lootTable());
    }
}
