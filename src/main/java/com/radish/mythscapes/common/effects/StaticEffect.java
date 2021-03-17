package com.radish.mythscapes.common.effects;

import com.radish.mythscapes.common.misc.MythDamageSources;
import com.radish.mythscapes.common.tags.MythEntityTags;
import com.radish.mythscapes.common.tags.MythFluidTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
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
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (MythEntityTags.ELECTRIC.contains(entity.getType()))
            return;

        World world = entity.getCommandSenderWorld();
        BlockPos pos = entity.blockPosition();

        if (isInConductiveFluid(entity, world)) {
            int duration = getDuration(entity, this);
            float damage = (float) Math.floor((float)duration / (5 * 20));

            List<? extends LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(pos).inflate(3));
            for (LivingEntity target : entities) {
                if (isInConductiveFluid(target, world))
                    target.hurt(MythDamageSources.STATIC_SHOCK, damage);
            }
            entity.removeEffect(this);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }


    private boolean isInConductiveFluid(LivingEntity entity, World world) {
        FluidState fluidState = world.getFluidState(entity.blockPosition());
        return entity.isEyeInFluid(MythFluidTags.CONDUCTIVE) || (!fluidState.isEmpty() && fluidState.is(MythFluidTags.CONDUCTIVE));
    }
}
