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

    private static final ResourceLocation BLANK = new ResourceLocation("");

    public NoRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return BLANK;
    }
}
