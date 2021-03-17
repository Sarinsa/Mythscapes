package com.radish.mythscapes.client.renderers.entity.living.lion;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.LionEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LionRenderer extends MobRenderer<LionEntity, LionModel<LionEntity>> {

    private static final ResourceLocation NORMAL = Mythscapes.resourceLoc("textures/entity/lion/lion.png");
    private static final ResourceLocation AGGROED = Mythscapes.resourceLoc("textures/entity/lion/lion_aggroed.png");

    public LionRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new LionModel<>(), 0.7f);
        this.addLayer(new LionManeLayerRenderer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(LionEntity entity) {
        return entity.isAggressive() ? AGGROED : NORMAL;
    }
}
