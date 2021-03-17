package com.radish.mythscapes.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.Objects;

public abstract class MythEffect extends Effect {

    public MythEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    public static int getDuration(LivingEntity entity, Effect effect) {
        if (entity.getEffect(effect) != null && !effect.isInstantenous()) {
            return Objects.requireNonNull(entity.getEffect(effect)).getDuration();
        }
        return 0;
    }
}
