package com.radish.mythscapes.client.renderers.entity.misc.boat;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radish.mythscapes.client.renderers.entity.misc.boat.MythBoatModel;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.misc.MythBoatEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class MythBoatRenderer extends EntityRenderer<MythBoatEntity> {

    //
    //  COPY PASTA CODE FROM BoatEntity.class
    //

    private static final ResourceLocation[] BOAT_TEXTURES = {
            Mythscapes.resourceLoc("textures/entity/boat/wolt_boat.png"),
            Mythscapes.resourceLoc("textures/entity/boat/viridian_boat.png")
    };
    protected final MythBoatModel model = new MythBoatModel();

    public MythBoatRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager);
        this.shadowRadius = 0.8F;
    }

    public void render(MythBoatEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.375D, 0.0D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float)entityIn.getHurtTime() - partialTicks;
        float f1 = entityIn.getDamage() - partialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getHurtDir()));
        }

        float f2 = entityIn.getBubbleAngle(partialTicks);
        if (!MathHelper.equal(f2, 0.0F)) {
            matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), entityIn.getBubbleAngle(partialTicks), true));
        }

        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        this.model.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!entityIn.isUnderWater()) {
            IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.waterMask());
            this.model.waterPatch().render(matrixStackIn, ivertexbuilder1, packedLightIn, OverlayTexture.NO_OVERLAY);
        }

        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getTextureLocation(MythBoatEntity entity) {
        return BOAT_TEXTURES[entity.getMythBoatType().ordinal()];
    }
}
