package com.mythscapes.register;

import com.mythscapes.common.entities.living.fishbones.FishbonesEntity;
import com.mythscapes.common.entities.living.lion.LionEntity;
import com.mythscapes.common.entities.living.pond_serpent.PondSerpentEntity;
import com.mythscapes.common.entities.living.snail.SnailEntity;
import com.mythscapes.common.entities.misc.MythBoatEntity;
import com.mythscapes.common.entities.projectile.BlisterberryEntity;
import com.mythscapes.common.entities.projectile.GlowballEntity;
import com.mythscapes.common.entities.projectile.StaticCottonEntity;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.Entity;
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

    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = register("myth_boat", EntityType.Builder.<MythBoatEntity>create(MythBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F));
    public static final RegistryObject<EntityType<BlisterberryEntity>> BLISTERBERRY = register("blisterberry", EntityType.Builder.<BlisterberryEntity>create(BlisterberryEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<GlowballEntity>> GLOWBALL = register("glowball", EntityType.Builder.<GlowballEntity>create(GlowballEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<StaticCottonEntity>> STATIC_COTTON = register("static_cotton", EntityType.Builder.<StaticCottonEntity>create(StaticCottonEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = register("pond_serpent", EntityType.Builder.create(PondSerpentEntity::new, EntityClassification.CREATURE).size(0.5f, 0.3f));
    public static final RegistryObject<EntityType<LionEntity>> LION = register("lion", EntityType.Builder.create(LionEntity::new, EntityClassification.CREATURE).size(1.0f, 1.3f));
    public static final RegistryObject<EntityType<FishbonesEntity>> FISHBONES = register("fishbones", EntityType.Builder.create(FishbonesEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F));
    // Snail time
    public static final RegistryObject<EntityType<SnailEntity>> MUSHROOM_SNAIL = register("moshroom_snail", EntityType.Builder.create(SnailEntity::new, EntityClassification.CREATURE).size(0.3f, 0.3f));


    public static void registerEntityPlacement() {
        EntitySpawnPlacementRegistry.register(POND_SERPENT.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FISHBONES.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FishbonesEntity::canFishbonesSpawn);
    }

    private static <I extends Entity> RegistryObject<EntityType<I>> register(String name, EntityType.Builder<I> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }
}
