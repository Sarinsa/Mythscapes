package com.mythscapes.client.renderers.entities;

import com.mythscapes.client.renderers.entities.model.PondSerpentModel;
import com.mythscapes.common.entities.living.pond_serpent.PondSerpentEntity;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
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
        return entity.isBlueVariant() ? POND_SERPENT_TEXTURES[0] : POND_SERPENT_TEXTURES[1];
    }
}
