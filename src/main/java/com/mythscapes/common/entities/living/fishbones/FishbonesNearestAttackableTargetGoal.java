package com.mythscapes.common.entities.living.fishbones;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class FishbonesNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public FishbonesNearestAttackableTargetGoal(FishbonesEntity fishbones, Class<T> targetClass, boolean checkSight) {
        super(fishbones, targetClass, checkSight);
    }

    public FishbonesNearestAttackableTargetGoal(FishbonesEntity fishbones, Class<T> targetClass, int targetChance, boolean checkSight, boolean nearbyOnly, @Nullable Predicate<LivingEntity> targetPredicate) {
        super(fishbones, targetClass, targetChance, checkSight, nearbyOnly, targetPredicate);
    }

    @Override
    protected void findNearestTarget() {
        LivingEntity entity;

        if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {
            entity = this.goalOwner.world.func_225318_b(this.targetClass, this.targetEntitySelector, this.goalOwner, this.goalOwner.getPosX(), this.goalOwner.getPosYEye(), this.goalOwner.getPosZ(), this.getTargetableArea(this.getTargetDistance()));
        } else {
            entity = this.goalOwner.world.getClosestPlayer(this.targetEntitySelector, this.goalOwner, this.goalOwner.getPosX(), this.goalOwner.getPosYEye(), this.goalOwner.getPosZ());
        }
        if (!FishbonesEntity.entityHoldingPrismarine(entity)) {
            this.nearestTarget = entity;
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return super.shouldContinueExecuting() && !FishbonesEntity.entityHoldingPrismarine(this.target);
    }
}
