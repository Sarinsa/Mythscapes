package com.radish.mythscapes.common.items.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class MythBarbarianArmorType extends MythBaseArmorType {

    @Override
    public String getRegName() {
        return "barbarian";
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return 100;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return 2;
    }

    @Override
    public int getEnchantmentValue() {
        return 5;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }
}
