package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.misc.MythDamageSources;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.tags.MythBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SnailEntity extends CreatureEntity {

    // Old snail type handling. Will likely never be used again, but I will keep it here for now.

    /*
    public enum SnailType {
        MUSHROOM("mushroom", Rarity.COMMON),
        SWAMP("swamp", Rarity.COMMON),
        FLOWER_FOREST("flower_forest", Rarity.COMMON),
        ROOFED_FOREST("roofed_forest", Rarity.COMMON),
        JUNGLE("jungle", Rarity.COMMON),
        BOG("bog", Rarity.COMMON),
        JEWELED("jeweled", Rarity.EPIC, new ItemStack(MythItems.BEJEWELED_SNAIL_SHELL.get()));

        private final String name;
        private final Rarity rarity;
        private final Supplier<ItemStack> shedDrop;

        SnailType(String name, Rarity rarity) {
            this.name = name;
            this.rarity = rarity;
            this.shedDrop = () -> new ItemStack(MythItems.SNAIL_SHELL.get());
        }

        SnailType(String name, Rarity rarity, ItemStack shedDrop) {
            this.name = name;
            this.rarity = rarity;
            this.shedDrop = () -> shedDrop;
        }

        public String getName() {
            return this.name;
        }

        public String getTranslationKey() {
            return "snail_type." + Mythscapes.MODID + "." + this.getName();
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public ItemStack getShedDrop() {
            return this.shedDrop.get();
        }

        public String toString() {
            return this.name;
        }

        public static SnailType getFromName(String name) {
            for (SnailType type : values()) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return SnailType.JUNGLE;
        }

        @Nullable
        public static SnailType getFromNameOrNull(@Nullable String name) {
            if (name == null || name.isEmpty())
                return null;

            for (SnailType type : values()) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return null;
        }

        public static SnailType getRandom() {
            SnailType[] types = values();
            return types[new Random().nextInt(types.length)];
        }
    }

     */


    private static final DataParameter<String> SNAIL_TYPE = EntityDataManager.createKey(SnailEntity.class, DataSerializers.STRING);
    // Used for shell shedding
    public int timeUntilShellShed = rand.nextInt(8000) + 3000;

    public SnailEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
        this.setPathPriority(PathNodeType.WATER, -1.0f);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0f));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    @Override
    protected void registerData() {
        super.registerData();
        // Default to jungle type. All of our own snail types are
        // statically initialized, thus this should cause no problems.
        this.dataManager.register(SNAIL_TYPE, "mythscapes:jungle");
    }

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
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        String snailType = compound.getString("SnailType");
        this.setSnailType(SnailTypeRegister.getFromName(snailType));

        super.readAdditional(compound);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 12.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.08D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 3.0D);
    }
}
