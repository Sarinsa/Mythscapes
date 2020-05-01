package com.mythscapes.common.entities.living.fishbones;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.List;

public class FollowPrismarineHolderGoal extends Goal {

    private final FishbonesEntity fishbones;
    private LivingEntity prismarineHolder;

    private final double followRange;
    private final double moveSpeed;
    private int recalcPathTime;

    public FollowPrismarineHolderGoal(FishbonesEntity entity, double followRange, double speed) {
        this.fishbones = entity;
        this.moveSpeed = speed;
        this.followRange = followRange;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        List<LivingEntity> entities = this.fishbones.getEntityWorld().getEntitiesWithinAABB(LivingEntity.class, this.fishbones.getBoundingBox().grow(this.followRange, 6.0D, this.followRange));
        LivingEntity target = null;

        if (!entities.isEmpty()) {
            for (LivingEntity entity : entities) {
                double distance = this.fishbones.getDistanceSq(entity);

                if (distance <= this.followRange && this.fishbones.getEntitySenses().canSee(entity)) {
                    if (FishbonesEntity.entityHoldingPrismarine(entity)) {
                        target = entity;
                        break;
                    }
                }
            }
            if (target == null) {
                return false;
            }
            else {
                this.prismarineHolder = target;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        if (!this.prismarineHolder.isAlive() || !FishbonesEntity.entityHoldingPrismarine(this.prismarineHolder)) {
            return false;
        }
        else {
            double distance = this.fishbones.getDistanceSq(this.prismarineHolder);
            return distance <= this.followRange;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.recalcPathTime = 0;
        this.fishbones.setAttackTarget(null);
        this.fishbones.setAggroed(false);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.prismarineHolder = null;
        this.fishbones.getNavigator().clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.fishbones.getLookController().setLookPositionWithEntity(this.prismarineHolder, 10.0F, (float)this.fishbones.getVerticalFaceSpeed());
        if (--this.recalcPathTime <= 0) {
            this.fishbones.getNavigator().tryMoveToEntityLiving(this.prismarineHolder, this.moveSpeed);
        }
    }
}
