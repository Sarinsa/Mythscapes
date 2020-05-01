package com.mythscapes.common.items.armor;

import com.mythscapes.register.MythItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class MythCottonArmorType extends MythBaseArmorType {

    @Override
    public int getDurability(EquipmentSlotType slotType) {
        switch (slotType) {
            case HEAD:
                return 230;
            case CHEST:
                return 680;
            case LEGS:
                return 590;
            case FEET:
                return 220;
        }
        return 0;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotType) {
        switch (slotType) {
            case HEAD:
                return 1;
            case CHEST:
            case LEGS:
                return 2;
            case FEET:
                return 1;
        }
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(MythItems.COTTON_HIDE.get());
    }

    @Override
    public String getRegName() {
        return "cotton";
    }

    @Override
    public float getToughness() {
        return 0;
    }
}
