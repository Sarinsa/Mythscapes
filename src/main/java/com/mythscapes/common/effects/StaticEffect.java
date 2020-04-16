package com.mythscapes.common.effects;

import com.mythscapes.common.damagesource.MythDamageSources;
import com.mythscapes.common.tags.MythEntityTags;
import com.mythscapes.common.tags.MythFluidTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class StaticEffect extends MythEffect {

    public StaticEffect(EffectType effectType, int color) {
        super(effectType, color);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        if (MythEntityTags.ELECTRIC.contains(entity.getType()))
            return;

        World world = entity.getEntityWorld();
        BlockPos pos = entity.getPosition();

        if (isInConductiveFluid(entity, world)) {
            int duration = getDuration(entity, this);
            float damage = (float) Math.floor((float)duration / (5 * 20));

            List<? extends LivingEntity> entities = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos).grow(3));
            for (LivingEntity target : entities) {
                if (isInConductiveFluid(target, world))
                    target.attackEntityFrom(MythDamageSources.STATIC_SHOCK, damage);
            }
            entity.removePotionEffect(this);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }


    private boolean isInConductiveFluid(LivingEntity entity, World world) {
        IFluidState fluidState = world.getFluidState(entity.getPosition());
        return entity.areEyesInFluid(MythFluidTags.CONDUCTIVE) || (!fluidState.isEmpty() && fluidState.isTagged(MythFluidTags.CONDUCTIVE));
    }
}
