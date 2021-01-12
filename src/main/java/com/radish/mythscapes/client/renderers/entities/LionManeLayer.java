package com.radish.mythscapes.client.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.client.renderers.entities.models.LionManeModel;
import com.radish.mythscapes.client.renderers.entities.models.LionModel;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.LionEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LionManeLayer extends LayerRenderer<LionEntity, LionModel<LionEntity>> {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/lion/mane.png");
    private final LionManeModel<LionEntity> maneModel = new LionManeModel<>();

    public LionManeLayer(IEntityRenderer<LionEntity, LionModel<LionEntity>> renderer) {
        super(renderer);
    }

    //  protected static <T extends LivingEntity> void renderCopyCutoutModel(EntityModel<T> modelParentIn, EntityModel<T> modelIn, ResourceLocation textureLocationIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks, float red, float green, float blue) {


    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, LionEntity lionEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (lionEntity.hasMane() && !lionEntity.isChild() && !lionEntity.isInvisible()) {
            renderCopyCutoutModel(this.getEntityModel(), this.maneModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, lionEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0f, 1.0f, 1.0f);
        }
    }
}
