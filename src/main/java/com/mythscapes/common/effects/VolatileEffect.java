package com.mythscapes.common.effects;

import com.mythscapes.common.damagesource.MythDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class VolatileEffect extends MythEffect {


    public VolatileEffect(EffectType effectType, int color) {
        super(effectType, color);
    }
    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        if (entity.isBurning()) {
            World world = entity.getEntityWorld();
            Vec3d pos = entity.getPositionVec();

            entity.removePotionEffect(this);
            entity.extinguish();

            if (!world.isRemote) {
                world.createExplosion(null, MythDamageSources.VOLATILE_EXPLOSION, pos.getX(), pos.getY() + (entity.getHeight() / 2), pos.getZ(), 1.2f, false, Explosion.Mode.NONE);
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
