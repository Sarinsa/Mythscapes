package com.mythscapes.common.items.armor;

import com.mythscapes.core.Mythscapes;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public abstract class MythBaseArmorType implements IArmorMaterial {

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public String getName() {
        return Mythscapes.MODID + ":" + getRegName();
    }

    public abstract String getRegName();
}
