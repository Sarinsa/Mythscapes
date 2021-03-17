package com.radish.mythscapes.common.effects;

import com.radish.mythscapes.common.misc.MythDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class VolatileEffect extends MythEffect {


    public VolatileEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isOnFire()) {
            World world = entity.getCommandSenderWorld();
            Vector3d pos = entity.position();

            entity.removeEffect(this);
            entity.clearFire();

            if (!world.isClientSide) {
                world.explode(null, MythDamageSources.VOLATILE_EXPLOSION, null, pos.x(), pos.y() + (entity.getBbHeight() / 2), pos.z(), 1.2f, false, Explosion.Mode.NONE);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
