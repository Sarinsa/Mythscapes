package com.mythscapes.common.entities.brushables;

import com.mythscapes.api.IBrushable;
import com.mythscapes.common.entities.living.LionEntity;
import com.mythscapes.register.MythItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class LionBrushable implements IBrushable<LionEntity> {

    @Override
    public void onBrushed(LionEntity lionEntity, World world) {
        lionEntity.newTimeManeRegrow();
    }

    @Override
    public boolean canBrush(LionEntity lionEntity) {
        return !lionEntity.isChild() && lionEntity.hasMane();
    }

    @Override
    public ItemStack itemDropped(int fortuneLevel) {
        return new ItemStack(MythItems.LION_FUR.get(), 1 + (fortuneLevel > 0 ? new Random().nextInt(fortuneLevel) : 0));
    }
}
