package com.mythscapes.api;

import com.mythscapes.common.entities.brushables.BirdBrushable;
import net.minecraft.entity.EntityType;

@MythscapesPlugin(modid = "mythscapes")
public class Plugin implements IMythscapesPlugin {

    @Override
    public void register(IRegistryHelper iRegistryHelper) {
        iRegistryHelper.registerBrushable(EntityType.CREEPER, new BirdBrushable());
    }
}
