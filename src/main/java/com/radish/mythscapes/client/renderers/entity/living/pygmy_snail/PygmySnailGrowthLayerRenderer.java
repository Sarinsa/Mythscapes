package com.radish.mythscapes.client.renderers.entity.living.pygmy_snail;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class PygmySnailGrowthLayerRenderer<T extends SnailEntity, M extends PygmySnailModel<T>> extends LayerRenderer<T, M> {

    private final PygmySnailBackGrowthModel<T> backGrowthModel = new PygmySnailBackGrowthModel<>();
    private final PygmySnailTailGrowthModel<T> tailGrowthModel = new PygmySnailTailGrowthModel<>();

    public PygmySnailGrowthLayerRenderer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T snailEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ISnailType snailType = snailEntity.getSnailType();

        switch (snailType.getGrowthRenderType()) {
            case TAIL:
                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.tailGrowthModel, this.getTextureLocation(snailEntity), matrixStackIn, bufferIn, packedLightIn, snailEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
                break;
            case BACK:
                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.backGrowthModel, this.getTextureLocation(snailEntity), matrixStackIn, bufferIn, packedLightIn, snailEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
                break;
            case BOTH:
                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.backGrowthModel, this.getTextureLocation(snailEntity), matrixStackIn, bufferIn, packedLightIn, snailEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
                coloredCutoutModelCopyLayerRender(this.getParentModel(), this.tailGrowthModel, this.getTextureLocation(snailEntity), matrixStackIn, bufferIn, packedLightIn, snailEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
                break;
            default:
        }
    }
}
