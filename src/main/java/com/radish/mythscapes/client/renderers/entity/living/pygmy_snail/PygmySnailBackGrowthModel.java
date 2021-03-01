package com.radish.mythscapes.client.renderers.entity.living.pygmy_snail;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PygmySnailBackGrowthModel<T extends SnailEntity> extends EntityModel<T> {

    private final ModelRenderer backGrowth;

    public PygmySnailBackGrowthModel() {
        this.textureHeight = 32;
        this.textureWidth = 32;

        backGrowth = new ModelRenderer(this);
        backGrowth.setRotationPoint(0.0F, 19.0F, 0.0F);
        setRotationAngle(backGrowth, 0.0F, 0.7854F, 0.0F);
        backGrowth.setTextureOffset(13, 0).addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);
        backGrowth.setTextureOffset(15, 8).addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.backGrowth.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
