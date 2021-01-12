package com.radish.mythscapes.common.core;

import com.radish.mythscapes.api.IMythscapesPlugin;
import com.radish.mythscapes.api.MythscapesPlugin;
import com.radish.mythscapes.api.impl.RegistryHelper;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.event.BiomeEvents;
import com.radish.mythscapes.common.event.EffectEvents;
import com.radish.mythscapes.common.event.EntityEvents;
import com.radish.mythscapes.common.event.TradeEvents;
import com.radish.mythscapes.common.misc.DispenserBehavior;
import com.radish.mythscapes.common.register.*;
import com.radish.mythscapes.common.tags.MythBlockTags;
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

    private final RegistryHelper registryHelper = new RegistryHelper();
    private final SnailTypeRegister snailTypeRegister = new SnailTypeRegister();

    static {
        MythBlockTags.init();
    }

    public Mythscapes() {
        INSTANCE = this;

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::loadComplete);

        MinecraftForge.EVENT_BUS.register(new EffectEvents());
        MinecraftForge.EVENT_BUS.register(new EntityEvents());
        MinecraftForge.EVENT_BUS.register(new TradeEvents());
        MinecraftForge.EVENT_BUS.register(new BiomeEvents());

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
        MythEntities.registerData();
        MythSounds.registerParrotMimics();
        DispenserBehavior.register();
        MythPotions.registerBrewingRecipes();

        snailTypeRegister.registerInternal();
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        ModList.get().getAllScanData().forEach(scanData -> {

            scanData.getAnnotations().forEach(annotationData -> {

                if (annotationData.getAnnotationType().getClassName().equals(MythscapesPlugin.class.getName())) {
                    String modid = (String) annotationData.getAnnotationData().getOrDefault("modid", "");

                    if (modid.isEmpty() || ModList.get().isLoaded(modid)) {

                        try {
                            Class<?> pluginClass = Class.forName(annotationData.getMemberName());

                            if (IMythscapesPlugin.class.isAssignableFrom(pluginClass)) {
                                IMythscapesPlugin plugin = (IMythscapesPlugin) pluginClass.newInstance();
                                registryHelper.setCurrentPluginID(plugin);
                                plugin.register(registryHelper);
                                LOGGER.info("Cool beans! Successfully registered plugin at {}", annotationData.getMemberName());
                            }
                        }
                        catch (Exception e) {
                            LOGGER.error("Failed to register plugin at {}! Ooof! :(", annotationData.getMemberName());
                            e.printStackTrace();
                        }
                    }
                }
            });
        });
        registryHelper.postPluginSetup();
    }

    public static Mythscapes getInstance() {
        return INSTANCE;
    }

    public static ResourceLocation resourceLoc(String path) {
        return new ResourceLocation(MODID, path);
    }

    public RegistryHelper getRegistryHelper() {
        return this.registryHelper;
    }

    public SnailTypeRegister getSnailTypeRegister() {
        return this.snailTypeRegister;
    }
}
