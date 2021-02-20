package com.radish.mythscapes.client.renderers.entity.living.pygmy_snail;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class PygmySnailRenderer extends MobRenderer<SnailEntity, PygmySnailModel<SnailEntity>> {


    private static final ResourceLocation[] SNAIL_TEXTURES = {
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/mushroom.png"),
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/swamp.png"),
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/flower_forest.png"),
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/roofed_forest.png"),
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/jungle.png"),
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/bog.png"),
            Mythscapes.resourceLoc("textures/entity/pygmy_snail/bejeweled.png"),
    };

    public PygmySnailRenderer(EntityRendererManager manager) {
        super(manager, new PygmySnailModel<>(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(SnailEntity entity) {
        return entity.getSnailType().getSnailTexture();
        //return SNAIL_TEXTURES[entity.getSnailType().ordinal()];
    }
}
