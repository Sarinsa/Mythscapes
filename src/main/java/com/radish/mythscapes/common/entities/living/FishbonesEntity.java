package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.common.tags.MythItemTags;
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
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class FishbonesEntity extends MonsterEntity {

    public FishbonesEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(PathNodeType.WATER, 8.0F);
        this.maxUpStep = 1.0f;
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FollowPrismarineItemGoal(this, 1.2D));
        this.goalSelector.addGoal(0, new FollowPrismarineHolderGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.35f, true));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 10.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new FishbonesNearestAttackableTargetGoal<>(this, PolarBearEntity.class, true));
        this.targetSelector.addGoal(2, new FishbonesNearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new FishbonesNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new FishbonesNearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(4, new FishbonesNearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
    }

    @Override
    public void aiStep() {
        boolean flag = this.isSunBurnTick();
        if (flag) {
            ItemStack itemstack = this.getItemBySlot(EquipmentSlotType.HEAD);
            if (!itemstack.isEmpty()) {
                if (itemstack.isDamageableItem()) {
                    itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2));
                    if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) {
                        this.broadcastBreakEvent(EquipmentSlotType.HEAD);
                        this.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
                    }
                }
                flag = false;
            }
            if (flag)
                this.setSecondsOnFire(8);
        }
        super.aiStep();
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    public float getWaterSlowDown() {
        return 0.0F;
    }

    public static boolean checkFishbonesSpawnRules(EntityType<? extends FishbonesEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random randomIn) {
        return MonsterEntity.checkMonsterSpawnRules(type, world, reason, pos, randomIn);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity)
                ((LivingEntity)target).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 10 * 20, 0));
            return true;
        }
        return false;
    }

    public static boolean entityHoldingPrismarine(@Nullable LivingEntity target) {
        if (target == null) {
            return false;
        }
        EquipmentSlotType slotType = EquipmentSlotType.MAINHAND;

        if (!target.hasItemInSlot(slotType)) {
            slotType = EquipmentSlotType.OFFHAND;
        }
        if (!target.hasItemInSlot(slotType)) {
            return false;
        }
        else {
            Item item = target.getItemBySlot(slotType).getItem();
            return item.is(MythItemTags.PRISMARINE);
        }
    }

    public static boolean isItemEntityPrismarine(@Nullable ItemEntity itemEntity) {
        return itemEntity != null && MythItemTags.PRISMARINE.contains(itemEntity.getItem().getItem());
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(ForgeMod.SWIM_SPEED.get(), 18.0D)
                .add(Attributes.MAX_HEALTH, 10.0D);
    }

    private static class FishbonesNearestAttackableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

        public FishbonesNearestAttackableTargetGoal(FishbonesEntity fishbones, Class<T> targetClass, boolean checkSight) {
            super(fishbones, targetClass, checkSight);
        }

        public FishbonesNearestAttackableTargetGoal(FishbonesEntity fishbones, Class<T> targetClass, int targetChance, boolean checkSight, boolean nearbyOnly, @Nullable Predicate<LivingEntity> targetPredicate) {
            super(fishbones, targetClass, targetChance, checkSight, nearbyOnly, targetPredicate);
        }

        @Override
        protected void findTarget() {
            LivingEntity entity;

            if (this.targetType != PlayerEntity.class && this.targetType != ServerPlayerEntity.class) {
                entity = this.mob.level.getNearestLoadedEntity(this.targetType, this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), this.getTargetSearchArea(this.getFollowDistance()));
            } else {
                entity = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            }
            if (!FishbonesEntity.entityHoldingPrismarine(entity)) {
                this.target = entity;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !FishbonesEntity.entityHoldingPrismarine(this.target);
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
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            List<LivingEntity> entities = this.fishbones.getCommandSenderWorld().getEntitiesOfClass(LivingEntity.class, this.fishbones.getBoundingBox().inflate(followRange, 6.0D, followRange));
            LivingEntity target = null;

            if (!entities.isEmpty()) {
                for (LivingEntity entity : entities) {
                    double distance = this.fishbones.distanceToSqr(entity);

                    if (distance <= followRange && this.fishbones.getSensing().canSee(entity)) {
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

        @Override
        public boolean canContinueToUse() {
            if (!this.prismarineHolder.isAlive() || !FishbonesEntity.entityHoldingPrismarine(this.prismarineHolder)) {
                return false;
            }
            else {
                double distance = this.fishbones.distanceToSqr(this.prismarineHolder);
                return distance <= followRange;
            }
        }

        @Override
        public void start() {
            this.recalcPathTime = 0;
            this.fishbones.setTarget(null);
            this.fishbones.setAggressive(false);
        }

        @Override
        public void stop() {
            this.prismarineHolder = null;
            this.fishbones.getNavigation().stop();
        }

        @Override
        public void tick() {
            this.fishbones.getLookControl().setLookAt(this.prismarineHolder, 10.0F, (float)this.fishbones.getMaxHeadXRot());
            if (--this.recalcPathTime <= 0) {
                this.fishbones.getNavigation().moveTo(this.prismarineHolder, this.moveSpeed);
            }
            this.fishbones.setTarget(null);
            this.fishbones.setAggressive(false);
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
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            List<ItemEntity> entities = this.fishbones.getCommandSenderWorld().getEntitiesOfClass(ItemEntity.class, this.fishbones.getBoundingBox().inflate(followRange, 6.0D, followRange));
            ItemEntity target = null;

            if (!entities.isEmpty()) {
                for (ItemEntity entity : entities) {
                    double distance = this.fishbones.distanceToSqr(entity);

                    if (distance <= followRange && this.fishbones.getSensing().canSee(entity)) {
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

        @Override
        public boolean canContinueToUse() {
            if (!this.itemTarget.isAlive() || !this.fishbones.getSensing().canSee(this.itemTarget)) {
                return false;
            }
            else {
                double distance = this.fishbones.distanceToSqr(this.itemTarget);
                return distance <= followRange;
            }
        }

        @Override
        public void start() {
            this.recalcPathTime = 0;
            this.fishbones.setTarget(null);
            this.fishbones.setAggressive(false);
        }

        @Override
        public void stop() {
            this.itemTarget = null;
            this.fishbones.getNavigation().stop();
        }

        @Override
        public void tick() {
            this.fishbones.getLookControl().setLookAt(this.itemTarget, 10.0F, (float)this.fishbones.getMaxHeadXRot());
            if (--this.recalcPathTime <= 0) {
                this.fishbones.getNavigation().moveTo(this.itemTarget, this.moveSpeed);
            }
            this.fishbones.setTarget(null);
            this.fishbones.setAggressive(false);
        }
    }

}
