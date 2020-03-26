package com.mythscapes.client;

import com.mythscapes.client.renderers.entities.PondSerpentRenderer;
import com.mythscapes.client.renderers.entities.boats.MythBoatRenderer;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@OnlyIn(value = Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {

    public static void registerEntityRenderers(Supplier<Minecraft> minecraftSupplier) {
        RenderingRegistry.registerEntityRenderingHandler(MythEntities.MYTH_BOAT.get(), MythBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(MythEntities.POND_SERPENT.get(), PondSerpentRenderer::new);

        // "2D" entities
        registerNewSpriteRenderer(MythEntities.BLISTERBERRY.get(), minecraftSupplier);
    }

    public static void setBlockRenderTypes() {
        RenderTypeLookup.setRenderLayer(MythBlocks.CHARGED_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(MythBlocks.BLISTERBERRY_THISTLE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(MythBlocks.BLISTERBERRY_THISTLE_TOP.get(), RenderType.getCutout());
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        ParticleManager manager = Minecraft.getInstance().particles;

        // manager.registerFactory(MythParticles.SOME_PARTICLE.get, SomeParticle.Factory::new);
    }

    private static <T extends Entity & IRendersAsItem> void registerNewSpriteRenderer(EntityType<T> entityType, Supplier<Minecraft> minecraftSupplier) {
        RenderingRegistry.registerEntityRenderingHandler(entityType, (rendererManager) -> new SpriteRenderer<>(rendererManager, minecraftSupplier.get().getItemRenderer()));
    }
}
