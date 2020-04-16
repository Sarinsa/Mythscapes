package com.mythscapes.common.items.armor;

import com.mythscapes.register.MythItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class MythArmorItem extends ArmorItem {

    public MythArmorItem(MythBaseArmorType armorType, EquipmentSlotType slot) {
        super(armorType, slot, new Item.Properties().group(MythItems.itemGroup));
    }
}
