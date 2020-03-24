package com.mythscapes.client.renderers.entities;

import com.mythscapes.client.renderers.entities.model.PondSerpentModel;
import com.mythscapes.common.entities.PondSerpentEntity;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class PondSerpentRenderer extends MobRenderer<PondSerpentEntity, PondSerpentModel<PondSerpentEntity>> {
    private static final ResourceLocation[] POND_SERPENT_TEXTURES = {
            new ModResourceLocation("textures/entity/pond_serpent/pond_serpent_ocean.png"),
            new ModResourceLocation("textures/entity/pond_serpent/pond_serpent_olympian_mountains.png")
    };

    public PondSerpentRenderer(EntityRendererManager manager) {
        super(manager, new PondSerpentModel<>(), 0.3f);
        //0.3f is the shadow size
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(PondSerpentEntity entity) {
        return POND_SERPENT_TEXTURES[entity.type.ordinal()];
    }
}
