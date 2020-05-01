package com.mythscapes.client.renderers.entities;

import com.mythscapes.client.renderers.entities.model.LionModel;
import com.mythscapes.common.entities.living.lion.LionEntity;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionRenderer extends MobRenderer<LionEntity, LionModel<LionEntity>> {

    private static final ResourceLocation[] TEXTURES = {
            new ModResourceLocation("textures/entity/lion/lion_maneless.png"),
            new ModResourceLocation("textures/entity/lion/lion.png"),
            new ModResourceLocation("textures/entity/lion/lion_aggroed.png")
    };


    public LionRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new LionModel<>(), 0.7f);
    }

    @Override
    public ResourceLocation getEntityTexture(LionEntity entity) {
        return entity.isChild() ? TEXTURES[0] :
                (entity.isAggressive() ? TEXTURES[2] : TEXTURES[1]);
    }
}
