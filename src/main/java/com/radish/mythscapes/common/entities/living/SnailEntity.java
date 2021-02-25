package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.goals.MoveToBlockGoalPrecise;
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
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SnailEntity extends CreatureEntity {

    private static final DataParameter<String> SNAIL_TYPE = EntityDataManager.createKey(SnailEntity.class, DataSerializers.STRING);
    private static final DataParameter<Boolean> FROM_BUCKET = EntityDataManager.createKey(SnailEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(SnailEntity.class, DataSerializers.BOOLEAN);

    public int timeUntilShellShed = rand.nextInt(8000) + 3000;

    public SnailEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        this.setPathPriority(PathNodeType.WATER, -1.0f);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new MoveTowardsComposterGoal<>(this, 1.05F, 15, 70));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0F));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    @Override
    protected void registerData() {
        super.registerData();
        // Default to jungle type. All of our own snail types are
        // statically initialized, so this should cause no problems.
        this.dataManager.register(SNAIL_TYPE, SnailTypeRegister.JUNGLE.getName().toString());
        this.dataManager.register(FROM_BUCKET, false);
        this.dataManager.register(HAS_EATEN, false);
    }

    @Override
    public int getMaxAir() {
        return 60;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!world.isRemote && --this.timeUntilShellShed <= 0) {
            ItemStack itemStack = this.getShedDrop(this.rand);

            if (!itemStack.isEmpty()) {
                this.timeUntilShellShed = this.rand.nextInt(8000) + 3000;
                this.entityDropItem(itemStack);
            }
        }
    }

    @Override
    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);

        if (this.isOnGround()) {
            BlockPos below = this.getPosition().down();

            if (this.world.getBlockState(below).isIn(MythBlockTags.SALT_BLOCKS)) {
                this.attackEntityFrom(MythDamageSources.SALT_DEHYDRATION, 1.0f);
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (!this.world.isRemote && this.world.getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES) && this.hasCustomName()) {
            this.world.getPlayers().forEach((playerEntity -> playerEntity.sendMessage(this.getCombatTracker().getDeathMessage(), Util.DUMMY_UUID)));
        }
        super.onDeath(cause);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        // No step sound
    }

    public static boolean canSnailSpawn(EntityType<? extends SnailEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() > 40 && !world.getBlockState(pos.down()).isIn(MythBlockTags.SALT_BLOCKS);
    }

    @Override
    public boolean preventDespawn() {
        return super.preventDespawn() || this.isFromBucket();
    }

    public boolean isFromBucket() {
        return this.dataManager.get(FROM_BUCKET);
    }

    /**
     * @return Whether this snail has eaten from a composter
     *         or not during it's lifespan.
     */
    public boolean hasEaten() {
        return this.dataManager.get(HAS_EATEN);
    }

    public void setHasEaten(boolean hasEaten) {
        this.dataManager.set(HAS_EATEN, hasEaten);
    }

    public void setFromBucket(boolean fromBucket) {
        this.dataManager.set(FROM_BUCKET, fromBucket);
    }

    public void setSnailType(ISnailType snailType) {
        this.dataManager.set(SNAIL_TYPE, snailType.getName().toString());
    }

    public ISnailType getSnailType() {
        return SnailTypeRegister.getFromName(this.dataManager.get(SNAIL_TYPE));
    }

    public ItemStack getShedDrop(Random random) {
        ItemStack itemStack = this.getSnailType().getShedDrop(random);
        return itemStack == null ? new ItemStack(MythItems.SNAIL_SHELL.get()) : itemStack;
    }

    @Override               //processInteract
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        if (player.getHeldItem(hand).getItem() == Items.BUCKET) {
            ItemStack itemStack = player.getHeldItem(hand);

            if (!player.abilities.isCreativeMode) {
                itemStack.shrink(1);
                player.addStat(Stats.ITEM_USED.get(itemStack.getItem()));
            }
            ItemStack snailBucket = new ItemStack(MythItems.SNAIL_BUCKET.get());
            CompoundNBT tag = snailBucket.getOrCreateTag();
            tag.putString("SnailType", this.getSnailType().getName().toString());
            snailBucket.setTag(tag);

            if (itemStack.isEmpty()) {
                player.setHeldItem(hand, snailBucket);
            }
            else {
                if (!player.inventory.addItemStackToInventory(snailBucket)) {
                    player.dropItem(snailBucket, false);
                }
            }
            this.remove();
            return ActionResultType.SUCCESS;
        }
        return super.func_230254_b_(player, hand);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT tag) {
        spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, tag);
        Map<ResourceLocation, List<ISnailType>> spawnList = Mythscapes.getInstance().getSnailTypeRegister().getSpawnBiomes();
        Biome biome = world.getBiome(this.getPosition());

        if (tag != null && tag.contains("SnailType", 8)) {
            this.setSnailType(SnailTypeRegister.getFromName(tag.getString("SnailType")));
        }
        else if (reason == SpawnReason.SPAWN_EGG || reason == SpawnReason.SPAWNER || !(spawnList.containsKey(biome.getRegistryName()))) {
            this.setSnailType(SnailTypeRegister.getRandom());
        }
        else {
            List<ISnailType> snailTypes = spawnList.get(biome.getRegistryName());
            this.setSnailType(snailTypes.get(this.rand.nextInt(snailTypes.size())));
        }
        return spawnData;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("SnailType", this.getSnailType().getName().toString());
        compound.putBoolean("FromBucket", this.isFromBucket());
        compound.putBoolean("HasEaten", this.hasEaten());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        String snailType = compound.getString("SnailType");
        this.setSnailType(SnailTypeRegister.getFromName(snailType));
        this.setFromBucket(compound.getBoolean("FromBucket"));
        this.setHasEaten(compound.getBoolean("HasEaten"));

        super.readAdditional(compound);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 12.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.1D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 3.0D);
    }

    private static class MoveTowardsComposterGoal<T extends SnailEntity> extends MoveToBlockGoal {

        private final int maxEatTimerCount;
        private int eatTimer;

        public MoveTowardsComposterGoal(T snailEntity, double speedIn, int length, int maxEatTimerCount) {
            super(snailEntity, speedIn, length);
            this.maxEatTimerCount = maxEatTimerCount;
        }

        @Override
        public double getTargetDistanceSq() {
            return 0.8D;
        }

        @Override
        protected boolean getIsAboveDestination() {
            return super.getIsAboveDestination() || this.creature.getPosition().equals(this.destinationBlock);
        }

        @Override
        public void tick() {
            super.tick();

            if (this.getIsAboveDestination()) {
                --this.eatTimer;

                if (this.eatTimer <= 0) {
                    SnailEntity entity = (SnailEntity) this.creature;
                    BlockState state = entity.world.getBlockState(this.destinationBlock);

                    int eatAmount = state.get(ComposterBlock.LEVEL) >= 8 ? 2 : 1;

                    if (state.isIn(Blocks.COMPOSTER)) {
                        entity.world.setBlockState(this.destinationBlock, state.with(ComposterBlock.LEVEL, state.get(ComposterBlock.LEVEL) - eatAmount));
                    }
                    entity.setHasEaten(true);
                }
                else if (this.eatTimer % 4 == 1) {
                    this.playEffects();
                }
            }
        }

        private void playEffects() {
            CreatureEntity entity = this.creature;

            entity.world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.NEUTRAL, 0.6F, (entity.getRNG().nextFloat() - entity.getRNG().nextFloat()) * 0.2F + 1.2F);

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
        protected boolean shouldMoveTo(IWorldReader world, BlockPos pos) {
            if (((SnailEntity)this.creature).hasEaten())
                return false;

            if (world.isAirBlock(pos.up()) && (world.getBlockState(pos).isIn(Blocks.COMPOSTER))) {
                return world.getBlockState(pos).get(ComposterBlock.LEVEL) > 0;
            }
            return false;
        }

        @Override
        public void startExecuting() {
            super.startExecuting();
            this.eatTimer = maxEatTimerCount;
        }
    }
}
