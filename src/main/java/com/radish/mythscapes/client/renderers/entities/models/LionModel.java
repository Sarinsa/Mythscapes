package com.radish.mythscapes.client.renderers.entities.models;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.entities.living.LionEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Lion - Durger King
 * Created using Tabula 7.1.0
 */
public class LionModel<T extends LionEntity> extends AgeableModel<T> {

    public ModelRenderer headModel;
    public ModelRenderer body;
    public ModelRenderer legBackRight;
    public ModelRenderer legBackLeft;
    public ModelRenderer legFrontRight;
    public ModelRenderer legFrontLeft;
    public ModelRenderer tail;
    public ModelRenderer muzzle;
    public ModelRenderer mane;
    public ModelRenderer earright;
    public ModelRenderer earleft;

    public LionModel() {
        super(true, 13.0F, 5.0F);
        this.textureWidth = 96;
        this.textureHeight = 64;

        this.headModel = new ModelRenderer(this, 46, 0);
        this.headModel.setRotationPoint(0.0F, 8.0F, -14.0F);
        this.headModel.addBox(-5.0F, -7.0F, -9.0F, 10, 9, 9, 0.0F);

        this.tail = new ModelRenderer(this, 46, 45);
        this.tail.setRotationPoint(4.0F, 0.0F, 25.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 2, 2, 14, 0.0F);
        this.setRotateAngle(tail, -0.8850564636863244F, 0.0F, 0.0F);

        this.legBackLeft = new ModelRenderer(this, 0, 0);
        this.legBackLeft.setRotationPoint(6.0F, 10.0F, 23.0F);
        this.legBackLeft.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.earleft = new ModelRenderer(this, 0, 14);
        this.earleft.setRotationPoint(7.0F, -2.0F, 2.0F);
        this.earleft.addBox(-5.0F, -7.0F, -10.0F, 3, 2, 2, 0.0F);

        this.legBackRight = new ModelRenderer(this, 0, 0);
        this.legBackRight.mirror = true;
        this.legBackRight.setRotationPoint(0.0F, 10.0F, 23.0F);
        this.legBackRight.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.earright = new ModelRenderer(this, 0, 14);
        this.earright.mirror = true;
        this.earright.setRotationPoint(0.0F, -2.0F, 2.0F);
        this.earright.addBox(-5.0F, -7.0F, -10.0F, 3, 2, 2, 0.0F);

        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-5.0F, 4.0F, -14.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 10, 10, 25, 0.0F);

        this.legFrontRight = new ModelRenderer(this, 0, 0);
        this.legFrontRight.mirror = true;
        this.legFrontRight.setRotationPoint(0.0F, 10.0F, 3.0F);
        this.legFrontRight.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.muzzle = new ModelRenderer(this, 45, 18);
        this.muzzle.setRotationPoint(2.0F, 5.0F, -2.0F);
        this.muzzle.addBox(-5.0F, -7.0F, -9.1F, 6, 5, 2, 0.0F);

        this.legFrontLeft = new ModelRenderer(this, 0, 0);
        this.legFrontLeft.setRotationPoint(6.0F, 10.0F, 3.0F);
        this.legFrontLeft.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.mane = new ModelRenderer(this, 0, 35);
        this.mane.setRotationPoint(-7.0F, -4.0F, 3.0F);
        this.mane.addBox(0.0F, -7.0F, -10.3F, 14, 17, 9, 0.0F);

        this.body.addChild(this.tail);
        this.body.addChild(this.legBackRight);
        this.body.addChild(this.legBackLeft);
        this.body.addChild(this.legFrontRight);
        this.body.addChild(this.legFrontLeft);
        this.headModel.addChild(this.earleft);
        this.headModel.addChild(this.earright);
        this.headModel.addChild(this.muzzle);
        this.headModel.addChild(this.mane);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.headModel);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.body);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.headModel.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.headModel.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

        if (entityIn.isLying()) {
            this.legFrontRight.rotateAngleX = 90;
            this.legFrontLeft.rotateAngleX = 90;
            this.legBackLeft.rotateAngleX = 90;
            this.legBackRight.rotateAngleX = 90;
        }
        else {
            this.legBackRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
