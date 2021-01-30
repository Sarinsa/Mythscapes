package com.radish.mythscapes.client.renderers.entity.models;

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
    public ModelRenderer frontleftleg;
    public ModelRenderer frontrightleg;
    public ModelRenderer backrightleg;
    public ModelRenderer backleftleg;
    public ModelRenderer tail;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer snoot;
    public ModelRenderer antlerleft;
    public ModelRenderer antlerright;

    public DeerModel() {
        super(true, 17.0F, 2.5F);
        this.textureWidth = 77;
        this.textureHeight = 32;

        this.antlerleft = new ModelRenderer(this, 34, -5);
        this.antlerleft.setRotationPoint(2.5F, -13.9F, 0.5F);
        this.antlerleft.addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antlerleft, 0.0F, 0.0F, 0.13962634015954636F);

        this.antlerright = new ModelRenderer(this, 34, -5);
        this.antlerright.setRotationPoint(-2.5F, -13.9F, 0.5F);
        this.antlerright.addBox(0.0F, 0.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antlerright, 0.0F, 0.0F, -0.13962634015954636F);

        this.frontrightleg = new ModelRenderer(this, 0, 0);
        this.frontrightleg.setRotationPoint(1.5F, 8.0F, 1.5F);
        this.frontrightleg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.neck = new ModelRenderer(this, 52, 20);
        this.neck.setRotationPoint(4.0F, 2.0F, 2.0F);
        this.neck.addBox(-2.0F, -6.0F, -4.0F, 4.0F, 8.0F, 4.0F, 0.0F, 0.0F, 0.0F);

        this.tail = new ModelRenderer(this, 67, 19);
        this.tail.setRotationPoint(4.0F, 0.0F, 18.0F);
        this.tail.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.4363323129985824F, 1.9764588155382245E-16F, 0.0F);

        this.body = new ModelRenderer(this, 0, 6);
        this.body.setRotationPoint(-4.0F, 4.0F, -9.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 18.0F, 0.0F, 0.0F, 0.0F);

        this.head = new ModelRenderer(this, 34, 13);
        this.head.setRotationPoint(0.0F, 0.0F, -10.5F);
        this.head.addBox(-2.0F, -4.0F, -3.5F, 4.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);

        this.backrightleg = new ModelRenderer(this, 61, 0);
        this.backrightleg.setRotationPoint(1.5F, 8.0F, 15.5F);
        this.backrightleg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.backleftleg = new ModelRenderer(this, 61, 0);
        this.backleftleg.mirror = true;
        this.backleftleg.setRotationPoint(6.5F, 8.0F, 15.5F);
        this.backleftleg.addBox(-1.6F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.snoot = new ModelRenderer(this, 0, 18);
        this.snoot.setRotationPoint(-2.0F, -3.0F, -6.5F);
        this.snoot.addBox(0.0F, 0.0F, 0.0F, 4.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.frontleftleg = new ModelRenderer(this, 0, 0);
        this.frontleftleg.mirror = true;
        this.frontleftleg.setRotationPoint(6.5F, 8.0F, 1.5F);
        this.frontleftleg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);

        this.head.addChild(this.antlerleft);
        this.head.addChild(this.antlerright);
        this.body.addChild(this.frontrightleg);
        this.body.addChild(this.neck);
        this.body.addChild(this.tail);
        this.body.addChild(this.backrightleg);
        this.body.addChild(this.backleftleg);
        this.head.addChild(this.snoot);
        this.body.addChild(this.frontleftleg);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

        this.backrightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backleftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontrightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontleftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(this.body);
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
