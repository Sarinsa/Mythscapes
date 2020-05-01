package com.mythscapes.common.entities.living.pond_serpent;

import com.mythscapes.register.MythItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PondSerpentEntity extends AbstractGroupFishEntity {

    public static DataParameter<Boolean> BLUE = EntityDataManager.createKey(PondSerpentEntity.class, DataSerializers.BOOLEAN);

    public PondSerpentEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(BLUE, false);
    }

    private void setSerpentType(Biome biome) {
        boolean bool = !(biome == Biomes.LUKEWARM_OCEAN || biome == Biomes.WARM_OCEAN);
        this.dataManager.set(BLUE, bool);
    }

    public boolean isBlueVariant() {
        return this.dataManager.get(BLUE);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Blue", this.isBlueVariant());
    }

    public void readAdditional(CompoundNBT compound) {
        boolean isBlue = compound.getBoolean("Blue");
        this.dataManager.set(BLUE, isBlue);
        super.readAdditional(compound);
    }

    @Override
    public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
        Biome biome = world.getBiome(this.getPosition());
        this.setSerpentType(biome);

        return super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    }

    @Nonnull
    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(MythItems.POND_SERPENT_FISH_BUCKET.get());
    }

    @Nonnull
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }
}
