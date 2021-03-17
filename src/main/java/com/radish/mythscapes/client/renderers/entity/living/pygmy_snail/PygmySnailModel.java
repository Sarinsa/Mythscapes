package com.radish.mythscapes.client.renderers.entity.living.pygmy_snail;

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

    public PygmySnailModel() {
        texWidth = 32;
        texHeight = 32;

        pygmySnail = new ModelRenderer(this);
        pygmySnail.setPos(0.0F, 24.0F, 0.0F);

        snail = new ModelRenderer(this);
        snail.setPos(0.0F, 0.0F, 0.0F);
        pygmySnail.addChild(snail);
        snail.texOffs(0, 0).addBox(-1.5F, -4.0F, -5.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        snail.texOffs(0, 3).addBox(0.5F, -4.0F, -5.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        snail.texOffs(0, 0).addBox(-1.5F, -2.0F, -5.5F, 3.0F, 2.0F, 7.0F, 0.0F, false);
        snail.texOffs(18, 7).addBox(-1.5F, -1.0F, 1.5F, 3.0F, 1.0F, 2.0F, 0.0F, false);

        shell = new ModelRenderer(this);
        shell.setPos(0.0F, 0.0F, 0.0F);
        snail.addChild(shell);
        shell.texOffs(0, 13).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        pygmySnail.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        
    }
}
