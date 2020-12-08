package com.radish.mythscapes.common.entities.misc;

import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.function.Supplier;

//
//  COPY PASTA FROM BoatEntity.java
//
public class MythBoatEntity extends BoatEntity {

    public enum Type {
        WOLT(MythBlocks.WOLT_PLANKS, MythItems.WOLT_BOAT, "wolt");
        //VIRIDIAN(MythBlocks.VIRIDIAN_PLANKS.get(), "viridian");

        private final String name;
        private final Supplier<Block> block;
        private final Supplier<Item> boatItem;

        Type(Supplier<Block> block, Supplier<Item> boatItem, String name) {
            this.name = name;
            this.block = block;
            this.boatItem = boatItem;
        }

        public String getName() {
            return this.name;
        }

        public Block asPlank() {
            return this.block.get();
        }

        public Item asBoatItem() {
            return this.boatItem.get();
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
        return this.getMythBoatType().asBoatItem();
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

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        this.lastYd = this.getMotion().y;
        if (!this.isPassenger()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != BoatEntity.Status.ON_LAND) {
                        this.fallDistance = 0.0F;
                        return;
                    }

                    this.onLivingFall(this.fallDistance, 1.0F);
                    if (!this.world.isRemote && !this.isAlive()) {
                        this.remove();
                        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                            for(int i = 0; i < 3; ++i) {
                                this.entityDropItem(this.getMythBoatType().asPlank());
                            }

                            for(int j = 0; j < 2; ++j) {
                                this.entityDropItem(Items.STICK);
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (!this.world.getFluidState(this.getPosition().down()).isTagged(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float)((double)this.fallDistance - y);
            }

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
