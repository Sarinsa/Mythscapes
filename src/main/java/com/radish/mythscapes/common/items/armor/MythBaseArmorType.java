package com.radish.mythscapes.common.items.armor;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public abstract class MythBaseArmorType implements IArmorMaterial {

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    @Override
    public String getName() {
        return Mythscapes.resourceLoc(this.getRegName()).toString();
    }

    public abstract String getRegName();
}
