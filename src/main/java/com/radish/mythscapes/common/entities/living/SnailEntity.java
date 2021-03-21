package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.misc.MythDamageSources;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.tags.MythBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SnailEntity extends CreatureEntity {

    private static final DataParameter<String> SNAIL_TYPE = EntityDataManager.defineId(SnailEntity.class, DataSerializers.STRING);
    private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.defineId(SnailEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.defineId(SnailEntity.class, DataSerializers.BOOLEAN);

    public int timeUntilShellShed = random.nextInt(8000) + 3000;

    public SnailEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        this.setPathfindingMalus(PathNodeType.WATER, -1.0f);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new MoveTowardsComposterGoal<>(this, 1.05F, 15, 70));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // Default to jungle type. All of our own snail types are
        // statically initialized, so this should cause no problems.
        this.entityData.define(SNAIL_TYPE, SnailTypeRegister.JUNGLE.getName().toString());
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(HAS_EATEN, false);
    }

    @Override
    public int getMaxAirSupply() {
        return 60;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level.isClientSide && --this.timeUntilShellShed <= 0) {
            ItemStack itemStack = this.getShedDrop(this.random);

            if (!itemStack.isEmpty()) {
                this.timeUntilShellShed = this.random.nextInt(8000) + 3000;
                this.spawnAtLocation(itemStack);
            }
        }
    }

    @Override
    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);

        if (this.isOnGround()) {
            BlockPos below = this.blockPosition().below();

            if (this.level.getBlockState(below).is(MythBlockTags.SALT_BLOCKS)) {
                this.hurt(MythDamageSources.SALT_DEHYDRATION, 1.0f);
            }
        }
    }

    @Override
    public void die(DamageSource cause) {
        if (!this.level.isClientSide && this.level.getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.hasCustomName()) {
            this.level.players().forEach((playerEntity -> playerEntity.sendMessage(this.getCombatTracker().getDeathMessage(), Util.NIL_UUID)));
        }
        super.die(cause);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        // No step sound
    }

    public static boolean checkPygmySnailSpawnRules(EntityType<? extends SnailEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() > 40 && !world.getBlockState(pos.below()).is(MythBlockTags.SALT_BLOCKS);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.isFromBucket();
    }

    public boolean isFromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    /**
     * @return Whether this snail has eaten from a composter
     *         or not during it's lifespan.
     */
    public boolean hasEaten() {
        return this.entityData.get(HAS_EATEN);
    }

    public void setHasEaten(boolean hasEaten) {
        this.entityData.set(HAS_EATEN, hasEaten);
    }

    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }

    public void setSnailType(ISnailType snailType) {
        this.entityData.set(SNAIL_TYPE, snailType.getName().toString());
    }

    public ISnailType getSnailType() {
        return SnailTypeRegister.INSTANCE.getFromName(this.entityData.get(SNAIL_TYPE));
    }

    public ItemStack getShedDrop(Random random) {
        ItemStack itemStack = this.getSnailType().getShedDrop(random);
        return itemStack == null ? new ItemStack(MythItems.SNAIL_SHELL.get()) : itemStack;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (player.getItemInHand(hand).getItem() == Items.BUCKET) {
            ItemStack itemStack = player.getItemInHand(hand);

            if (!player.abilities.instabuild) {
                itemStack.shrink(1);
                player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            }
            ItemStack snailBucket = new ItemStack(MythItems.SNAIL_BUCKET.get());
            CompoundNBT tag = snailBucket.getOrCreateTag();
            tag.putString("SnailType", this.getSnailType().getName().toString());
            snailBucket.setTag(tag);

            if (itemStack.isEmpty()) {
                player.setItemInHand(hand, snailBucket);
            }
            else {
                if (!player.inventory.add(snailBucket)) {
                    player.drop(snailBucket, false);
                }
            }
            this.remove();
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT tag) {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, tag);
        Biome biome = world.getBiome(this.blockPosition());

        if (tag != null && tag.contains("SnailType", 8)) {
            this.setSnailType(SnailTypeRegister.INSTANCE.getFromName(tag.getString("SnailType")));
        }
        else if (reason == SpawnReason.SPAWN_EGG || reason == SpawnReason.SPAWNER) {
            this.setSnailType(SnailTypeRegister.INSTANCE.getRandom());
        }
        else {
            ISnailType snailType = SnailTypeRegister.INSTANCE.getWeightedForBiome(biome);
            this.setSnailType(snailType);
        }
        return spawnData;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("SnailType", this.getSnailType().getName().toString());
        compound.putBoolean("FromBucket", this.isFromBucket());
        compound.putBoolean("HasEaten", this.hasEaten());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        String snailType = compound.getString("SnailType");
        this.setSnailType(SnailTypeRegister.INSTANCE.getFromName(snailType));
        this.setFromBucket(compound.getBoolean("FromBucket"));
        this.setHasEaten(compound.getBoolean("HasEaten"));

        super.readAdditionalSaveData(compound);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.1D)
                .add(Attributes.MAX_HEALTH, 3.0D);
    }

    private static class MoveTowardsComposterGoal<T extends SnailEntity> extends MoveToBlockGoal {

        private final int maxEatTimerCount;
        private int eatTimer;

        public MoveTowardsComposterGoal(T snailEntity, double speedIn, int length, int maxEatTimerCount) {
            super(snailEntity, speedIn, length);
            this.maxEatTimerCount = maxEatTimerCount;
        }

        @Override
        public double acceptedDistance() {
            return 0.8D;
        }

        @Override
        protected boolean isReachedTarget() {
            return super.isReachedTarget() || this.mob.blockPosition().equals(this.blockPos);
        }

        @Override
        public void tick() {
            super.tick();

            if (this.isReachedTarget()) {
                --this.eatTimer;

                if (this.eatTimer <= 0) {
                    SnailEntity entity = (SnailEntity) this.mob;
                    BlockState state = entity.level.getBlockState(this.blockPos);

                    int eatAmount = state.getValue(ComposterBlock.LEVEL) >= 8 ? 2 : 1;

                    if (state.is(Blocks.COMPOSTER)) {
                        entity.level.setBlockAndUpdate(this.blockPos, state.setValue(ComposterBlock.LEVEL, state.getValue(ComposterBlock.LEVEL) - eatAmount));
                    }
                    entity.setHasEaten(true);
                }
                else if (this.eatTimer % 4 == 1) {
                    this.playEffects();
                }
            }
        }

        private void playEffects() {
            CreatureEntity entity = this.mob;

            entity.level.playSound(null, entity.blockPosition(), SoundEvents.GENERIC_EAT, SoundCategory.NEUTRAL, 0.6F, (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F + 1.2F);

            // Maybe do this, maybe not, we'll see
            /*
            if (!entity.world.isRemote) {
                ServerWorld serverWorld = (ServerWorld) entity.world;
                Vector3d vector3d = (new Vector3d(((double)entity.getRNG().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotatePitch(-entity.rotationPitch * ((float)Math.PI / 180F)).rotateYaw(-entity.rotationYaw * ((float)Math.PI / 180F));
                serverWorld.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.PODZOL.getDefaultState()).setPos(this.destinationBlock),
                        entity.getPosX() + entity.getLookVec().x / 2.0D,
                        entity.getPosY(),
                        entity.getPosZ() + entity.getLookVec().z / 2.0D,
                        vector3d.x,
                        vector3d.y + 0.05D,
                        vector3d.z);
            }

             */
        }

        @Override
        protected boolean isValidTarget(IWorldReader world, BlockPos pos) {
            if (((SnailEntity)this.mob).hasEaten())
                return false;

            if (world.isEmptyBlock(pos.above()) && (world.getBlockState(pos).is(Blocks.COMPOSTER))) {
                return world.getBlockState(pos).getValue(ComposterBlock.LEVEL) > 0;
            }
            return false;
        }

        @Override
        public void start() {
            super.start();
            this.eatTimer = maxEatTimerCount;
        }
    }
}
