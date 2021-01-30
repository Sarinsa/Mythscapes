package com.radish.mythscapes.client;

import com.radish.mythscapes.client.particles.StaticCottonFallingParticle;
import com.radish.mythscapes.client.particles.StaticCottonParticle;
import com.radish.mythscapes.client.particles.StaticCottonPoofParticle;
import com.radish.mythscapes.client.renderers.entity.*;
import com.radish.mythscapes.client.renderers.tile.ModSignTileEntityRenderer;
import com.radish.mythscapes.common.blocks.plant.TintedLeafCarpetBlock;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythParticles;
import com.radish.mythscapes.common.register.registry.MythTileEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.function.Supplier;

import static net.minecraft.client.renderer.RenderTypeLookup.setRenderLayer;
import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        registerEntityRenderers(event.getMinecraftSupplier());
        registerTileEntityRenderers();
        setBlockRenderTypes();
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particles;

        manager.registerFactory(MythParticles.STATIC_COTTON.get(), StaticCottonParticle.Factory::new);
        manager.registerFactory(MythParticles.STATIC_COTTON_FALLING.get(), StaticCottonFallingParticle.Factory::new);
        manager.registerFactory(MythParticles.STATIC_COTTON_POOF.get(), new StaticCottonPoofParticle.Factory());
    }

    @SubscribeEvent
    public static void registerItemColors(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        TintedLeafCarpetBlock.blockList.forEach((block -> {
            itemColors.register((itemStack, i) -> block.getLeavesColor(), block);
        }));
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();

        TintedLeafCarpetBlock.blockList.forEach((block -> {
            blockColors.register((state, displayReader, blockPos, i) -> block.getLeavesColor(), block);
        }));
    }

    public static void registerEntityRenderers(Supplier<Minecraft> minecraftSupplier) {
        registerEntityRenderingHandler(MythEntities.MYTH_BOAT.get(), MythBoatRenderer::new);
        registerEntityRenderingHandler(MythEntities.POND_SERPENT.get(), PondSerpentRenderer::new);
        registerEntityRenderingHandler(MythEntities.LION.get(), LionRenderer::new);
        registerEntityRenderingHandler(MythEntities.FISHBONES.get(), FishbonesRenderer::new);
        registerEntityRenderingHandler(MythEntities.PYGMY_SNAIL.get(), PygmySnailRenderer::new);
        registerEntityRenderingHandler(MythEntities.DEER.get(), DeerRenderer::new);

        // "2D" entities & throwables
        registerSpriteRenderer(MythEntities.BLISTERBERRY.get(), minecraftSupplier);
        registerSpriteRenderer(MythEntities.GLOWBALL.get(), minecraftSupplier);
        registerSpriteRenderer(MythEntities.STATIC_COTTON.get(), minecraftSupplier);
    }

    public static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(MythTileEntities.SIGN.get(), ModSignTileEntityRenderer::new);
    }

    public static void setBlockRenderTypes() {
        setRenderLayer(MythBlocks.WOLT_SAPLING.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.POTTED_WOLT_SAPLING.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.WOLT_DOOR.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.WOLT_TRAPDOOR.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.WOLT_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        setRenderLayer(MythBlocks.BLISTERBERRY_THISTLE.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.CHARGED_DANDELION.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.POTTED_CHARGED_DANDELION.get(), RenderType.getCutout());
        setRenderLayer(MythBlocks.LAUNCHER_RAIL.get(), RenderType.getCutout());
    }

    private static <T extends Entity & IRendersAsItem> void registerSpriteRenderer(EntityType<T> entityType, Supplier<Minecraft> minecraftSupplier) {
        ItemRenderer renderer = minecraftSupplier.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(entityType, (rendererManager) -> new SpriteRenderer<>(rendererManager, renderer));
    }
}
