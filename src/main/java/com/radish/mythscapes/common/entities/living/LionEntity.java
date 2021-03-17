package com.radish.mythscapes.common.entities.living;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.tags.MythEntityTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class LionEntity extends AnimalEntity {

    // Appeasing items
    private static final ImmutableList<Item> appeasing = ImmutableList.of(
            Items.BEEF,
            Items.MUTTON,
            Items.CHICKEN,
            Items.RABBIT
    );

    private static final DataParameter<Boolean> LYING = EntityDataManager.defineId(LionEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> HUNGER = EntityDataManager.defineId(LionEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> TIME_APPEASED = EntityDataManager.defineId(LionEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> TIME_MANE_REGROW = EntityDataManager.defineId(LionEntity.class, DataSerializers.INT);

    // Time until the lion should
    // start lying again.
    protected int nextTimeLying = this.newNextTimeLying();
    private static final int maxHunger = 30;

    public static final Predicate<LivingEntity> LION_PREY = (livingEntity) -> (MythEntityTags.LION_PREY.contains(livingEntity.getType()));


    public LionEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LionEntity.LionPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new LionEntity.AvoidGoal<>(this, RavagerEntity.class, 20.0F, 1.2D, 1.5D));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.25f, false));
        this.goalSelector.addGoal(4, new LionEntity.BreedingGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LionEntity.LyingGoal<>(this, (lionEntity) -> lionEntity.isBaby() ? 450 : 320));
        this.goalSelector.addGoal(8, new LionEntity.WaterAvoidingRandomWalkGoal<>(this, 0.8D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(11, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new LionNearestAttackableGoal<>(this, LivingEntity.class, 10, true, false, LION_PREY));
        this.targetSelector.addGoal(3, new LionNearestAttackableGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LYING, false);
        this.entityData.define(HUNGER, maxHunger);
        this.entityData.define(TIME_APPEASED, 0);
        this.entityData.define(TIME_MANE_REGROW, 0);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isBaby() ? 0.6F : 1.20F;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isLying()) {
            this.jumping = false;
            this.xxa = 0.0f;
            this.zza = 0.0f;
        }
        else {
            --this.nextTimeLying;
        }

        if (this.getHunger() > 0 && this.random.nextInt(130) == 0) {
            this.setHunger(this.getHunger() - 1);
        }
        if (this.getTimeAppeased() > 0) {
            this.setTimeAppeased(this.getTimeAppeased() - 1);
        }
        if (this.getTimeManeRegrow() > 0) {
            this.setTimeManeRegrow(this.getTimeManeRegrow() - 1);
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (appeasing.contains(itemstack.getItem()) && !this.isAppeased()) {
            player.playSound(SoundEvents.CAT_EAT, 1.0F, 0.4F);
            if (!player.abilities.instabuild)
                itemstack.shrink(1);
            this.newTimeAppeased();
            return ActionResultType.SUCCESS;
        }
        else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public void killed(ServerWorld serverWorld, LivingEntity entityLiving) {
        super.killed(serverWorld, entityLiving);

        if (MythEntityTags.LION_PREY.contains(entityLiving.getType()))
            this.setHunger(maxHunger);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damage) {
        if ((damageSource.getEntity() != null && damageSource.getEntity() instanceof LivingEntity) && this.isBaby()) {
            LivingEntity attacker = (LivingEntity) damageSource.getEntity();

            List<LivingEntity> nearbyLions = this.level.getEntitiesOfClass(LionEntity.class, new AxisAlignedBB(this.blockPosition()).inflate(14, 5, 14), (entity) -> !entity.isBaby());

            if (!nearbyLions.isEmpty()) {
                nearbyLions.forEach((entity) -> {
                    entity.setLastHurtByMob(attacker);
                });
            }
        }
        return super.hurt(damageSource, damage);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Lying", this.isLying());
        compound.putInt("Hunger", this.getHunger());
        compound.putInt("TimeAppeased", this.getTimeAppeased());
        compound.putInt("TimeManeRegrow", this.getTimeManeRegrow());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        this.setLying(compound.getBoolean("Lying"));
        this.setHunger(compound.getInt("Hunger"));
        this.setTimeAppeased(compound.getInt("TimeAppeased"));
        this.setTimeManeRegrow(compound.getInt("TimeManeRegrow"));

        super.readAdditionalSaveData(compound);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.getItem() == Items.COOKED_BEEF;
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return MythEntities.LION.get().create(this.level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    public void newTimeAppeased() {
        this.setTimeAppeased(8000 + this.random.nextInt(3000));
    }

    public void newTimeManeRegrow() {
        this.setTimeManeRegrow(8000 + this.random.nextInt(2000));
    }

    protected int newNextTimeLying() {
        return this.random.nextInt(200) + 100;
    }

    public void setHunger(int hunger) {
        this.entityData.set(HUNGER, hunger);
    }

    public int getHunger() {
        return this.entityData.get(HUNGER);
    }

    public boolean isHungry() {
        return this.getHunger() <= 0;
    }

    public int getTimeAppeased() {
        return this.entityData.get(TIME_APPEASED);
    }

    public void setTimeAppeased(int ticks) {
        this.entityData.set(TIME_APPEASED, ticks);
    }

    public boolean isAppeased() {
        return this.entityData.get(TIME_APPEASED) > 0;
    }

    public int getTimeManeRegrow() {
        return this.entityData.get(TIME_MANE_REGROW);
    }

    public void setTimeManeRegrow(int ticks) {
        this.entityData.set(TIME_MANE_REGROW, ticks);
    }

    public boolean hasMane() {
        return  !this.isBaby() && this.getTimeManeRegrow() <= 0;
    }

    public boolean isLying() {
        return this.entityData.get(LYING);
    }

    public void setLying(boolean lying) {
        this.entityData.set(LYING, lying);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return CreatureEntity.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    private class LionPanicGoal extends PanicGoal {

        public LionPanicGoal(LionEntity lionEntity, double speedIn) {
            super(lionEntity, speedIn);
        }

        @Override
        public boolean canUse() {
            return LionEntity.this.isBaby() && super.canUse();
        }

        @Override
        public void start() {
            LionEntity.this.setLying(false);
            super.start();
        }
    }

    private class LionNearestAttackableGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

        public LionNearestAttackableGoal(LionEntity lionEntity, Class<T> targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, @Nullable Predicate<LivingEntity> targetPredicate) {
            super(lionEntity, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
        }

        public LionNearestAttackableGoal(LionEntity lionEntity, Class<T> targetClass, boolean checkSight) {
            super(lionEntity, targetClass, checkSight);
        }

        @Override
        public boolean canContinueToUse() {
            if (PlayerEntity.class.isAssignableFrom(this.targetType)) {
                return !LionEntity.this.isAppeased();
            }
            else {
                return super.canContinueToUse();
            }
        }

        public boolean canUse() {
            if (super.canUse() && LionEntity.this.isHungry()) {
                if (this.target instanceof PlayerEntity) {
                    return !LionEntity.this.isAppeased();
                }
                else {
                    return true;
                }
            }
            return false;
        }
    }

    private static class LyingGoal<T extends LionEntity> extends Goal {

        private final T lionEntity;
        private final ToIntFunction<T> executionChanceFunction;
        private int timeLying;

        public LyingGoal(T lionEntity, ToIntFunction<T> executionChanceFunction) {
            this.lionEntity = lionEntity;
            this.executionChanceFunction = executionChanceFunction;
        }

        @Override
        public boolean canContinueToUse() {
            return stillLying() && !lionEntity.isHungry() && lionEntity.isOnGround() && !lionEntity.isAggressive() && !lionEntity.isInWaterOrBubble();
        }

        private boolean stillLying() {
            return lionEntity.isLying() && this.timeLying > 0;
        }

        @Override
        public void tick() {
            if (this.stillLying()) {
                --this.timeLying;
            }
        }

        @Override
        public boolean canUse() {
            int executionChance = this.executionChanceFunction.applyAsInt(lionEntity);

            if (lionEntity.nextTimeLying <= 0 && lionEntity.getRandom().nextInt(executionChance) == 0) {
                return !lionEntity.isHungry() && lionEntity.isOnGround() && !lionEntity.isAggressive() && !lionEntity.isInWaterOrBubble();
            }
            return false;
        }

        @Override
        public void start() {
            this.timeLying = this.lionEntity.getRandom().nextInt(300) + 400;
            lionEntity.getNavigation().stop();
            lionEntity.setLying(true);
            lionEntity.setAggressive(false);
        }

        @Override
        public void stop() {
            this.timeLying = 0;
            lionEntity.newNextTimeLying();
            lionEntity.setLying(false);
            lionEntity.getNavigation().stop();
        }
    }

    private class BreedingGoal extends BreedGoal {

        public BreedingGoal(LionEntity lionEntity, double speedIn) {
            super(lionEntity, speedIn);
        }

        @Override
        public boolean canUse() {
            return !LionEntity.this.isAggressive() && super.canUse();
        }

        @Override
        public void start() {
            LionEntity.this.setLying(false);
            super.start();
        }
    }

    private class WaterAvoidingRandomWalkGoal<T extends LionEntity> extends WaterAvoidingRandomWalkingGoal {

        public WaterAvoidingRandomWalkGoal(T creature, double speedIn) {
            super(creature, speedIn);
        }

        @Override
        public boolean canUse() {
            return !LionEntity.this.isLying() && super.canUse();
        }
    }

    private class AvoidGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

        public AvoidGoal(LionEntity lionEntity, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(lionEntity, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        }

        @Override
        public void start() {
            LionEntity.this.setLying(false);
            super.start();
        }
    }
}
