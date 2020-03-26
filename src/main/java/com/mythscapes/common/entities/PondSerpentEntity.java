package com.mythscapes.common.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PondSerpentEntity extends AbstractGroupFishEntity {
    public Type type;

    public PondSerpentEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        Biome biome = world.getBiome(new BlockPos(this));
        switch (biome.getCategory()) {
            case SWAMP:
                type = Type.OLYMPIAN_MOUNTAINS;
                break;
            case OCEAN:
            default:
                type = Type.OCEAN;
                break;
        }

    }

    @Nonnull
    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(Items.COD_BUCKET);
    }

    @Nonnull
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    public enum Type {
        OCEAN,
        OLYMPIAN_MOUNTAINS
    }
}
