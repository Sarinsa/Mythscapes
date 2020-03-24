package com.mythscapes.register;

import com.mythscapes.common.entities.BlisterBerryEntity;
import com.mythscapes.common.entities.MythBoatEntity;
import com.mythscapes.common.entities.PondSerpentEntity;
import com.mythscapes.common.items.BlisterBerryItem;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.http.client.entity.EntityBuilder;

public class MythEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Mythscapes.MODID);

    public static final RegistryObject<EntityType<MythBoatEntity>> MYTH_BOAT = ENTITIES.register("myth_boat", () -> EntityType.Builder.<MythBoatEntity>create(MythBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build("myth_boat"));
    public static final RegistryObject<EntityType<BlisterBerryEntity>> BLISTER_BERRY = ENTITIES.register("blister_berry", () -> EntityType.Builder.<BlisterBerryEntity>create(BlisterBerryEntity::new, EntityClassification.MISC).size(0.4f, 0.4f).build("blister_berry"));
    public static final RegistryObject<EntityType<PondSerpentEntity>> POND_SERPENT = ENTITIES.register("pond_serpent", () -> EntityType.Builder.create(PondSerpentEntity::new, EntityClassification.CREATURE).build("pond_serpent"));
}
