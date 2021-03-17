package com.radish.mythscapes.common.items.armor;

import com.radish.mythscapes.common.misc.MythItemGroup;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class MythArmorItem extends ArmorItem {

    public MythArmorItem(MythBaseArmorType armorType, EquipmentSlotType slot) {
        super(armorType, slot, new Item.Properties().tab(MythItemGroup.MOD_ITEM_GROUP));
    }
}
