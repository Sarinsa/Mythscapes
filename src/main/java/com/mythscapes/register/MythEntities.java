package com.mythscapes.register;

import com.mythscapes.api.IBrushable;
import com.mythscapes.common.entities.brushables.BirdBrushable;
import com.mythscapes.common.entities.brushables.DefaultBrushable;
import com.mythscapes.common.entities.brushables.LionBrushable;
import com.mythscapes.common.entities.brushables.SheepBrushable;
import com.mythscapes.common.entities.living.FishbonesEntity;
import com.mythscapes.common.entities.living.LionEntity;
import com.mythscapes.common.entities.living.PondSerpentEntity;
import com.mythscapes.common.entities.living.SnailEntity;
import com.mythscapes.common.entities.misc.MythBoatEntity;
import com.mythscapes.common.entities.projectile.BlisterberryEntity;
import com.mythscapes.common.entities.projectile.GlowballEntity;
import com.mythscapes.common.entities.projectile.StaticCottonEntity;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;

public class MythEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Mythscapes.MODID);

    public static final LinkedHashMap<EntityType<?>, IBrushable<?>> BRUSHABLES = new LinkedHashMap<>();

    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = register("myth_boat", EntityType.Builder.<MythBoatEntity>create(MythBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F));
    public static final RegistryObject<EntityType<BlisterberryEntity>> BLISTERBERRY = register("blisterberry", EntityType.Builder.<BlisterberryEntity>create(BlisterberryEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<GlowballEntity>> GLOWBALL = register("glowball", EntityType.Builder.<GlowballEntity>create(GlowballEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<StaticCottonEntity>> STATIC_COTTON = register("static_cotton", EntityType.Builder.<StaticCottonEntity>create(StaticCottonEntity::new, EntityClassification.MISC).size(0.25f, 0.25f));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = register("pond_serpent", EntityType.Builder.create(PondSerpentEntity::new, EntityClassification.CREATURE).size(0.5f, 0.3f));
    public static final RegistryObject<EntityType<LionEntity>> LION = register("lion", EntityType.Builder.create(LionEntity::new, EntityClassification.CREATURE).size(1.0f, 1.3f));
    public static final RegistryObject<EntityType<FishbonesEntity>> FISHBONES = register("fishbones", EntityType.Builder.create(FishbonesEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F));
    public static final RegistryObject<EntityType<SnailEntity>> PYGMY_SNAIL = register("pygmy_snail", EntityType.Builder.create(SnailEntity::new, EntityClassification.CREATURE).size(0.3f, 0.3f));

    public static void registerAttributes() {
        GlobalEntityTypeAttributes.put(POND_SERPENT.get(), PondSerpentEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(LION.get(), LionEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(FISHBONES.get(), FishbonesEntity.registerEntityAttributes().create());
        GlobalEntityTypeAttributes.put(PYGMY_SNAIL.get(), SnailEntity.registerEntityAttributes().create());
    }

    public static void registerBrushables() {
        RegistryUtil registryHelper = (RegistryUtil) Mythscapes.getInstance().getRegistryHelper();

        registryHelper.registerBrushableInternal(LION.get(), new LionBrushable());
        registryHelper.registerBrushableInternal(EntityType.SHEEP, new SheepBrushable());
        registryHelper.registerBrushableInternal(EntityType.COW, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.HORSE, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.PIG, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.WOLF, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.CAT, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.LLAMA, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.RABBIT, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.CHICKEN, new BirdBrushable());
        registryHelper.registerBrushableInternal(EntityType.PARROT, new BirdBrushable());
        registryHelper.registerBrushableInternal(EntityType.DONKEY, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.MULE, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.MOOSHROOM, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.PANDA, new DefaultBrushable());
        registryHelper.registerBrushableInternal(EntityType.POLAR_BEAR, new DefaultBrushable());
    }

    public static void registerEntityPlacement() {
        EntitySpawnPlacementRegistry.register(LION.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        EntitySpawnPlacementRegistry.register(POND_SERPENT.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AbstractFishEntity::func_223363_b);
        EntitySpawnPlacementRegistry.register(FISHBONES.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FishbonesEntity::canFishbonesSpawn);
        EntitySpawnPlacementRegistry.register(PYGMY_SNAIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, SnailEntity::canSnailSpawn);
    }

    private static <I extends Entity> RegistryObject<EntityType<I>> register(String name, EntityType.Builder<I> builder) {
        return ENTITIES.register(name, () -> builder.build(name));
    }
}
