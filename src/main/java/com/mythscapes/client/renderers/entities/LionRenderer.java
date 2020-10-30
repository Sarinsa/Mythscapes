package com.mythscapes.client.renderers.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mythscapes.client.renderers.entities.model.LionModel;
import com.mythscapes.common.entities.living.LionEntity;
import com.mythscapes.core.Mythscapes;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionRenderer extends MobRenderer<LionEntity, LionModel<LionEntity>> {

    private static final ResourceLocation MANELESS = Mythscapes.resourceLoc("textures/entity/lion/lion_maneless.png");
    private static final ResourceLocation NORMAL = Mythscapes.resourceLoc("textures/entity/lion/lion.png");
    private static final ResourceLocation AGGROED = Mythscapes.resourceLoc("textures/entity/lion/lion_aggroed.png");

    public LionRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new LionModel<>(), 0.7f);
    }

    @Override
    public void render(LionEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
        if (entity.isLying()) {
            matrixStack.push();
            matrixStack.translate(entity.getPosX(), entity.getPosY() - 1.0D, entity.getPosZ());
            matrixStack.pop();
        }
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getEntityTexture(LionEntity entity) {
        if (entity.isChild() || !entity.hasMane()) {
            return MANELESS;
        } else {
            return entity.isAggressive() ? AGGROED : NORMAL;
        }
    }
}
