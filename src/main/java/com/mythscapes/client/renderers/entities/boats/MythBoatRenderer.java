package com.mythscapes.client.renderers.entities.boats;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mythscapes.common.entities.MythBoatEntity;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BoatModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class MythBoatRenderer extends EntityRenderer<MythBoatEntity> {

    //
    //  COPY PASTA CODE FROM BoatEntity.class
    //

    private static final ResourceLocation[] BOAT_TEXTURES = {
            new ModResourceLocation("textures/entity/boat/charged_wood_boat.png"),
            new ModResourceLocation("textures/entity/boat/viridian_boat.png")
    };
    protected final MythBoatModel model = new MythBoatModel();

    public MythBoatRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager);
        this.shadowSize = 0.8F;
    }

    public void render(MythBoatEntity boatEntity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLightIn) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float hitPartial = (float)boatEntity.getTimeSinceHit() - partialTicks;
        float damagePartial = boatEntity.getDamageTaken() - partialTicks;

        if (damagePartial < 0.0F) {
            damagePartial = 0.0F;
        }
        if (hitPartial > 0.0F) {
            matrixStack.rotate(Vector3f.XP.rotationDegrees(MathHelper.sin(hitPartial) * hitPartial * damagePartial / 10.0F * (float)boatEntity.getForwardDirection()));
        }
        float rockingAngle = boatEntity.getRockingAngle(partialTicks);

        if (!MathHelper.epsilonEquals(rockingAngle, 0.0F)) {
            matrixStack.rotate(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boatEntity.getRockingAngle(partialTicks), true));
        }

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));

        this.model.setRotationAngles(boatEntity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        IVertexBuilder lvt_10_1_ = buffer.getBuffer(this.model.getRenderType(this.getEntityTexture(boatEntity)));
        this.model.render(matrixStack, lvt_10_1_, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        IVertexBuilder lvt_11_1_ = buffer.getBuffer(RenderType.getWaterMask());
        this.model.func_228245_c_().render(matrixStack, lvt_11_1_, packedLightIn, OverlayTexture.NO_OVERLAY);

        matrixStack.pop();
        super.render(boatEntity, entityYaw, partialTicks, matrixStack, buffer, packedLightIn);
    }

    public ResourceLocation getEntityTexture(MythBoatEntity boatEntity) {
        return BOAT_TEXTURES[boatEntity.getMythBoatType().ordinal()];
    }
}
