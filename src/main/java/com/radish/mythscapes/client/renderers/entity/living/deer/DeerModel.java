package com.radish.mythscapes.client.renderers.entity.living.deer;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.entities.living.DeerEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Deer - Durger King
 * Created using Tabula 7.1.0
 */
public class DeerModel<T extends DeerEntity> extends AgeableModel<T> {

    public ModelRenderer body;
    public ModelRenderer frontLeftLeg;
    public ModelRenderer frontRightLeg;
    public ModelRenderer backRightLeg;
    public ModelRenderer backLeftLeg;
    public ModelRenderer tail;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer snoot;
    public ModelRenderer antlerLeft;
    public ModelRenderer antlerRight;

    public DeerModel() {
        super(true, 17.0F, 2.5F);
        this.texWidth = 77;
        this.texHeight = 32;

        this.antlerLeft = new ModelRenderer(this, 34, -5);
        this.antlerLeft.setPos(2.5F, -13.9F, 0.5F);
        this.antlerLeft.addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antlerLeft, 0.0F, 0.0F, 0.13962634015954636F);

        this.antlerRight = new ModelRenderer(this, 34, -5);
        this.antlerRight.setPos(-2.5F, -13.9F, 0.5F);
        this.antlerRight.addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antlerRight, 0.0F, 0.0F, -0.13962634015954636F);

        this.frontRightLeg = new ModelRenderer(this, 0, 0);
        this.frontRightLeg.setPos(1.5F, 8.0F, 1.5F);
        this.frontRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.neck = new ModelRenderer(this, 52, 20);
        this.neck.setPos(4.0F, 2.0F, 2.0F);
        this.neck.addBox(-2.0F, -6.0F, -4.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);

        this.tail = new ModelRenderer(this, 67, 19);
        this.tail.setPos(4.0F, 0.0F, 18.0F);
        this.tail.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.4363323129985824F, 1.9764588155382245E-16F, 0.0F);

        this.body = new ModelRenderer(this, 0, 6);
        this.body.setPos(-4.0F, 4.0F, -9.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 18.0F, 0.0F, 0.0F, 0.0F);

        this.head = new ModelRenderer(this, 34, 13);
        this.head.setPos(0.0F, 0.0F, -10.5F);
        this.head.addBox(-2.0F, -4.0F, -3.5F, 4.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);

        this.backRightLeg = new ModelRenderer(this, 61, 0);
        this.backRightLeg.setPos(1.5F, 8.0F, 15.5F);
        this.backRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.backLeftLeg = new ModelRenderer(this, 61, 0);
        this.backLeftLeg.mirror = true;
        this.backLeftLeg.setPos(6.5F, 8.0F, 15.5F);
        this.backLeftLeg.addBox(-1.6F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.snoot = new ModelRenderer(this, 0, 18);
        this.snoot.setPos(-2.0F, -3.0F, -6.5F);
        this.snoot.addBox(0.0F, 0.0F, 0.0F, 4.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.frontLeftLeg = new ModelRenderer(this, 0, 0);
        this.frontLeftLeg.mirror = true;
        this.frontLeftLeg.setPos(6.5F, 8.0F, 1.5F);
        this.frontLeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.head.addChild(this.antlerLeft);
        this.head.addChild(this.antlerRight);
        this.body.addChild(this.frontRightLeg);
        this.body.addChild(this.neck);
        this.body.addChild(this.tail);
        this.body.addChild(this.backRightLeg);
        this.body.addChild(this.backLeftLeg);
        this.head.addChild(this.snoot);
        this.body.addChild(this.frontLeftLeg);
    }

    @Override
    public void setupAnim(T deer, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);

        this.setAntlerVisibility(deer);

        this.backRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontRightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    private void setAntlerVisibility(T deer) {
        boolean visibleAntlers = deer.hasAntlers();
        this.antlerRight.visible = visibleAntlers;
        this.antlerLeft.visible = visibleAntlers;
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(this.body);
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
