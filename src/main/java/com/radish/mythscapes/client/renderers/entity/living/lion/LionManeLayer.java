package com.radish.mythscapes.client.renderers.entity.living.lion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.LionEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LionManeLayer<T extends LionEntity> extends LayerRenderer<T, LionModel<T>> {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/lion/mane.png");
    private final LionManeModel<T> maneModel = new LionManeModel<>();

    public LionManeLayer(IEntityRenderer<T, LionModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T lionEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (lionEntity.hasMane() && !lionEntity.isInvisible()) {
            renderCopyCutoutModel(this.getEntityModel(), this.maneModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, lionEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
        }
    }
}
