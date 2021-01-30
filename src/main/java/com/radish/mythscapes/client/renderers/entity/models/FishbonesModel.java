package com.radish.mythscapes.client.renderers.entity.models;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.entities.living.FishbonesEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * Fishbones - Hobojoe
 * Created using Tabula 7.1.0
 */
public class FishbonesModel<T extends FishbonesEntity> extends SegmentedModel<T> {
    public ModelRenderer body;
    public ModelRenderer fin;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer jaw;

    public FishbonesModel() {
        super();
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.legLeft = new ModelRenderer(this, 26, 2);
        this.legLeft.setRotationPoint(1.5F, 5.5F, 0.0F);
        this.legLeft.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);

        this.body = new ModelRenderer(this, 0, 2);
        this.body.setRotationPoint(0.0F, 8.5F, 0.0F);
        this.body.addBox(-2.5F, -5.5F, -2.0F, 5, 11, 4, 0.0F);

        this.fin = new ModelRenderer(this, 0, 18);
        this.fin.setRotationPoint(0.0F, -1.0F, 2.0F);
        this.fin.addBox(0.0F, -2.5F, 0.0F, 0, 5, 2, 0.0F);

        this.tail = new ModelRenderer(this, 32, 17);
        this.tail.setRotationPoint(0.5F, 4.7F, 1.5F);
        this.tail.addBox(-0.5F, -0.5F, -2.5F, 0, 5, 5, 0.0F);
        this.setRotateAngle(tail, 0.7285004297824331F, 0.0F, 0.0F);

        this.legRight = new ModelRenderer(this, 18, 2);
        this.legRight.setRotationPoint(-1.5F, 5.5F, 0.0F);
        this.legRight.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);

        this.armRight = new ModelRenderer(this, 16, 19);
        this.armRight.setRotationPoint(-2.5F, -4.0F, 0.0F);
        this.armRight.addBox(-2.0F, -1.5F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(armRight, 0.0F, 0.0F, 0.05235987755982988F);

        this.head = new ModelRenderer(this, 0, 21);
        this.head.setRotationPoint(0.0F, -6.8F, 0.5F);
        this.head.addBox(-2.0F, -5.5F, -2.0F, 4, 7, 4, 0.0F);

        this.jaw = new ModelRenderer(this, 32, 27);
        this.jaw.setRotationPoint(0.0F, -2.0F, -2.0F);
        this.jaw.addBox(-1.5F, -3.0F, -1.0F, 3, 4, 1, 0.0F);
        this.setRotateAngle(jaw, 0.6981317007977318F, 0.0F, 0.0F);

        this.armLeft = new ModelRenderer(this, 24, 19);
        this.armLeft.setRotationPoint(2.5F, -4.0F, 0.0F);
        this.armLeft.addBox(0.0F, -1.5F, -1.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(armLeft, 0.0F, 0.0F, -0.05235987755982988F);

        this.body.addChild(this.legLeft);
        this.head.addChild(this.jaw);
        this.body.addChild(this.fin);
        this.body.addChild(this.tail);
        this.body.addChild(this.legRight);
        this.body.addChild(this.armRight);
        this.body.addChild(this.head);
        this.body.addChild(this.armLeft);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        this.head.rotateAngleX = (headPitch * ((float)Math.PI / 180F)) + 1.0F;
        this.head.rotateAngleY = headYaw * ((float)Math.PI / 180F);
        this.legLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

        float armAngle = entity.isAggressive() ? -((float)Math.PI / 2) : 0.0F;

        this.armLeft.rotateAngleX = armAngle;
        this.armRight.rotateAngleX = armAngle;
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
