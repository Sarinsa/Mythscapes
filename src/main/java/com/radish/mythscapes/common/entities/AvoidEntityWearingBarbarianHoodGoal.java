package com.radish.mythscapes.common.entities;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class AvoidEntityWearingBarbarianHoodGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

    public AvoidEntityWearingBarbarianHoodGoal(CreatureEntity creatureEntity, Class<T> classToAvoidIn, float avoidDistance, double farSpeedIn, double nearSpeedIn) {
        super(creatureEntity, classToAvoidIn, avoidDistance, farSpeedIn, nearSpeedIn);
    }

    @Override
    public boolean shouldExecute() {
        return super.shouldExecute() && this.isPlayerWearingHood();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return super.shouldContinueExecuting() && this.isPlayerWearingHood();
    }

    private boolean isPlayerWearingHood() {
        ItemStack itemStack = this.avoidTarget.getItemStackFromSlot(EquipmentSlotType.HEAD);
        Mythscapes.LOGGER.info("Player wearing hood: " + (!itemStack.isEmpty() && itemStack.getItem() == MythItems.BARBARIAN_HOOD.get()));
        Mythscapes.LOGGER.info("Player helmet slot item:" + itemStack.getItem());
        return !itemStack.isEmpty() && itemStack.getItem() == MythItems.BARBARIAN_HOOD.get();
    }
}
