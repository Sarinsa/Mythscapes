package com.radish.mythscapes.client.renderers.entity.living.lion;

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
    public ModelRenderer earright;
    public ModelRenderer earleft;

    public LionModel() {
        super(true, 13.0F, 5.0F);
        this.texWidth = 96;
        this.texHeight = 64;

        this.headModel = new ModelRenderer(this, 46, 0);
        this.headModel.setPos(0.0F, 8.0F, -14.0F);
        this.headModel.addBox(-5.0F, -7.0F, -9.0F, 10, 9, 9, 0.0F);

        this.tail = new ModelRenderer(this, 46, 45);
        this.tail.setPos(4.0F, 0.0F, 25.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 2, 2, 14, 0.0F);
        this.setRotateAngle(tail, -0.8850564636863244F, 0.0F, 0.0F);

        this.legBackLeft = new ModelRenderer(this, 0, 0);
        this.legBackLeft.setPos(6.0F, 10.0F, 23.0F);
        this.legBackLeft.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.earleft = new ModelRenderer(this, 0, 14);
        this.earleft.setPos(7.0F, -2.0F, 2.0F);
        this.earleft.addBox(-5.0F, -7.0F, -10.0F, 3, 2, 2, 0.0F);

        this.legBackRight = new ModelRenderer(this, 0, 0);
        this.legBackRight.mirror = true;
        this.legBackRight.setPos(0.0F, 10.0F, 23.0F);
        this.legBackRight.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.earright = new ModelRenderer(this, 0, 14);
        this.earright.mirror = true;
        this.earright.setPos(0.0F, -2.0F, 2.0F);
        this.earright.addBox(-5.0F, -7.0F, -10.0F, 3, 2, 2, 0.0F);

        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(-5.0F, 4.0F, -14.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 10, 10, 25, 0.0F);

        this.legFrontRight = new ModelRenderer(this, 0, 0);
        this.legFrontRight.mirror = true;
        this.legFrontRight.setPos(0.0F, 10.0F, 3.0F);
        this.legFrontRight.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.muzzle = new ModelRenderer(this, 45, 18);
        this.muzzle.setPos(2.0F, 5.0F, -2.0F);
        this.muzzle.addBox(-5.0F, -7.0F, -9.1F, 6, 5, 2, 0.0F);

        this.legFrontLeft = new ModelRenderer(this, 0, 0);
        this.legFrontLeft.setPos(6.0F, 10.0F, 3.0F);
        this.legFrontLeft.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.body.addChild(this.tail);
        this.body.addChild(this.legBackRight);
        this.body.addChild(this.legBackLeft);
        this.body.addChild(this.legFrontRight);
        this.body.addChild(this.legFrontLeft);
        this.headModel.addChild(this.earleft);
        this.headModel.addChild(this.earright);
        this.headModel.addChild(this.muzzle);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.headModel);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body);
    }

    @Override
    public void setupAnim(T lion, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (lion.isLying()) {
            this.headModel.setPos(-0.5F, 16.4F, -11.0F);
            this.body.setPos(-7.0F, 24.0F, -10.0F);
            this.tail.setPos(4.0F, 0.0F, 25.0F);
            this.earleft.setPos(7.0F, -2.0F, 2.0F);
            this.earright.setPos(0.0F, -2.0F, 2.0F);
            this.legBackLeft.setPos(0.0F, 10.0F, 23.0F);
            this.legBackRight.setPos(6.0F, 10.0F, 23.0F);
            this.legFrontLeft.setPos(2.0F, -0.7F, -0.5F);
            this.legFrontRight.setPos(2.0F, 6.0F, 2.4F);
            this.muzzle.setPos(2.0F, 5.0F, -2.0F);
            this.setRotateAngle(body, -0.27314402793711257F, 0.0F, -1.5707963267948966F);
            this.setRotateAngle(tail, -0.8850564636863244F, -0.091106186954104F, 0.31869712141416456F);
            this.setRotateAngle(legBackLeft, 0.40980330836826856F, 0.0F, 0.0F);
            this.setRotateAngle(legBackRight, 0.9773843811168246F, -0.4363323129985824F, 0.22689280275926282F);
            this.setRotateAngle(legFrontLeft, -1.593485607070823F, -0.22759093446006054F, 1.5707963267948966F);
            this.setRotateAngle(headModel, 0.0F, -0.4553564018453205F, 0.0F);
            this.setRotateAngle(legFrontRight, -1.593485607070823F, -0.40980330836826856F, 1.6390387005478748F);
        }
        else {
            this.headModel.setPos(0.0F, 8.0F, -14.0F);
            this.tail.setPos(4.0F, 0.0F, 25.0F);
            this.legBackLeft.setPos(6.0F, 10.0F, 23.0F);
            this.earleft.setPos(7.0F, -2.0F, 2.0F);
            this.legBackRight.setPos(0.0F, 10.0F, 23.0F);
            this.earright.setPos(0.0F, -2.0F, 2.0F);
            this.body.setPos(-5.0F, 4.0F, -14.0F);
            this.legFrontRight.setPos(0.0F, 10.0F, 3.0F);
            this.muzzle.setPos(2.0F, 5.0F, -2.0F);
            this.legFrontLeft.setPos(6.0F, 10.0F, 3.0F);
            this.setRotateAngle(headModel, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(body, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(tail, -0.8850564636863244F, 0.0F, 0.0F);
            this.setRotateAngle(earleft, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(earright, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(legBackLeft, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(legBackRight, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(legFrontLeft, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(legFrontRight, 0.0F, 0.0F, 0.0F);
            this.setRotateAngle(muzzle, 0.0F, 0.0F, 0.0F);

            this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
        this.headModel.xRot = headPitch * ((float)Math.PI / 180F);
        this.headModel.yRot = netHeadYaw * ((float)Math.PI / 180F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
