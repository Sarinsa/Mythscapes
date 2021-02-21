package com.radish.mythscapes.client.renderers.entity.living.fishbones;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.FishbonesEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishbonesRenderer<T extends FishbonesEntity> extends MobRenderer<T, FishbonesModel<T>> {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/fishbones/fishbones.png");

    public FishbonesRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FishbonesModel<>(), 0.5f);
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(0.95F, 0.95F, 0.95F);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return TEXTURE;
    }
}
