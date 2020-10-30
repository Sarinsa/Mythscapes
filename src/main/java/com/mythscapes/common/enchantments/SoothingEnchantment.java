package com.mythscapes.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SoothingEnchantment extends Enchantment {

    public SoothingEnchantment(Rarity rarity, EnchantmentType type, EquipmentSlotType... slots) {
        super(rarity, type, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
