package com.mythscapes.common.entities.living.snail;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SnailEntity extends CreatureEntity {

    public SnailEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
    }
}
