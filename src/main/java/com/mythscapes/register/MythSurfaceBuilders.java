package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythSurfaceBuilders {


    public static SurfaceBuilderConfig GRASS_GALVITE_GRAVEL_CONFIG;

    @SubscribeEvent
    public static void initConfigs(RegistryEvent.Register<Item> event) {
        GRASS_GALVITE_GRAVEL_CONFIG = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), MythBlocks.GALVITE.get().getDefaultState(), Blocks.GRAVEL.getDefaultState());
    }


    @SubscribeEvent
    public static void initSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {

    }

    private static SurfaceBuilder<?> register(String name, SurfaceBuilder<?> surfaceBuilder) {
        return surfaceBuilder.setRegistryName(Mythscapes.resourceLoc(name));
    }
}
