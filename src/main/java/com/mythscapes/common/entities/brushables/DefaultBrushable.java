package com.mythscapes.common.entities.brushables;

import com.mythscapes.api.IBrushable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class DefaultBrushable implements IBrushable<LivingEntity> {

    @Override
    public void onBrushed(LivingEntity livingEntity, World world) {

    }

    @Override
    public boolean canBrush(LivingEntity livingEntity) {
        return true;
    }

    @Override
    public ItemStack itemDropped(int fortuneLevel) {
        return ItemStack.EMPTY;
    }
}
