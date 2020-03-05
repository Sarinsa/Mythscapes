package com.mythscapes.core;

import com.mythscapes.common.biomes.BaseBiome;
import com.mythscapes.register.MythBiomes;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mythscapes")
public class Mythscapes {

    // ModID
    public static final String MODID = "mythscapes";
    // Logger
    private static final Logger LOGGER = LogManager.getLogger();
    // Mod instance
    public static Mythscapes INSTANCE;


    public Mythscapes() {
        INSTANCE = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarting);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MythBlocks.BLOCKS.register(eventBus);
        MythItems.ITEMS.register(eventBus);
        MythBiomes.BIOMES.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void serverStarting(FMLServerAboutToStartEvent event) {
        // Adding biome entity spawns
        MythBiomes.biome_list.forEach(BaseBiome::addEntitySpawns);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }
}
