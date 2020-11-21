package com.radish.mythscapes.common.entities.misc;

import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

//
//  COPY PASTA FROM BoatEntity.java
//
public class MythBoatEntity extends BoatEntity {

    public enum Type {
        WOLT(MythBlocks.WOLT_PLANKS.get(), "wolt");
        //VIRIDIAN(MythBlocks.VIRIDIAN_PLANKS.get(), "viridian");

        private final String name;
        private final Block block;

        Type(Block block, String name) {
            this.name = name;
            this.block = block;
        }

        public String getName() {
            return this.name;
        }

        public Block asPlank() {
            return this.block;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by it's enum ordinal
         *
         * @return
         */
        public static MythBoatEntity.Type byId(int id) {
            MythBoatEntity.Type[] types = values();
            if (id < 0 || id >= types.length) {
                id = 0;
            }
            return types[id];
        }

        public static MythBoatEntity.Type getTypeFromString(String name) {
            MythBoatEntity.Type[] types = values();

            for (Type type : types) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return types[0];
        }
    }

    public Item getItemBoat() {
        return MythItems.WOLT_BOAT.get();

        /*
        switch(this.getMythBoatType()) {
            case WOLT:
            default:
                return MythItems.WOLT_BOAT.get();
            case VIRIDIAN:
                return MythItems.VIRIDIAN_BOAT.get();
        }
         */
    }

    private static final DataParameter<Integer> MYTH_BOAT_TYPE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);

    public MythBoatEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
    }

    public MythBoatEntity(World world, double x, double y, double z) {
        this(MythEntities.MYTH_BOAT.get(), world);
        this.setPosition(x, y, z);
        this.setMotion(Vector3d.ZERO);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(MYTH_BOAT_TYPE, Type.WOLT.ordinal());
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("MythBoatType", this.getMythBoatType().getName());
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);

        if (compound.contains("MythBoatType", 8)) {
            this.setBoatType(MythBoatEntity.Type.getTypeFromString(compound.getString("BoatType")));
        }
    }

    public void setBoatType(MythBoatEntity.Type boatType) {
        this.dataManager.set(MYTH_BOAT_TYPE, boatType.ordinal());
    }

    public MythBoatEntity.Type getMythBoatType() {
        return MythBoatEntity.Type.byId(this.dataManager.get(MYTH_BOAT_TYPE));
    }

    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
