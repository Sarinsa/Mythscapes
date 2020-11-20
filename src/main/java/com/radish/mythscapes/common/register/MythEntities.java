package com.radish.mythscapes.common.register;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.api.impl.RegistryUtil;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;

import static com.radish.mythscapes.common.misc.Util.areBiomesEqual;

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
    public static final RegistryObject<EntityType<SnailEntity>> PYGMY_SNAIL = register("pygmy_snail", EntityType.Builder.create(SnailEntity::new, EntityClassification.CREATURE).size(0.3f, 0.3f));
    public static final RegistryObject<EntityType<DeerEntity>> DEER = register("deer", EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(1.0f, 1.4f));

    public static void registerAttributes() {
        GlobalEntityTypeAttributes.put(POND_SERPENT.get(), PondSerpentEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(LION.get(), LionEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(FISHBONES.get(), FishbonesEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(PYGMY_SNAIL.get(), SnailEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(DEER.get(), DeerEntity.registerEntityAttributes().create());
    }

    public static void registerBrushables() {
        RegistryUtil registryHelper = (RegistryUtil) Mythscapes.getInstance().getRegistryHelper();

        registryHelper.registerBrushable(LionEntity.class, new LionBrushable());
        registryHelper.registerBrushable(DeerEntity.class, new DefaultBrushable());

        // Maybe these should be registered via an internal plugin with low priority?
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

    public static void registerEntityPlacement() {
        EntitySpawnPlacementRegistry.register(LION.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(POND_SERPENT.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FISHBONES.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FishbonesEntity::canFishbonesSpawn);
        EntitySpawnPlacementRegistry.register(PYGMY_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SnailEntity::canSnailSpawn);
        EntitySpawnPlacementRegistry.register(DEER.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
    }

    /**
     * Adding new and removing existing entity spawns.
     *
     * @param event - The BiomeLoadingEvent parsed when a biome is loaded.
     */
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void changeBiomeSpawns(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (biomeName != null) {

            if (areBiomesEqual(biomeName, Biomes.SAVANNA) || areBiomesEqual(biomeName, Biomes.SHATTERED_SAVANNA)) {
                addBiomeSpawn(event, LION.get(), EntityClassification.CREATURE, 7, 2, 4);
            }
            else if (areBiomesEqual(biomeName, Biomes.WARM_OCEAN) || areBiomesEqual(biomeName, Biomes.LUKEWARM_OCEAN)) {
                addBiomeSpawn(event, POND_SERPENT.get(), EntityClassification.WATER_CREATURE, 3, 1, 2);
            }
            else if (areBiomesEqual(biomeName, Biomes.OCEAN) || areBiomesEqual(biomeName, Biomes.DEEP_OCEAN)) {
                addBiomeSpawn(event, POND_SERPENT.get(), EntityClassification.WATER_CREATURE, 3, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.FROZEN_OCEAN) || areBiomesEqual(biomeName, Biomes.FROZEN_RIVER) || areBiomesEqual(biomeName, Biomes.DEEP_FROZEN_OCEAN)) {
                addBiomeSpawn(event, FISHBONES.get(), EntityClassification.MONSTER, 95, 3, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.ICE_SPIKES)) {
                addBiomeSpawn(event, FISHBONES.get(), EntityClassification.MONSTER, 95, 3, 5);
                removeBiomeSpawn(event, EntityType.ZOMBIE, EntityClassification.MONSTER);
                removeBiomeSpawn(event, EntityType.ZOMBIE_VILLAGER, EntityClassification.MONSTER);
            }
            else if (areBiomesEqual(biomeName, Biomes.JUNGLE) || areBiomesEqual(biomeName, Biomes.JUNGLE_EDGE) || areBiomesEqual(biomeName, Biomes.JUNGLE_HILLS) || areBiomesEqual(biomeName, Biomes.MODIFIED_JUNGLE) || areBiomesEqual(biomeName, Biomes.MODIFIED_JUNGLE_EDGE)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.BAMBOO_JUNGLE) || areBiomesEqual(biomeName, Biomes.BAMBOO_JUNGLE_HILLS)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.MUSHROOM_FIELDS)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.FLOWER_FOREST)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 90, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.FOREST) || areBiomesEqual(biomeName, Biomes.BIRCH_FOREST)) {
                addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 90, 2, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.TAIGA) || areBiomesEqual(biomeName, Biomes.TAIGA_HILLS) || areBiomesEqual(biomeName, Biomes.TAIGA_MOUNTAINS)) {
                addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 90, 2, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.SNOWY_TUNDRA)) {
                addBiomeSpawn(event, DEER.get(), EntityClassification.CREATURE, 90, 2, 5);
            }
            else if (areBiomesEqual(biomeName, Biomes.BEACH)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 55, 1, 3);
            }
            else if (areBiomesEqual(biomeName, Biomes.SWAMP) || areBiomesEqual(biomeName, Biomes.SWAMP_HILLS)) {
                addBiomeSpawn(event, PYGMY_SNAIL.get(), EntityClassification.CREATURE, 75, 1, 3);
            }
        }
    }

    private static void addBiomeSpawn(BiomeLoadingEvent event, EntityType<?> entityType, EntityClassification entityClassification, int weight, int minCount, int maxCount) {
        if (minCount > maxCount) {
            Mythscapes.LOGGER.error("Tried to register biome entity spawn with min count greater than max count. This can't be right?");
            return;
        }
        event.getSpawns().getSpawner(entityClassification).add(new MobSpawnInfo.Spawners(entityType, weight, minCount, maxCount));
    }

    private static void removeBiomeSpawn(BiomeLoadingEvent event, EntityType<?> entityType, EntityClassification entityClassification) {
        event.getSpawns().getSpawner(entityClassification).removeIf((spawners) -> spawners.type == entityType);
    }

    private static <I extends Entity> RegistryObject<EntityType<I>> register(String name, EntityType.Builder<I> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }
}
