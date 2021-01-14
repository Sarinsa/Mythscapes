package com.radish.mythscapes.api.impl.brushables;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.common.entities.living.LionEntity;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LionBrushable implements IBrushable<LionEntity> {

    @Override
    public void onBrushed(LionEntity lionEntity, World world) {
        lionEntity.newTimeManeRegrow();
    }

    @Override
    public boolean canBrush(LionEntity lionEntity, World world) {
        return !lionEntity.isChild() && lionEntity.hasMane();
    }

    @Override
    public @NotNull ItemStack itemDropped(LionEntity lionEntity, Random random, int fortuneLevel) {
        return new ItemStack(MythItems.LION_FUR.get(), 1 + (fortuneLevel > 0 ? random.nextInt(fortuneLevel) : 0));
    }
}
