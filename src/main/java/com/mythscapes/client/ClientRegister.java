package com.mythscapes.client;

import com.mythscapes.client.renderers.entities.MythBoatRenderer;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(value = Dist.CLIENT)
public class ClientRegister {

    public static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(MythEntities.MYTH_BOAT.get(), MythBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(MythEntities.BLISTER_BERRY.get(), (rendererManager) -> new SpriteRenderer<>(rendererManager, Minecraft.getInstance().getItemRenderer()));
    }

    public static void setBlockRenderTypes() {
        RenderTypeLookup.setRenderLayer(MythBlocks.BLISTER_BERRY_BUSH.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(MythBlocks.BLISTER_BERRY_BUSH_TOP.get(), RenderType.getTranslucent());

    }
}
