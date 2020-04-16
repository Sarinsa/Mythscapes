package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Mythscapes.MODID);

    public static final RegistryObject<SoundEvent> LION_IDLE = SOUNDS.register("lion_idle", () -> new SoundEvent(new ModResourceLocation("")));
}
