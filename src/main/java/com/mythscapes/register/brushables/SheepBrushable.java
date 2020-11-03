package com.mythscapes.register.brushables;

import com.mythscapes.api.IBrushable;
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
        return !sheepEntity.getSheared();
    }

    @Override
    public @NotNull ItemStack itemDropped(SheepEntity livingEntity, int fortuneLevel) {
        return new ItemStack(Items.STRING, (new Random().nextInt(3 + fortuneLevel) + 1));
    }
}
