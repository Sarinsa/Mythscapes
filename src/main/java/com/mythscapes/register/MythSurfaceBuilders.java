package com.mythscapes.register;

import com.mythscapes.common.surface_builders.StaticForestSurfaceBuilder;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.ModResourceLocation;
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

    public static SurfaceBuilder<SurfaceBuilderConfig> STATIC_FOREST_TEMP = new StaticForestSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    @SubscribeEvent
    public static void initConfigs(RegistryEvent.Register<Item> event) {
        GRASS_GALVITE_GRAVEL_CONFIG = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), MythBlocks.GALVITE.get().getDefaultState(), Blocks.GRAVEL.getDefaultState());
    }


    @SubscribeEvent
    public static void initSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().register(
                register("static_forest", STATIC_FOREST_TEMP)
        );
    }

    private static SurfaceBuilder<?> register(String name, SurfaceBuilder<?> surfaceBuilder) {
        return surfaceBuilder.setRegistryName(new ModResourceLocation(name));
    }
}
