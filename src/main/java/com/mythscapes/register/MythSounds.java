package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Mythscapes.MODID);


    public static final RegistryObject<SoundEvent> LION_IDLE = registerSound("lion_idle");


    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ModResourceLocation(name)));
    }

    public static void registerParrotMimics() {
        registerMimic(MythEntities.LION, MythSounds.LION_IDLE);
    }

    private static void registerMimic(Supplier<? extends EntityType<?>> entityTypeSupplier, Supplier<SoundEvent> soundEventSupplier) {
        ParrotEntity.IMITATION_SOUND_EVENTS.put(entityTypeSupplier.get(), soundEventSupplier.get());
    }
}
