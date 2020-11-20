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

    private static final DataParameter<Integer> TIME_SINCE_HIT;
    private static final DataParameter<Integer> FORWARD_DIRECTION;
    private static final DataParameter<Float> DAMAGE_TAKEN;
    private static final DataParameter<Integer> BOAT_TYPE;
    private static final DataParameter<Boolean> LEFT_PADDLE;
    private static final DataParameter<Boolean> RIGHT_PADDLE;
    private static final DataParameter<Integer> ROCKING_TICKS;

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
        this.dataManager.register(TIME_SINCE_HIT, 0);
        this.dataManager.register(FORWARD_DIRECTION, 1);
        this.dataManager.register(DAMAGE_TAKEN, 0.0F);
        this.dataManager.register(BOAT_TYPE, Type.WOLT.ordinal());
        this.dataManager.register(LEFT_PADDLE, false);
        this.dataManager.register(RIGHT_PADDLE, false);
        this.dataManager.register(ROCKING_TICKS, 0);
    }

    protected void writeAdditional(CompoundNBT compound) {
        compound.putString("BoatType", this.getMythBoatType().getName());
    }

    protected void readAdditional(CompoundNBT compound) {
        if (compound.contains("BoatType", 8)) {
            this.setBoatType(MythBoatEntity.Type.getTypeFromString(compound.getString("BoatType")));
        }

    }

    public void setBoatType(MythBoatEntity.Type boatType) {
        this.dataManager.set(BOAT_TYPE, boatType.ordinal());
    }

    public MythBoatEntity.Type getMythBoatType() {
        return MythBoatEntity.Type.byId(this.dataManager.get(BOAT_TYPE));
    }


    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    static {
        TIME_SINCE_HIT = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
        FORWARD_DIRECTION = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
        DAMAGE_TAKEN = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.FLOAT);
        BOAT_TYPE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
        LEFT_PADDLE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.BOOLEAN);
        RIGHT_PADDLE = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.BOOLEAN);
        ROCKING_TICKS = EntityDataManager.createKey(MythBoatEntity.class, DataSerializers.VARINT);
    }
}
