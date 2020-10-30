package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythFeatures {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Feature<?>> event) {

    }
}
