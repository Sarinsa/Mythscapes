package com.radish.mythscapes.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import org.jetbrains.annotations.NotNull;

public class PetrifiedEffect extends MythEffect {

    public PetrifiedEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {

    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
