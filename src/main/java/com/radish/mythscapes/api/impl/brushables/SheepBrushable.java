package com.radish.mythscapes.api.impl.brushables;

import com.radish.mythscapes.api.IBrushable;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class SheepBrushable implements IBrushable<SheepEntity> {

    @Override
    public void onBrushed(SheepEntity sheepEntity, World world) {

    }

    @Override
    public boolean canBrush(SheepEntity sheepEntity, World world) {
        return !sheepEntity.isSheared();
    }

    @Override
    public ItemStack itemDropped(SheepEntity livingEntity, Random random, int fortuneLevel) {
        return new ItemStack(Items.STRING, random.nextInt(3 + fortuneLevel) + 1);
    }
}
