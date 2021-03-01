package com.radish.mythscapes.client.renderers.entity.living.pygmy_snail;

import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class PygmySnailRenderer extends MobRenderer<SnailEntity, PygmySnailModel<SnailEntity>> {

    public PygmySnailRenderer(EntityRendererManager manager) {
        super(manager, new PygmySnailModel<>(), 0.2f);
        this.addLayer(new PygmySnailGrowthLayerRenderer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(SnailEntity entity) {
        return entity.getSnailType().getSnailTexture();
    }
}
