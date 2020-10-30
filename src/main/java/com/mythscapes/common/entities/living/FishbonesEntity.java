package com.mythscapes.common.entities.living;

import com.mythscapes.common.tags.MythItemTags;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class FishbonesEntity extends MonsterEntity {

    public FishbonesEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 8.0F);
        this.setPathPriority(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathPriority(PathNodeType.DAMAGE_FIRE, -1.0F);
        this.stepHeight = 1.0f;
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FollowPrismarineItemGoal(this, 1.2D));
        this.goalSelector.addGoal(0, new FollowPrismarineHolderGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.35f, true));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new FishbonesNearestAttackableTargetGoal<>(this, PolarBearEntity.class, true));
        this.targetSelector.addGoal(2, new FishbonesNearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new FishbonesNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new FishbonesNearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(4, new FishbonesNearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
    }

    @Override
    public void livingTick() {
        super.livingTick();

        boolean flag = this.isInDaylight();
        if (flag) {
            ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.HEAD);
            if (!itemstack.isEmpty()) {
                if (itemstack.isDamageable()) {
                    itemstack.setDamage(itemstack.getDamage() + this.rand.nextInt(2));
                    if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
                        this.sendBreakAnimation(EquipmentSlotType.HEAD);
                        this.setItemStackToSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                    }
                }
                flag = false;
            }
            if (flag)
                this.setFire(8);
        }
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    public float getWaterSlowDown() {
        return 0.0F;
    }

    public static boolean canFishbonesSpawn(EntityType<? extends FishbonesEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random randomIn) {
        return MonsterEntity.canMonsterSpawnInLight(type, world, reason, pos, randomIn);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        if (super.attackEntityAsMob(target)) {
            if (target instanceof LivingEntity)
                ((LivingEntity)target).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10 * 20, 0));
            return true;
        }
        return false;
    }

    public static boolean entityHoldingPrismarine(@Nullable LivingEntity target) {
        EquipmentSlotType slotType = EquipmentSlotType.MAINHAND;

        if (target == null) {
            return false;
        }
        if (!target.hasItemInSlot(slotType)) {
            slotType = EquipmentSlotType.OFFHAND;
        }
        if (!target.hasItemInSlot(slotType)) {
            return false;
        }
        else {
            Item item = target.getItemStackFromSlot(slotType).getItem();
            return item.isIn(MythItemTags.PRISMARINE);
        }
    }

    public static boolean isItemEntityPrismarine(@Nullable ItemEntity itemEntity) {
        if (itemEntity == null) {
            return false;
        }
        else {
            return MythItemTags.PRISMARINE.contains(itemEntity.getItem().getItem());
        }
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 25.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.28D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
    }

    private static class FishbonesNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

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

    private static class FollowPrismarineHolderGoal extends Goal {

        private final FishbonesEntity fishbones;
        private LivingEntity prismarineHolder;
        private static final int followRange = 24 * 24;
        private final double moveSpeed;
        private int recalcPathTime;

        public FollowPrismarineHolderGoal(FishbonesEntity entity, double speed) {
            this.fishbones = entity;
            this.moveSpeed = speed;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            List<LivingEntity> entities = this.fishbones.getEntityWorld().getEntitiesWithinAABB(LivingEntity.class, this.fishbones.getBoundingBox().grow(followRange, 6.0D, followRange));
            LivingEntity target = null;

            if (!entities.isEmpty()) {
                for (LivingEntity entity : entities) {
                    double distance = this.fishbones.getDistanceSq(entity);

                    if (distance <= followRange && this.fishbones.getEntitySenses().canSee(entity)) {
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
                return distance <= followRange;
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
            this.fishbones.setAttackTarget(null);
            this.fishbones.setAggroed(false);
        }
    }

    private static class FollowPrismarineItemGoal extends Goal {

        private final FishbonesEntity fishbones;
        private ItemEntity itemTarget;
        private static final int followRange = 20 * 20;
        private final double moveSpeed;
        private int recalcPathTime;

        public FollowPrismarineItemGoal(FishbonesEntity entity, double speed) {
            this.fishbones = entity;
            this.moveSpeed = speed;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            List<ItemEntity> entities = this.fishbones.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, this.fishbones.getBoundingBox().grow(followRange, 6.0D, followRange));
            ItemEntity target = null;

            if (!entities.isEmpty()) {
                for (ItemEntity entity : entities) {
                    double distance = this.fishbones.getDistanceSq(entity);

                    if (distance <= followRange && this.fishbones.getEntitySenses().canSee(entity)) {
                        if (FishbonesEntity.isItemEntityPrismarine(entity)) {
                            target = entity;
                            break;
                        }
                    }
                }
                if (target == null) {
                    return false;
                }
                else {
                    this.itemTarget = target;
                    return true;
                }
            }
            return false;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            if (!this.itemTarget.isAlive() || !this.fishbones.getEntitySenses().canSee(this.itemTarget)) {
                return false;
            }
            else {
                Mythscapes.LOGGER.info(this.itemTarget.getItem().getItem().getRegistryName());
                double distance = this.fishbones.getDistanceSq(this.itemTarget);
                return distance <= followRange;
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
            this.itemTarget = null;
            this.fishbones.getNavigator().clearPath();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            this.fishbones.getLookController().setLookPositionWithEntity(this.itemTarget, 10.0F, (float)this.fishbones.getVerticalFaceSpeed());
            if (--this.recalcPathTime <= 0) {
                this.fishbones.getNavigator().tryMoveToEntityLiving(this.itemTarget, this.moveSpeed);
            }
            this.fishbones.setAttackTarget(null);
            this.fishbones.setAggroed(false);
        }
    }

}
