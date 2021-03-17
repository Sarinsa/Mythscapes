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
import net.minecraft.client.renderer.texture.SpriteUploader;
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
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MythEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Mythscapes.MODID);

    public static final LinkedHashMap<Class<?>, IBrushable<?>> BRUSHABLES = new LinkedHashMap<>();

    public static EntityType<PondSerpentEntity> POND_SERPENT_TYPE;
    public static EntityType<LionEntity> LION_TYPE;
    public static EntityType<FishbonesEntity> FISHBONES_TYPE;
    public static EntityType<SnailEntity> PYGMY_SNAIL_TYPE;
    public static EntityType<DeerEntity> DEER_TYPE;


    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = register("myth_boat", EntityType.Builder.<MythBoatEntity>of(MythBoatEntity::new, EntityClassification.MISC).sized(1.375f, 0.5625f));
    public static final RegistryObject<EntityType<BlisterberryEntity>> BLISTERBERRY = register("blisterberry", EntityType.Builder.<BlisterberryEntity>of(BlisterberryEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f));
    public static final RegistryObject<EntityType<GlowballEntity>> GLOWBALL = register("glowball", EntityType.Builder.<GlowballEntity>of(GlowballEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f));
    public static final RegistryObject<EntityType<StaticCottonEntity>> STATIC_COTTON = register("static_cotton", EntityType.Builder.<StaticCottonEntity>of(StaticCottonEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = register("pond_serpent", () -> POND_SERPENT_TYPE);
    public static final RegistryObject<EntityType<LionEntity>> LION = register("lion", () -> LION_TYPE);
    public static final RegistryObject<EntityType<FishbonesEntity>> FISHBONES = register("fishbones", () -> FISHBONES_TYPE);
    public static final RegistryObject<EntityType<SnailEntity>> PYGMY_SNAIL = register("pygmy_snail", () -> PYGMY_SNAIL_TYPE);
    public static final RegistryObject<EntityType<DeerEntity>> DEER = register("deer", () -> DEER_TYPE);

    // Initializing the types for living entities that
    // will be used with spawn eggs. While not pretty
    // or cool in the slightest, it works. These should
    // only be used by the spawn eggs, so use the registry
    // objects in other contexts.
    //
    // Fingers crossed that Mojang changes
    // spawn eggs to not be the big stupid.
    public static void initTypes() {
        POND_SERPENT_TYPE = create("pond_serpent", PondSerpentEntity::new, EntityClassification.CREATURE, 0.5f, 0.3f);
        LION_TYPE = create("lion", LionEntity::new, EntityClassification.CREATURE,1.0f, 1.3f);
        FISHBONES_TYPE = create("fishbones", FishbonesEntity::new, EntityClassification.MONSTER, 0.6F, 1.8F);
        PYGMY_SNAIL_TYPE = create("pygmy_snail", SnailEntity::new, EntityClassification.CREATURE, 0.3f, 0.3f);
        DEER_TYPE = create("deer", DeerEntity::new, EntityClassification.CREATURE, 1.0f, 1.4f);
    }

    public static void registerData() {
        registerEntityPlacement();
        registerBrushables();
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(POND_SERPENT.get(), PondSerpentEntity.registerEntityAttributes().build());
        event.put(LION.get(), LionEntity.registerEntityAttributes().build());
        event.put(FISHBONES.get(), FishbonesEntity.registerEntityAttributes().build());
        event.put(PYGMY_SNAIL.get(), SnailEntity.registerEntityAttributes().build());
        event.put(DEER.get(), DeerEntity.registerEntityAttributes().build());
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
        EntitySpawnPlacementRegistry.register(LION.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
        EntitySpawnPlacementRegistry.register(POND_SERPENT.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::checkFishSpawnRules);
        EntitySpawnPlacementRegistry.register(FISHBONES.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FishbonesEntity::checkFishbonesSpawnRules);
        EntitySpawnPlacementRegistry.register(PYGMY_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SnailEntity::checkPygmySnailSpawnRules);
        EntitySpawnPlacementRegistry.register(DEER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
    }


    private static <I extends Entity> RegistryObject<EntityType<I>> register(String name, EntityType.Builder<I> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }

    private static <I extends Entity> RegistryObject<EntityType<I>> register(String name, Supplier<EntityType<I>> entityTypeSupplier) {
        return ENTITIES.register(name, entityTypeSupplier);
    }

    private static <I extends Entity> EntityType<I> create(String name, EntityType.IFactory<I> factory, EntityClassification entityClassification, float width, float height) {
        return EntityType.Builder.of(factory, entityClassification).sized(width, height).build(name);
    }
}
