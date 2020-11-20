package com.radish.mythscapes.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import org.jetbrains.annotations.NotNull;

public class PetrifiedEffect extends MythEffect {

    public PetrifiedEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public void performEffect(@NotNull LivingEntity entity, int amplifier) {

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
