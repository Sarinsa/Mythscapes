package com.radish.mythscapes.client.renderers.entity.living.lion;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.common.entities.living.LionEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LionManeModel<T extends LionEntity> extends AgeableModel<T> {

    public ModelRenderer mane;

    public LionManeModel() {
        this.mane = new ModelRenderer(this, 0, 0);
        this.mane.setPos(0.0F, 8.0F, -14.0F);
        this.mane.addBox(-7.0F, -11.0F, -7.0F, 14, 17, 9, 0.0F);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.mane);
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of();
    }

    @Override
    public void setupAnim(T lion, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (lion.isLying()) {
            this.mane.setPos(-0.5F, 16.4F, -11.0F);
        }
        else {
            this.mane.setPos(0.0F, 8.0F, -14.0F);
        }
        this.mane.xRot = headPitch * ((float)Math.PI / 180F);
        this.mane.yRot = netHeadYaw * ((float)Math.PI / 180F);
    }
}
