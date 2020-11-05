package com.mythscapes.client.renderers.entities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


/**
 *  Kinda just here for testing entities that don't have a
 *  finished texture or model yet.
 */
public class NoRenderer extends EntityRenderer<Entity> {

    public NoRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("");
    }
}
