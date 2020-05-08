package com.mythscapes.client.renderers.entities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.loot.LootModifierManager;

@OnlyIn(Dist.CLIENT)
public class NoRenderer extends EntityRenderer<Entity> {

    public NoRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
