package com.mythscapes.common.entities;

import com.mythscapes.register.MythEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class LionEntity extends AnimalEntity {

    private static final DataParameter<Boolean> LYING = EntityDataManager.createKey(LionEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> HUNGER = EntityDataManager.createKey(LionEntity.class, DataSerializers.VARINT);
    private static final int maxHunger = 15;

    public LionEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, RavagerEntity.class, 20.0F, 2.0D, 1.5D));
        this.goalSelector.addGoal(2, new LionPanicGoal(this, 2.0D));
        this.goalSelector.addGoal(3, new LionEntity.LionMeleeAttackGoal());
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.8D, 1.0000001E-5F));
        //this.goalSelector.addGoal(7, new LionEntity.LyingGoal(this));
        this.goalSelector.addGoal(12, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this).setCallsForHelp(LionEntity.class));
        this.targetSelector.addGoal(1, new LionNearestAttackableGoal(this, AnimalEntity.class, true));
        this.targetSelector.addGoal(2, new LionNearestAttackableGoal(this, PlayerEntity.class, true));
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LYING, false);
        this.dataManager.register(HUNGER, maxHunger);
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 5;
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.COOKED_BEEF;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return MythEntities.LION.get().create(this.world);
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if (this.isChild())
            return false;
        if (target.getType() == MythEntities.LION.get()) {
            return this.getRevengeTarget() == target;
        }
        return true;
    }

    public boolean isHungry() {
        return true;
    }

    public boolean isLying() {
        return this.dataManager.get(LYING);
    }

    public void setLying(boolean lying) {
        this.dataManager.set(LYING, lying);
    }

    class LionMeleeAttackGoal extends MeleeAttackGoal {

        public LionMeleeAttackGoal() {
            super(LionEntity.this, 1.3D, false);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double reach = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= reach && this.attackTick <= 0) {
                this.attackTick = 20;
                this.attacker.attackEntityAsMob(enemy);
            }
        }
    }

    static class LyingGoal extends Goal {

        LionEntity lionEntity;

        private LyingGoal(LionEntity lionEntity) {
            this.lionEntity = lionEntity;
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        @Override
        public boolean shouldExecute() {
            if (!lionEntity.onGround) {
                return false;
            }
            if (lionEntity.isAggressive()) {
                return false;
            }
            return !lionEntity.isInWaterOrBubbleColumn();
        }

        public void startExecuting() {
            lionEntity.getNavigator().clearPath();
            lionEntity.setLying(true);
        }

        public void resetTask() {
            lionEntity.setLying(false);
        }
    }

    static class LionNearestAttackableGoal extends NearestAttackableTargetGoal<LionEntity> {

        public LionNearestAttackableGoal(LionEntity lionEntity, Class targetClass, boolean checkSight) {
            super(lionEntity, targetClass, checkSight);
        }

        public boolean shouldExecute() {
            return ((LionEntity)this.goalOwner).isHungry() && super.shouldExecute();
        }

        public void startExecuting() {
            this.goalOwner.setAttackTarget(this.nearestTarget);
            super.startExecuting();
        }
    }

    static class LionPanicGoal extends PanicGoal {

        public LionPanicGoal(LionEntity lionEntity, double speedIn) {
            super(lionEntity, speedIn);
        }

        @Override
        public boolean shouldExecute() {
            return this.creature.isChild() && super.shouldExecute();
        }
    }
}
