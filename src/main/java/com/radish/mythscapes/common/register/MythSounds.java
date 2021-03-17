package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Mythscapes.MODID);


    public static final RegistryObject<SoundEvent> LION_IDLE = registerSound("lion_idle");
    public static final RegistryObject<SoundEvent> OOF = registerSound("oof");


    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(Mythscapes.resourceLoc(name)));
    }

    public static void registerParrotMimics() {
        registerMimic(MythEntities.LION, MythSounds.LION_IDLE);
    }

    private static void registerMimic(Supplier<? extends EntityType<?>> entityTypeSupplier, Supplier<SoundEvent> soundEventSupplier) {
        ParrotEntity.MOB_SOUND_MAP.put(entityTypeSupplier.get(), soundEventSupplier.get());
    }
}
