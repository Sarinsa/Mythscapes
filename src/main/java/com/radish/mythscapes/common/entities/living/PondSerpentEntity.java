package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.misc.BiomeUtil;
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
import org.jetbrains.annotations.Nullable;

public class PondSerpentEntity extends AbstractGroupFishEntity {

    public enum Type {
        GREEN("olympian"),
        BLUE("ocean");

        final String name;

        Type(String name) {
            this.name = name;
        }

        private String getName() {
            return this.name;
        }

        private static Type getFromName(String name) {
            for (Type type : values()) {
                if (type.getName().equals(name))
                    return type;
            }
            return BLUE;
        }
    }

    public static DataParameter<String> TYPE = EntityDataManager.createKey(PondSerpentEntity.class, DataSerializers.STRING);

    public PondSerpentEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TYPE, Type.BLUE.getName());
    }

    private void setSerpentType(Biome biome) {
        boolean bool = !(BiomeUtil.areBiomesEqual(biome, Biomes.LUKEWARM_OCEAN) || BiomeUtil.areBiomesEqual(biome, Biomes.WARM_OCEAN));
        this.setSerpentType(bool ? Type.BLUE.getName() : Type.GREEN.getName());
    }

    public void setSerpentType(String type) {
        this.dataManager.set(TYPE, type);
    }

    public Type getSerpentType() {
        return Type.getFromName(this.dataManager.get(TYPE));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("SerpentType", this.getSerpentType().getName());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        String type = compound.getString("SerpentType");
        this.setSerpentType(type);
        super.readAdditional(compound);
    }

    @Override
    protected void setBucketData(ItemStack bucket) {
        bucket.getOrCreateChildTag("serpentType").putString("SerpentType", this.getSerpentType().getName());
    }

    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT tag) {
        Biome biome = world.getBiome(this.getPosition());

        if (tag != null) {
            Mythscapes.LOGGER.info(tag.toString());
        }

        if (tag == null) {
            this.setSerpentType(biome);
        }
        else if (tag.contains("serpentType", 10)) {
            this.setSerpentType(tag.getCompound("serpentType").getString("SerpentType"));
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
