package com.mythscapes.common.entities.living;

import com.mythscapes.register.MythItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class SnailEntity extends CreatureEntity {

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

        public Rarity getRarity() {
            return this.rarity;
        }

        public ItemStack getShedDrop() {
            return this.shedDrop.get();
        }

        public String toString() {
            return this.name;
        }

        @Nonnull
        public static SnailType getFromName(String name) {
            SnailType[] types = values();
            for (SnailType type : types) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return SnailType.JUNGLE;
        }

        @Nullable
        public static SnailType getFromNameOrNull(String name) {
            SnailType[] types = values();
            for (SnailType type : types) {
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


    private static final DataParameter<String> SNAIL_TYPE = EntityDataManager.createKey(SnailEntity.class, DataSerializers.STRING);
    // Used for shell shedding
    public int timeUntilShellShed = rand.nextInt(5000) + 2000;

    public SnailEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0f));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 10.0F));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(SNAIL_TYPE, SnailType.JUNGLE.getName());
    }

    public int getMaxAir() {
        return 60;
    }

    public void livingTick() {
        super.livingTick();
        if (!world.isRemote && --this.timeUntilShellShed <= 0) {
            this.timeUntilShellShed = rand.nextInt(5000) + 2000;
            this.entityDropItem(getShedDrop());
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        // No step sound
    }

    public static boolean canSnailSpawn(EntityType<? extends SnailEntity> type, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.GRASS && pos.getY() > 50 && world.getLightSubtracted(pos, 0) > 8;
    }

    public void setSnailType(SnailType snailType) {
        this.dataManager.set(SNAIL_TYPE, snailType.getName());
    }

    public SnailType getSnailType() {
        return SnailType.getFromName(this.dataManager.get(SNAIL_TYPE));
    }

    public ItemStack getShedDrop() {
        return this.getSnailType().getShedDrop();
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT tag) {
        spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, tag);

        if (tag != null && tag.contains("SnailType", 8)) {
            this.setSnailType(SnailType.getFromName(tag.getString("SnailType")));
        }
        else if (reason == SpawnReason.SPAWN_EGG || reason == SpawnReason.SPAWNER) {
            this.setSnailType(SnailType.getRandom());
        }
        // If no data tag is provided, pick a snail type depending on the biome.
        else {
            // TODO - Need to figure out how to perform biome checks
            this.setSnailType(SnailType.getRandom());

            /*
            Biome biome = world.getBiome(this.getPosition());

            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
                this.setSnailType(SnailType.MUSHROOM);

            else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SWAMP))
                this.setSnailType(SnailType.SWAMP);

            else if (biome == Biomes.FLOWER_FOREST)
                this.setSnailType(SnailType.FLOWER_FOREST);

            else if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH))
                this.setSnailType(SnailType.JEWELED);

            else {
                this.setSnailType(SnailType.JUNGLE);
            }
             */
        }
        return spawnData;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("SnailType", this.getSnailType().getName());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        String snailType = compound.getString("SnailType");
        this.setSnailType(SnailType.getFromName(snailType));

        super.readAdditional(compound);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 12.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.08D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 2.0D);
    }
}
