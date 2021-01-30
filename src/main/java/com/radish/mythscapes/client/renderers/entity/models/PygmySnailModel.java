package com.radish.mythscapes.client.renderers.entity.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * Pygmy Snail - Echolite
 */
public class PygmySnailModel<T extends SnailEntity> extends EntityModel<T> {
    
    private final ModelRenderer pygmySnail;
    private final ModelRenderer snail;
    private final ModelRenderer shell;
    private final ModelRenderer overgrowth;
    private final ModelRenderer overgrowthCross;

    public PygmySnailModel() {
        textureWidth = 32;
        textureHeight = 32;

        pygmySnail = new ModelRenderer(this);
        pygmySnail.setRotationPoint(0.0F, 24.0F, 0.0F);

        snail = new ModelRenderer(this);
        snail.setRotationPoint(0.0F, 0.0F, 0.0F);
        pygmySnail.addChild(snail);
        snail.setTextureOffset(0, 0).addBox(-1.5F, -4.0F, -5.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        snail.setTextureOffset(0, 3).addBox(0.5F, -4.0F, -5.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        snail.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -5.5F, 3.0F, 2.0F, 7.0F, 0.0F, false);
        snail.setTextureOffset(18, 7).addBox(-1.5F, -1.0F, 1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);

        shell = new ModelRenderer(this);
        shell.setRotationPoint(0.0F, 0.0F, 0.0F);
        snail.addChild(shell);
        shell.setTextureOffset(0, 13).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

        overgrowth = new ModelRenderer(this);
        overgrowth.setRotationPoint(-1.0F, 0.0F, 0.5F);
        shell.addChild(overgrowth);
        overgrowth.setTextureOffset(0, 14).addBox(1.0F, -7.0F, -4.0F, 0.0F, 5.0F, 9.0F, 0.0F, false);

        overgrowthCross = new ModelRenderer(this);
        overgrowthCross.setRotationPoint(1.0F, -5.0F, 0.0F);
        overgrowth.addChild(overgrowthCross);
        setRotationAngle(overgrowthCross, 0.0F, 0.7854F, 0.0F);
        overgrowthCross.setTextureOffset(13, 0).addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 0.0F, 0.0F, false);
        overgrowthCross.setTextureOffset(15, 8).addBox(0.0F, -5.0F, -3.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        pygmySnail.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
    }
}
