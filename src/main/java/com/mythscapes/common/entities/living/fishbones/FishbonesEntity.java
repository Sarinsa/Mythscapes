package com.mythscapes.common.entities.living.fishbones;

import com.mythscapes.common.tags.MythItemTags;
import com.mythscapes.core.Mythscapes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
        this.goalSelector.addGoal(0, new FollowPrismarineHolderGoal(this, 25.0D, 1.2D));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2f, true));
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

        if (this.getAttackTarget() != null) {
            if (entityHoldingPrismarine(this.getAttackTarget())) {
                this.setAttackTarget(null);
                this.setAggroed(false);
            }
        }
    }

    @Override
    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        this.getAttribute(SWIM_SPEED).setBaseValue(15.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
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

    public static boolean canFishbonesSpawn(EntityType<? extends FishbonesEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random randomIn) {
        return (world.getBlockState(pos).getBlock() == Blocks.WATER && world.getBlockState(pos.up()).getBlock() == Blocks.WATER)
                || MonsterEntity.canMonsterSpawnInLight(type, world, reason, pos, randomIn);
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
}
