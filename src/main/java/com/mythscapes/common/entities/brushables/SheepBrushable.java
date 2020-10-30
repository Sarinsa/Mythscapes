package com.mythscapes.common.entities.brushables;

import com.mythscapes.api.IBrushable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import java.util.Random;

public class SheepBrushable implements IBrushable<SheepEntity> {

    @Override
    public void onBrushed(SheepEntity sheepEntity, World world) {

    }

    @Override
    public boolean canBrush(SheepEntity sheepEntity) {
        return !sheepEntity.getSheared();
    }

    @Override
    public ItemStack itemDropped(int fortuneLevel) {
        return new ItemStack(Items.STRING, (new Random().nextInt(3 + fortuneLevel) + 1));
    }
}
