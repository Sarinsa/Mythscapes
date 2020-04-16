package com.mythscapes.common.entities;

import com.mythscapes.register.MythItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;

public class PondSerpentEntity extends AbstractGroupFishEntity {

    public static DataParameter<Boolean> GREEN = EntityDataManager.createKey(PondSerpentEntity.class, DataSerializers.BOOLEAN);

    public PondSerpentEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GREEN, false);
    }

    private void setSerpentType(boolean bool) {
        this.dataManager.set(GREEN, bool);
    }


    public boolean isGreenVariant() {
        return this.dataManager.get(GREEN);
    }


    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Green", this.isGreenVariant());
    }


    public void readAdditional(CompoundNBT compound) {
        boolean isGreen = compound.getBoolean("Green");
        this.setSerpentType(isGreen);
        super.readAdditional(compound);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        Biome biome = world.getBiome(new BlockPos(this));
        this.setSerpentType(biome.getCategory() == Biome.Category.SWAMP);
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
