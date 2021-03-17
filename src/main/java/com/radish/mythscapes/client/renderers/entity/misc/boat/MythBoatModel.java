package com.radish.mythscapes.client.renderers.entity.misc.boat;

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
        ModelRenderer[] modelRenderers = new ModelRenderer[]{(new ModelRenderer(this, 0, 0)).setTexSize(128, 64), (new ModelRenderer(this, 0, 19)).setTexSize(128, 64), (new ModelRenderer(this, 0, 27)).setTexSize(128, 64), (new ModelRenderer(this, 0, 35)).setTexSize(128, 64), (new ModelRenderer(this, 0, 43)).setTexSize(128, 64)};
        modelRenderers[0].addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
        modelRenderers[0].setPos(0.0F, 3.0F, 1.0F);
        modelRenderers[1].addBox(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[1].setPos(-15.0F, 4.0F, 4.0F);
        modelRenderers[2].addBox(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[2].setPos(15.0F, 4.0F, 0.0F);
        modelRenderers[3].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[3].setPos(0.0F, 4.0F, -9.0F);
        modelRenderers[4].addBox(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
        modelRenderers[4].setPos(0.0F, 4.0F, 9.0F);
        modelRenderers[0].xRot = 1.5707964F;
        modelRenderers[1].yRot = 4.712389F;
        modelRenderers[2].yRot = 1.5707964F;
        modelRenderers[3].yRot = 3.1415927F;
        this.paddles[0] = this.makePaddle(true);
        this.paddles[0].setPos(3.0F, -5.0F, 9.0F);
        this.paddles[1] = this.makePaddle(false);
        this.paddles[1].setPos(3.0F, -5.0F, -9.0F);
        this.paddles[1].yRot = 3.1415927F;
        this.paddles[0].zRot = 0.19634955F;
        this.paddles[1].zRot = 0.19634955F;
        this.noWater = (new ModelRenderer(this, 0, 0)).setTexSize(128, 64);
        this.noWater.addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
        this.noWater.setPos(0.0F, -3.0F, 1.0F);
        this.noWater.xRot = 1.5707964F;
        ImmutableList.Builder<ModelRenderer> modelList = ImmutableList.builder();
        modelList.addAll(Arrays.asList(modelRenderers));
        modelList.addAll(Arrays.asList(this.paddles));
        this.modelRenderers = modelList.build();
    }

    @Override
    public void setupAnim(MythBoatEntity entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.animatePaddle(entity, 0, p_225597_2_);
        this.animatePaddle(entity, 1, p_225597_2_);
    }

    @Override
    public ImmutableList<ModelRenderer> parts() {
        return this.modelRenderers;
    }

    public ModelRenderer waterPatch() {
        return this.noWater;
    }

    protected ModelRenderer makePaddle(boolean paddle) {
        ModelRenderer modelRenderer = (new ModelRenderer(this, 62, paddle ? 0 : 20)).setTexSize(128, 64);
        modelRenderer.addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
        modelRenderer.addBox(paddle ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
        return modelRenderer;
    }

    protected void animatePaddle(MythBoatEntity entity, int i, float j) {
        float rowingTime = entity.getRowingTime(i, j);
        ModelRenderer modelRenderer = this.paddles[i];
        modelRenderer.xRot = (float) MathHelper.clampedLerp(-1.0471975803375244D, -0.2617993950843811D, ((MathHelper.sin(-rowingTime) + 1.0F) / 2.0F));
        modelRenderer.yRot = (float)MathHelper.clampedLerp(-0.7853981852531433D, 0.7853981852531433D, ((MathHelper.sin(-rowingTime + 1.0F) + 1.0F) / 2.0F));
        if (i == 1) {
            modelRenderer.yRot = 3.1415927F - modelRenderer.yRot;
        }
    }
}
