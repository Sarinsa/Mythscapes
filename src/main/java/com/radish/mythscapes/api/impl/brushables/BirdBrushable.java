package com.radish.mythscapes.api.impl.brushables;

import com.radish.mythscapes.api.IBrushable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BirdBrushable implements IBrushable<LivingEntity> {

    @Override
    public void onBrushed(LivingEntity livingEntity, World world) {

    }

    @Override
    public boolean canBrush(LivingEntity livingEntity, World world) {
        return true;
    }

    @Override
    public @NotNull ItemStack itemDropped(LivingEntity livingEntity, int fortuneLevel) {
        return new ItemStack(Items.FEATHER);
    }
}
