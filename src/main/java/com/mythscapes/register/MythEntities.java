package com.mythscapes.register;

import com.mythscapes.common.entities.BlisterberryEntity;
import com.mythscapes.common.entities.MythBoatEntity;
import com.mythscapes.common.entities.PondSerpentEntity;
import com.mythscapes.common.items.BlisterBerryItem;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Mythscapes.MODID);

    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = ENTITIES.register("myth_boat", () -> EntityType.Builder.<MythBoatEntity>create(MythBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build("myth_boat"));
    public static final RegistryObject<EntityType<BlisterberryEntity>> BLISTERBERRY = ENTITIES.register("blisterberry", () -> EntityType.Builder.<BlisterberryEntity>create(BlisterberryEntity::new, EntityClassification.MISC).size(0.25f, 0.25f).build("blisterberry"));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = ENTITIES.register("pond_serpent", () -> EntityType.Builder.create(PondSerpentEntity::new, EntityClassification.CREATURE).build("pond_serpent"));
}
