package com.mythscapes.client.renderers.entities.model;

import com.google.common.collect.ImmutableList;
import com.mythscapes.common.entities.living.lion.LionEntity;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Lion - Durger King
 * Created using Tabula 7.1.0
 */
@OnlyIn(Dist.CLIENT)
public class LionModel<T extends LionEntity> extends AgeableModel<T> {
    
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer tail;
    public ModelRenderer frontleftleg;
    public ModelRenderer frontrightleg;
    public ModelRenderer backleftleg;
    public ModelRenderer backrightleg;
    public ModelRenderer muzzle;
    public ModelRenderer mane;
    public ModelRenderer earright;
    public ModelRenderer earleft;

    public LionModel() {
        this.textureWidth = 96;
        this.textureHeight = 64;

        this.head = new ModelRenderer(this, 46, 0);
        this.head.setRotationPoint(0.0F, 8.0F, -14.0F);
        this.head.addBox(-5.0F, -7.0F, -9.0F, 10, 9, 9, 0.0F);

        this.tail = new ModelRenderer(this, 46, 45);
        this.tail.setRotationPoint(4.0F, 0.0F, 25.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 2, 2, 14, 0.0F);
        this.setRotateAngle(tail, -0.8850564636863244F, 0.0F, 0.0F);

        this.backrightleg = new ModelRenderer(this, 0, 0);
        this.backrightleg.setRotationPoint(6.0F, 10.0F, 23.0F);
        this.backrightleg.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.earleft = new ModelRenderer(this, 0, 14);
        this.earleft.setRotationPoint(7.0F, -2.0F, 2.0F);
        this.earleft.addBox(-5.0F, -7.0F, -10.0F, 3, 2, 2, 0.0F);

        this.backleftleg = new ModelRenderer(this, 0, 0);
        this.backleftleg.mirror = true;
        this.backleftleg.setRotationPoint(0.0F, 10.0F, 23.0F);
        this.backleftleg.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.earright = new ModelRenderer(this, 0, 14);
        this.earright.mirror = true;
        this.earright.setRotationPoint(0.0F, -2.0F, 2.0F);
        this.earright.addBox(-5.0F, -7.0F, -10.0F, 3, 2, 2, 0.0F);

        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-5.0F, 4.0F, -14.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 10, 10, 25, 0.0F);

        this.frontrightleg = new ModelRenderer(this, 0, 0);
        this.frontrightleg.mirror = true;
        this.frontrightleg.setRotationPoint(0.0F, 10.0F, 3.0F);
        this.frontrightleg.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.muzzle = new ModelRenderer(this, 45, 18);
        this.muzzle.setRotationPoint(2.0F, 5.0F, -2.0F);
        this.muzzle.addBox(-5.0F, -7.0F, -9.1F, 6, 5, 2, 0.0F);

        this.frontleftleg = new ModelRenderer(this, 0, 0);
        this.frontleftleg.setRotationPoint(6.0F, 10.0F, 3.0F);
        this.frontleftleg.addBox(0.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

        this.mane = new ModelRenderer(this, 0, 35);
        this.mane.setRotationPoint(-7.0F, -4.0F, 3.0F);
        this.mane.addBox(0.0F, -7.0F, -10.3F, 14, 17, 9, 0.0F);

        this.body.addChild(this.tail);
        this.body.addChild(this.backrightleg);
        this.head.addChild(this.earleft);
        this.body.addChild(this.backleftleg);
        this.head.addChild(this.earright);
        this.body.addChild(this.frontrightleg);
        this.head.addChild(this.muzzle);
        this.body.addChild(this.frontleftleg);
        this.head.addChild(this.mane);
    }

    @Override
    public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
        if (entity.isLying()) {

        }
    }

    @ParametersAreNonnullByDefault
    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = headYaw * ((float)Math.PI / 180F);
        this.backrightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backleftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.frontrightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
