package com.radish.mythscapes.client.renderers.entity.living;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


/**
 *  Kinda just here for testing entities that don't have a
 *  finished texture or model yet.
 */
public class NoRenderer<T extends Entity> extends EntityRenderer<T> {

    public NoRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return new ResourceLocation("");
    }
}
