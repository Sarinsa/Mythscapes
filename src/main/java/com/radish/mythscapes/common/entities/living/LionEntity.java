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

    private static final DataParameter<Boolean> LYING = EntityDataManager.createKey(LionEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> HUNGER = EntityDataManager.createKey(LionEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> TIME_APPEASED = EntityDataManager.createKey(LionEntity.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> TIME_MANE_REGROW = EntityDataManager.createKey(LionEntity.class, DataSerializers.VARINT);

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
        this.goalSelector.addGoal(6, new LionEntity.LyingGoal<>(this, (lionEntity) -> lionEntity.isChild() ? 450 : 320));
        this.goalSelector.addGoal(8, new LionEntity.WaterAvoidingRandomWalkGoal<>(this, 0.8D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(11, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new LionNearestAttackableGoal<>(this, LivingEntity.class, 10, true, false, LION_PREY));
        this.targetSelector.addGoal(3, new LionNearestAttackableGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LYING, false);
        this.dataManager.register(HUNGER, maxHunger);
        this.dataManager.register(TIME_APPEASED, 0);
        this.dataManager.register(TIME_MANE_REGROW, 0);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.6F : 1.20F;
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (this.isLying()) {
            this.isJumping = false;
            this.moveStrafing = 0.0f;
            this.moveForward = 0.0f;
        }
        else {
            --this.nextTimeLying;
        }

        if (this.getHunger() > 0 && this.rand.nextInt(130) == 0) {
            this.setHunger(this.getHunger() - 1);
        }
        if (this.getTimeAppeased() > 0) {
            this.setTimeAppeased(this.getTimeAppeased() - 1);
        }
        if (this.getTimeManeRegrow() > 0) {
            this.setTimeManeRegrow(this.getTimeManeRegrow() - 1);
        }
    }

    @Override               //processInteract
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (appeasing.contains(itemstack.getItem()) && !this.isAppeased()) {
            player.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 0.4F);
            if (!player.abilities.isCreativeMode)
                itemstack.shrink(1);
            this.newTimeAppeased();
            return ActionResultType.SUCCESS;
        }
        else {
            return super.func_230254_b_(player, hand);
        }
    }

    @Override   //onKillEntity
    public void func_241847_a(ServerWorld serverWorld, LivingEntity entityLiving) {
        super.func_241847_a(serverWorld, entityLiving);

        if (MythEntityTags.LION_PREY.contains(entityLiving.getType()))
            this.setHunger(maxHunger);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if ((damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof LivingEntity) && this.isChild()) {
            LivingEntity attacker = (LivingEntity) damageSource.getTrueSource();

            List<LivingEntity> nearbyLions = this.world.getLoadedEntitiesWithinAABB(LionEntity.class, new AxisAlignedBB(this.getPosition()).grow(14, 5, 14), (entity) -> !entity.isChild());

            if (!nearbyLions.isEmpty()) {
                nearbyLions.forEach((entity) -> {
                    entity.setRevengeTarget(attacker);
                });
            }
        }
        return super.attackEntityFrom(damageSource, damage);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Lying", this.isLying());
        compound.putInt("Hunger", this.getHunger());
        compound.putInt("TimeAppeased", this.getTimeAppeased());
        compound.putInt("TimeManeRegrow", this.getTimeManeRegrow());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        this.setLying(compound.getBoolean("Lying"));
        this.setHunger(compound.getInt("Hunger"));
        this.setTimeAppeased(compound.getInt("TimeAppeased"));
        this.setTimeManeRegrow(compound.getInt("TimeManeRegrow"));

        super.readAdditional(compound);
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.COOKED_BEEF;
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return MythEntities.LION.get().create(this.world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    public void newTimeAppeased() {
        this.setTimeAppeased(8000 + this.rand.nextInt(3000));
    }

    public void newTimeManeRegrow() {
        this.setTimeManeRegrow(8000 + this.rand.nextInt(2000));
    }

    protected int newNextTimeLying() {
        return this.getRNG().nextInt(200) + 100;
    }

    public void setHunger(int hunger) {
        this.dataManager.set(HUNGER, hunger);
    }

    public int getHunger() {
        return this.dataManager.get(HUNGER);
    }

    public boolean isHungry() {
        return this.getHunger() <= 0;
    }

    public int getTimeAppeased() {
        return this.dataManager.get(TIME_APPEASED);
    }

    public void setTimeAppeased(int ticks) {
        this.dataManager.set(TIME_APPEASED, ticks);
    }

    public boolean isAppeased() {
        return this.dataManager.get(TIME_APPEASED) > 0;
    }

    public int getTimeManeRegrow() {
        return this.dataManager.get(TIME_MANE_REGROW);
    }

    public void setTimeManeRegrow(int ticks) {
        this.dataManager.set(TIME_MANE_REGROW, ticks);
    }

    public boolean hasMane() {
        return  !this.isChild() && this.getTimeManeRegrow() <= 0;
    }

    public boolean isLying() {
        return this.dataManager.get(LYING);
    }

    public void setLying(boolean lying) {
        this.dataManager.set(LYING, lying);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 15.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    private class LionPanicGoal extends PanicGoal {

        public LionPanicGoal(LionEntity lionEntity, double speedIn) {
            super(lionEntity, speedIn);
        }

        @Override
        public boolean shouldExecute() {
            return LionEntity.this.isChild() && super.shouldExecute();
        }

        @Override
        public void startExecuting() {
            LionEntity.this.setLying(false);
            super.startExecuting();
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
        public boolean shouldContinueExecuting() {
            if (PlayerEntity.class.isAssignableFrom(this.targetClass)) {
                return !LionEntity.this.isAppeased();
            }
            else {
                return super.shouldContinueExecuting();
            }
        }

        public boolean shouldExecute() {
            if (super.shouldExecute() && LionEntity.this.isHungry()) {
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
        public boolean shouldContinueExecuting() {
            return stillLying() && !lionEntity.isHungry() && lionEntity.isOnGround() && !lionEntity.isAggressive() && !lionEntity.isInWaterOrBubbleColumn();
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
        public boolean shouldExecute() {
            int executionChance = this.executionChanceFunction.applyAsInt(lionEntity);

            if (lionEntity.nextTimeLying <= 0 && lionEntity.getRNG().nextInt(executionChance) == 0) {
                return !lionEntity.isHungry() && lionEntity.isOnGround() && !lionEntity.isAggressive() && !lionEntity.isInWaterOrBubbleColumn();
            }
            return false;
        }

        @Override
        public void startExecuting() {
            this.timeLying = this.lionEntity.getRNG().nextInt(300) + 400;
            lionEntity.getNavigator().clearPath();
            lionEntity.setLying(true);
            lionEntity.setAggroed(false);
        }

        @Override
        public void resetTask() {
            this.timeLying = 0;
            lionEntity.newNextTimeLying();
            lionEntity.setLying(false);
            lionEntity.getNavigator().clearPath();
        }
    }

    private class BreedingGoal extends BreedGoal {

        public BreedingGoal(LionEntity lionEntity, double speedIn) {
            super(lionEntity, speedIn);
        }

        @Override
        public boolean shouldExecute() {
            return !LionEntity.this.isAggressive() && super.shouldExecute();
        }

        @Override
        public void startExecuting() {
            LionEntity.this.setLying(false);
            super.startExecuting();
        }
    }

    private class WaterAvoidingRandomWalkGoal<T extends LionEntity> extends WaterAvoidingRandomWalkingGoal {

        public WaterAvoidingRandomWalkGoal(T creature, double speedIn) {
            super(creature, speedIn);
        }

        @Override
        public boolean shouldExecute() {
            return !LionEntity.this.isLying() && super.shouldExecute();
        }
    }

    private class AvoidGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

        public AvoidGoal(LionEntity lionEntity, Class<T> classToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(lionEntity, classToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        }

        @Override
        public void startExecuting() {
            LionEntity.this.setLying(false);
            super.startExecuting();
        }
    }
}
