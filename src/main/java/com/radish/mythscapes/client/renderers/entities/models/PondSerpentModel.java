package com.radish.mythscapes.client.renderers.entities.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Pond Serpent - Durger King
 */
public class PondSerpentModel<T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer body1;
    private final ModelRenderer head;
    private final ModelRenderer horn;
    private final ModelRenderer body2;
    private final ModelRenderer body3;
    private final ModelRenderer body4;
    private final ModelRenderer fluke;
    private final ModelRenderer dorsal;
    private final ModelRenderer rightFin;
    private final ModelRenderer leftFin;

    public PondSerpentModel() {
        this.textureWidth = 16;
        this.textureHeight = 32;

        this.horn = new ModelRenderer(this, 0, 20);
        this.horn.setRotationPoint(0.0F, -2.0F, -3.0F);
        this.horn.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);

        this.body2 = new ModelRenderer(this, 0, 10);
        this.body2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.body2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);

        this.leftFin = new ModelRenderer(this, 0, 19);
        this.leftFin.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.leftFin.addBox(0.0F, 0.0F, 0.0F, 0, 1, 3, 0.0F);
        this.setRotateAngle(leftFin, -0.136659280431156F, 0.136659280431156F, 0.0F);

        this.body4 = new ModelRenderer(this, 0, 10);
        this.body4.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.body4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);

        this.rightFin = new ModelRenderer(this, 0, 19);
        this.rightFin.mirror = true;
        this.rightFin.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.rightFin.addBox(0.0F, 0.0F, 0.0F, 0, 1, 3, 0.0F);
        this.setRotateAngle(rightFin, -0.136659280431156F, -0.136659280431156F, 0.0F);

        this.fluke = new ModelRenderer(this, 0, 12);
        this.fluke.setRotationPoint(1.0F, -1.0F, 0.9F);
        this.fluke.addBox(-1.0F, -1.0F, 0.0F, 0, 3, 3, 0.0F);

        this.dorsal = new ModelRenderer(this, 0, 16);
        this.dorsal.setRotationPoint(0.0F, -2.0F, -3.0F);
        this.dorsal.addBox(0.0F, 0.0F, 0.0F, 0, 1, 3, 0.0F);

        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 23.0F, -5.0F);
        this.head.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);

        this.body1 = new ModelRenderer(this, 0, 5);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);

        this.body3 = new ModelRenderer(this, 0, 5);
        this.body3.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.body3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);

        this.head.addChild(this.horn);
        this.body1.addChild(this.body2);
        this.body1.addChild(this.leftFin);
        this.body3.addChild(this.body4);
        this.body1.addChild(this.rightFin);
        this.body4.addChild(this.fluke);
        this.body2.addChild(this.dorsal);
        this.head.addChild(this.body1);
        this.body2.addChild(this.body3);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        body1.rotateAngleY = 0.25f * MathHelper.sin(0.6f * ageInTicks);
        body2.rotateAngleY = 0.25f * MathHelper.sin(0.6f * ageInTicks);
        body3.rotateAngleY = 0.25f * MathHelper.sin(0.6f * ageInTicks);
        body4.rotateAngleY = 0.25f * MathHelper.sin(0.6f * ageInTicks);
    }

    @Nonnull
    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(head);
    }
}