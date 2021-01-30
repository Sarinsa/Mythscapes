package com.radish.mythscapes.client.renderers.entity.models;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.entities.misc.MythBoatEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

//
//  MORE COPY PASTA
//
public class MythBoatModel extends SegmentedModel<MythBoatEntity> {

    private final ModelRenderer[] paddles = new ModelRenderer[2];
    private final ModelRenderer noWater;
    private final ImmutableList<ModelRenderer> modelRenderers;

    public MythBoatModel() {
        ModelRenderer[] modelRenderers = new ModelRenderer[]{(new ModelRenderer(this, 0, 0)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 19)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 27)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 35)).setTextureSize(128, 64), (new ModelRenderer(this, 0, 43)).setTextureSize(128, 64)};
        modelRenderers[0].addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
        modelRenderers[0].setRotationPoint(0.0F, 3.0F, 1.0F);
        modelRenderers[1].addBox(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[1].setRotationPoint(-15.0F, 4.0F, 4.0F);
        modelRenderers[2].addBox(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[2].setRotationPoint(15.0F, 4.0F, 0.0F);
        modelRenderers[3].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[3].setRotationPoint(0.0F, 4.0F, -9.0F);
        modelRenderers[4].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[4].setRotationPoint(0.0F, 4.0F, 9.0F);
        modelRenderers[0].rotateAngleX = 1.5707964F;
        modelRenderers[1].rotateAngleY = 4.712389F;
        modelRenderers[2].rotateAngleY = 1.5707964F;
        modelRenderers[3].rotateAngleY = 3.1415927F;
        this.paddles[0] = this.makePaddle(true);
        this.paddles[0].setRotationPoint(3.0F, -5.0F, 9.0F);
        this.paddles[1] = this.makePaddle(false);
        this.paddles[1].setRotationPoint(3.0F, -5.0F, -9.0F);
        this.paddles[1].rotateAngleY = 3.1415927F;
        this.paddles[0].rotateAngleZ = 0.19634955F;
        this.paddles[1].rotateAngleZ = 0.19634955F;
        this.noWater = (new ModelRenderer(this, 0, 0)).setTextureSize(128, 64);
        this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
        this.noWater.setRotationPoint(0.0F, -3.0F, 1.0F);
        this.noWater.rotateAngleX = 1.5707964F;
        ImmutableList.Builder<ModelRenderer> modelList = ImmutableList.builder();
        modelList.addAll(Arrays.asList(modelRenderers));
        modelList.addAll(Arrays.asList(this.paddles));
        this.modelRenderers = modelList.build();
    }

    public void setRotationAngles(MythBoatEntity entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.func_228244_a_(entity, 0, p_225597_2_);
        this.func_228244_a_(entity, 1, p_225597_2_);
    }

    @Override
    public ImmutableList<ModelRenderer> getParts() {
        return this.modelRenderers;
    }

    public ModelRenderer func_228245_c_() {
        return this.noWater;
    }

    protected ModelRenderer makePaddle(boolean paddle) {
        ModelRenderer modelRenderer = (new ModelRenderer(this, 62, paddle ? 0 : 20)).setTextureSize(128, 64);
        modelRenderer.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
        modelRenderer.addBox(paddle ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
        return modelRenderer;
    }

    protected void func_228244_a_(MythBoatEntity entity, int i, float j) {
        float rowingTime = entity.getRowingTime(i, j);
        ModelRenderer modelRenderer = this.paddles[i];
        modelRenderer.rotateAngleX = (float) MathHelper.clampedLerp(-1.0471975803375244D, -0.2617993950843811D, ((MathHelper.sin(-rowingTime) + 1.0F) / 2.0F));
        modelRenderer.rotateAngleY = (float)MathHelper.clampedLerp(-0.7853981852531433D, 0.7853981852531433D, ((MathHelper.sin(-rowingTime + 1.0F) + 1.0F) / 2.0F));
        if (i == 1) {
            modelRenderer.rotateAngleY = 3.1415927F - modelRenderer.rotateAngleY;
        }
    }
}
