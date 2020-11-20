package com.radish.mythscapes.client.renderers.entities;

import com.radish.mythscapes.client.renderers.entities.models.DeerModel;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.DeerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel<DeerEntity>> {

    private static final ResourceLocation[] TEXTURES = {
            Mythscapes.resourceLoc("textures/entity/deer/deer.png"),
            Mythscapes.resourceLoc("textures/entity/deer/deer_no_antlers.png")
    };

    public DeerRenderer(EntityRendererManager renderManager) {
        super(renderManager, new DeerModel<>(), 0.6f);
    }

    @Override
    public ResourceLocation getEntityTexture(DeerEntity entity) {
        return entity.hasAntlers() ? TEXTURES[0] : TEXTURES[1];
    }
}
