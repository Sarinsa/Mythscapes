package com.mythscapes.common.entities.brushables;

import com.mythscapes.api.IBrushable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BirdBrushable implements IBrushable<LivingEntity> {

    @Override
    public void onBrushed(LivingEntity livingEntity, World world) {

    }

    @Override
    public boolean canBrush(LivingEntity livingEntity) {
        return true;
    }

    @Override
    public ItemStack itemDropped(int fortuneLevel) {
        return new ItemStack(Items.FEATHER);
    }
}
