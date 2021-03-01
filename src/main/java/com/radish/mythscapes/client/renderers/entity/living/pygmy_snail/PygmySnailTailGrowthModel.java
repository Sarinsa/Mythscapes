package com.radish.mythscapes.client.renderers.entity.living.pygmy_snail;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PygmySnailTailGrowthModel<T extends SnailEntity> extends EntityModel<T> {

    private final ModelRenderer tailGrowth;

    public PygmySnailTailGrowthModel() {
        this.textureHeight = 32;
        this.textureWidth = 32;

        tailGrowth = new ModelRenderer(this);
        tailGrowth.setRotationPoint(-1.0F, 0.0F, 0.5F);
        tailGrowth.setTextureOffset(0, 14).addBox(1.0F, -7.0F, -4.0F, 0.0F, 5.0F, 9.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.tailGrowth.setRotationPoint(-1.0F, 25.0F, 0.5F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.tailGrowth.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green ,blue, alpha);
    }
}
