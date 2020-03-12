package com.mythscapes.common.entities;

import com.mythscapes.register.MythEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class MythBoatEntity extends BoatEntity {

    public MythBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public MythBoatEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(MythEntities.MYTH_BOAT.get(), world);
    }
}
