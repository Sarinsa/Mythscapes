package com.mythscapes.core;

import com.mythscapes.client.ClientRegister;
import com.mythscapes.common.biomes.BaseBiome;
import com.mythscapes.misc.DispenserBehavior;
import com.mythscapes.register.*;
import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import net.minecraft.util.Timer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.server.timings.ForgeTimings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Mythscapes.MODID)
public class Mythscapes {

    // ModID
    public static final String MODID = "mythscapes";
    // Version
    public static final String VERSION = "1.0b";
    // Logger
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    // Mod instance
    public static Mythscapes INSTANCE;


    public Mythscapes() {
        INSTANCE = this;

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::serverStarting);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(this::clientSetup);
        });


        MythBlocks.BLOCKS.register(eventBus);
        MythItems.ITEMS.register(eventBus);
        MythFluids.FLUIDS.register(eventBus);
        MythSounds.SOUNDS.register(eventBus);
        MythBiomes.BIOMES.register(eventBus);
        MythEntities.ENTITIES.register(eventBus);
        MythParticles.PARTICLES.register(eventBus);
        MythEffects.POTION_EFFECTS.register(eventBus);
    }

    private void serverStarting(FMLServerAboutToStartEvent event) {
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        MythItems.registerItemInfo();
        MythBlocks.registerBlockInfo();

        MythBiomes.setBiomeEntitySpawns();
        MythBiomes.addBiomes();

        MythEntities.registerEntityPlacement();

        MythSounds.registerParrotMimics();

        DispenserBehavior.register();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        ClientRegister.registerEntityRenderers(event.getMinecraftSupplier());
        ClientRegister.setBlockRenderTypes();
    }
}
