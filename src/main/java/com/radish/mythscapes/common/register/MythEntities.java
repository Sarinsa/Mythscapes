package com.radish.mythscapes.common.register;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.api.impl.RegistryHelper;
import com.radish.mythscapes.api.impl.brushables.BirdBrushable;
import com.radish.mythscapes.api.impl.brushables.DefaultBrushable;
import com.radish.mythscapes.api.impl.brushables.LionBrushable;
import com.radish.mythscapes.api.impl.brushables.SheepBrushable;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.*;
import com.radish.mythscapes.common.entities.misc.MythBoatEntity;
import com.radish.mythscapes.common.entities.projectile.BlisterberryEntity;
import com.radish.mythscapes.common.entities.projectile.GlowballEntity;
import com.radish.mythscapes.common.entities.projectile.StaticCottonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;

public class MythEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Mythscapes.MODID);

    public static final LinkedHashMap<Class<?>, IBrushable<?>> BRUSHABLES = new LinkedHashMap<>();

    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = register("myth_boat", EntityType.Builder.<MythBoatEntity>create(MythBoatEntity::new, EntityClassification.MISC).size(1.375f, 0.5625f));
    public static final RegistryObject<EntityType<BlisterberryEntity>> BLISTERBERRY = register("blisterberry", EntityType.Builder.<BlisterberryEntity>create(BlisterberryEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<GlowballEntity>> GLOWBALL = register("glowball", EntityType.Builder.<GlowballEntity>create(GlowballEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<StaticCottonEntity>> STATIC_COTTON = register("static_cotton", EntityType.Builder.<StaticCottonEntity>create(StaticCottonEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = register("pond_serpent", EntityType.Builder.create(PondSerpentEntity::new, EntityClassification.CREATURE).size(0.5f, 0.3f));
    public static final RegistryObject<EntityType<LionEntity>> LION = register("lion", EntityType.Builder.create(LionEntity::new, EntityClassification.CREATURE).size(1.0f, 1.3f));
    public static final RegistryObject<EntityType<FishbonesEntity>> FISHBONES = register("fishbones", EntityType.Builder.create(FishbonesEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F));
    public static final RegistryObject<EntityType<SnailEntity>> PYGMY_SNAIL = register("pygmy_snail", EntityType.Builder.<SnailEntity>create(SnailEntity::new, EntityClassification.CREATURE).size(0.3f, 0.3f));
    public static final RegistryObject<EntityType<DeerEntity>> DEER = register("deer", EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(1.0f, 1.4f));

    public static void registerData() {
        registerAttributes();
        registerEntityPlacement();
        registerBrushables();
    }

    private static void registerAttributes() {
        GlobalEntityTypeAttributes.put(POND_SERPENT.get(), PondSerpentEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(LION.get(), LionEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(FISHBONES.get(), FishbonesEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(PYGMY_SNAIL.get(), SnailEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(DEER.get(), DeerEntity.registerEntityAttributes().create());
    }

    private static void registerBrushables() {
        RegistryHelper registryHelper = Mythscapes.getInstance().getRegistryHelper();

        registryHelper.registerBrushable(LionEntity.class, new LionBrushable());
        registryHelper.registerBrushable(DeerEntity.class, new DefaultBrushable());

        // Vanilla
        registryHelper.registerBrushable(SheepEntity.class, new SheepBrushable());
        registryHelper.registerBrushable(CowEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(HorseEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(PigEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(WolfEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(CatEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(LlamaEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(RabbitEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(ChickenEntity.class, new BirdBrushable());
        registryHelper.registerBrushable(ParrotEntity.class, new BirdBrushable());
        registryHelper.registerBrushable(DonkeyEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(MuleEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(MooshroomEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(PandaEntity.class, new DefaultBrushable());
        registryHelper.registerBrushable(PolarBearEntity.class, new DefaultBrushable());
    }

    private static void registerEntityPlacement() {
        EntitySpawnPlacementRegistry.register(LION.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(POND_SERPENT.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FISHBONES.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FishbonesEntity::canFishbonesSpawn);
        EntitySpawnPlacementRegistry.register(PYGMY_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SnailEntity::canSnailSpawn);
        EntitySpawnPlacementRegistry.register(DEER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
    }


    private static <I extends Entity> RegistryObject<EntityType<I>> register(String name, EntityType.Builder<I> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }
}
