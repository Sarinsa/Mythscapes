package com.radish.mythscapes.client.renderers.entity.living.pond_serpent;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.PondSerpentEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;


public class PondSerpentRenderer extends MobRenderer<PondSerpentEntity, PondSerpentModel<PondSerpentEntity>> {

    private static final ResourceLocation[] POND_SERPENT_TEXTURES = {
            Mythscapes.resourceLoc("textures/entity/pond_serpent/pond_serpent_olympian_mountains.png"),
            Mythscapes.resourceLoc("textures/entity/pond_serpent/pond_serpent_ocean.png")
    };

    public PondSerpentRenderer(EntityRendererManager manager) {
        super(manager, new PondSerpentModel<>(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(PondSerpentEntity entity) {
        return POND_SERPENT_TEXTURES[entity.getSerpentType().ordinal()];
    }

    @Override
    protected void applyRotations(PondSerpentEntity pondSerpentEntity, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(pondSerpentEntity, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

        if (!pondSerpentEntity.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, 0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}
