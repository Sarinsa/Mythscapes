package com.radish.mythscapes.client.renderers.entity.living.deer;

import com.radish.mythscapes.common.entities.living.DeerEntity;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel<DeerEntity>> {

    private static final ResourceLocation[] TEXTURES = {
            TextureUtil.entityTexture("deer/deer"),
            TextureUtil.entityTexture("deer/deer_no_antlers")
    };

    public DeerRenderer(EntityRendererManager renderManager) {
        super(renderManager, new DeerModel<>(), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(DeerEntity entity) {
        return entity.hasAntlers() ? TEXTURES[0] : TEXTURES[1];
    }
}
