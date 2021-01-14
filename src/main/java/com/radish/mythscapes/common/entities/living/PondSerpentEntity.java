package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.misc.Util;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.Nullable;

public class PondSerpentEntity extends AbstractGroupFishEntity {

    public static DataParameter<Boolean> BLUE = EntityDataManager.createKey(PondSerpentEntity.class, DataSerializers.BOOLEAN);

    public PondSerpentEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(BLUE, false);
    }

    private void setSerpentType(Biome biome) {
        boolean bool = !(Util.areBiomesEqual(biome, Biomes.LUKEWARM_OCEAN) || Util.areBiomesEqual(biome, Biomes.WARM_OCEAN));
        this.dataManager.set(BLUE, bool);
    }

    public boolean isBlueVariant() {
        return this.dataManager.get(BLUE);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Blue", this.isBlueVariant());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        boolean isBlue = compound.getBoolean("Blue");
        this.dataManager.set(BLUE, isBlue);
        super.readAdditional(compound);
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
        bucket.getOrCreateChildTag("serpentType").putBoolean("Blue", this.isBlueVariant());
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT tag) {
        Biome biome = world.getBiome(this.getPosition());

        if (tag == null) {
            this.setSerpentType(biome);
        }
        else if (tag.contains("serpentType", 10)) {
            this.dataManager.set(BLUE, tag.getCompound("serpentType").getBoolean("Blue"));
        }
        return super.onInitialSpawn(world, difficulty, reason, spawnData, tag);
    }

    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return AbstractFishEntity.func_234176_m_();
    }

    @Override
    protected ItemStack getFishBucket() {
        return new ItemStack(MythItems.POND_SERPENT_FISH_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }
}
