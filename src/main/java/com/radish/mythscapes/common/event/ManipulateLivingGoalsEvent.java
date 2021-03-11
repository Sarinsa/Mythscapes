package com.radish.mythscapes.common.event;

import com.radish.mythscapes.common.entities.living.LionEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.*;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Keeping all this code in here to not
 * fill up EntityEvents too much.
 */
public class ManipulateLivingGoalsEvent {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onLivingSpawn(LivingSpawnEvent event) {
        LivingEntity entity = event.getEntityLiving();
        EntityType<?> type = entity.getType();

        /*
        if (entity instanceof CreatureEntity) {
            if (((CreatureEntity)entity).goalSelector != null) {
                ((CreatureEntity)entity).goalSelector.addGoal(1, new AvoidEntityWearingBarbarianHoodGoal<>((CreatureEntity) entity, PlayerEntity.class, 14.0F, 1.0F, 1.1F));
            }
        }

         */

        if (entity instanceof VindicatorEntity || entity instanceof PillagerEntity || entity instanceof EvokerEntity || entity instanceof IllusionerEntity) {
            AbstractIllagerEntity illagerEntity = (AbstractIllagerEntity) entity;
            double farSpeed = (type == EntityType.EVOKER || type == EntityType.ILLUSIONER) ? 0.7D : 1.0D;
            double nearSpeed = (type == EntityType.EVOKER || type == EntityType.ILLUSIONER) ? 0.9D : 1.1D;
            illagerEntity.goalSelector.addGoal(3, new AvoidEntityGoal<>(illagerEntity, LionEntity.class, 10.0F, farSpeed, nearSpeed));

            if (type == EntityType.EVOKER) {
                illagerEntity.targetSelector.addGoal(4 ,new NearestAttackableTargetGoal<>(illagerEntity, LionEntity.class, true));
            }
        }

        else if (entity instanceof CreeperEntity) {
            CreatureEntity creatureEntity = (CreatureEntity) entity;
            creatureEntity.goalSelector.addGoal(3, new AvoidEntityGoal<>(creatureEntity, LionEntity.class, 6.0F, 1.0D, 1.2D));
        }
    }
}
