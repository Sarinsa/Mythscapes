package com.mythscapes.core;

import com.mythscapes.api.IMythscapesPlugin;
import com.mythscapes.api.IRegistryHelper;
import com.mythscapes.api.MythscapesPlugin;
import com.mythscapes.misc.DispenserBehavior;
import com.mythscapes.register.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(Mythscapes.MODID)
public class Mythscapes {

    public static final String MODID = "mythscapes";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    private static Mythscapes INSTANCE;

    private final IRegistryHelper registryHelper = new RegistryUtil();


    public Mythscapes() {
        INSTANCE = this;

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::loadComplete);

        MythBlocks.BLOCKS.register(eventBus);
        MythItems.ITEMS.register(eventBus);
        MythFluids.FLUIDS.register(eventBus);
        MythSounds.SOUNDS.register(eventBus);
        MythBiomes.BIOMES.register(eventBus);
        MythPotions.POTIONS.register(eventBus);
        MythEntities.ENTITIES.register(eventBus);
        MythParticles.PARTICLES.register(eventBus);
        //MythDimensions.DIMENSIONS.register(eventBus);
        MythEffects.POTION_EFFECTS.register(eventBus);
        MythEnchantments.ENCHANTMENTS.register(eventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        MythItems.registerItemInfo();
        MythBlocks.registerBlockInfo();
        //MythBiomes.setBiomeEntitySpawns();
        MythBiomes.addBiomes();
        MythEntities.registerBrushables();
        MythEntities.registerAttributes();
        MythEntities.registerEntityPlacement();
        MythSounds.registerParrotMimics();
        MythPotions.registerBrewingRecipes();
        DispenserBehavior.register();
    }

    /**
     * Register Mythscapes plugins from other mods and whatnots
     */
    public void loadComplete(FMLLoadCompleteEvent event) {
        ModList.get().getAllScanData().forEach(scan -> {
            scan.getAnnotations().forEach(annotationData -> {

                if (annotationData.getAnnotationType().getClassName().equals(MythscapesPlugin.class.getName())) {
                    String modid = (String) annotationData.getAnnotationData().getOrDefault("modid", "");

                    if (modid.isEmpty() || ModList.get().isLoaded(modid)) {
                        try {
                            Class<?> pluginClass = Class.forName(annotationData.getMemberName());

                            if (IMythscapesPlugin.class.isAssignableFrom(pluginClass)) {
                                IMythscapesPlugin plugin = (IMythscapesPlugin) pluginClass.newInstance();
                                plugin.register(getRegistryHelper());
                                LOGGER.info("Registered plugin at {}", annotationData.getMemberName());
                            }
                        }
                        catch (Exception e) {
                            LOGGER.error("Failed to register plugin at {}", annotationData.getMemberName());
                            e.printStackTrace();
                        }
                    }
                }
            });
        });
    }

    public static Mythscapes getInstance() {
        return INSTANCE;
    }

    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MODID, path);
    }

    public IRegistryHelper getRegistryHelper() {
        return this.registryHelper;
    }
}
