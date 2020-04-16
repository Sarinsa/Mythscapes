package com.mythscapes.register;

import com.mythscapes.common.entities.*;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Mythscapes.MODID);

    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = ENTITIES.register("myth_boat", () -> EntityType.Builder.<MythBoatEntity>create(MythBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build("myth_boat"));
    public static final RegistryObject<EntityType<BlisterberryEntity>> BLISTERBERRY = ENTITIES.register("blisterberry", () -> EntityType.Builder.<BlisterberryEntity>create(BlisterberryEntity::new, EntityClassification.MISC).size(0.25f, 0.25f).build("blisterberry"));
    public static final RegistryObject<EntityType<GlowballEntity>> GLOWBALL = ENTITIES.register("glowball", () -> EntityType.Builder.<GlowballEntity>create(GlowballEntity::new, EntityClassification.MISC).size(0.25f, 0.25f).build("glowball"));
    public static final RegistryObject<EntityType<StaticCottonEntity>> STATIC_COTTON = ENTITIES.register("static_cotton", () -> EntityType.Builder.<StaticCottonEntity>create(StaticCottonEntity::new, EntityClassification.MISC).size(0.25f, 0.25f).build("static_cotton"));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = ENTITIES.register("pond_serpent", () -> EntityType.Builder.create(PondSerpentEntity::new, EntityClassification.CREATURE).size(0.5f, 0.3f).build("pond_serpent"));
    public static final RegistryObject<EntityType<LionEntity>> LION = ENTITIES.register("lion", () -> EntityType.Builder.create(LionEntity::new, EntityClassification.CREATURE).size(1.0f, 1.3f).build("lion"));

    public static void registerEntityPlacement() {
        EntitySpawnPlacementRegistry.register(POND_SERPENT.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
    }
}
