package com.mythscapes.client.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mythscapes.client.renderers.entities.model.FishbonesModel;
import com.mythscapes.common.entities.living.FishbonesEntity;
import com.mythscapes.core.Mythscapes;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishbonesRenderer extends MobRenderer<FishbonesEntity, FishbonesModel<FishbonesEntity>> {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/fishbones/fishbones.png");

    public FishbonesRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FishbonesModel<>(), 0.5f);
    }

    @Override
    public void render(FishbonesEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(0.95F, 0.95F, 0.95F);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(FishbonesEntity entity) {
        return TEXTURE;
    }
}
