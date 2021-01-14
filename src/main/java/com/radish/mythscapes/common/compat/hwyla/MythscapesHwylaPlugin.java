package com.radish.mythscapes.common.compat.hwyla;

import com.radish.mythscapes.common.blocks.plant.BlisterberryThistleBlock;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.util.ResourceLocation;

@WailaPlugin
public class MythscapesHwylaPlugin implements IWailaPlugin {

    protected static final ResourceLocation CONFIG_SNAIL_TYPE = Mythscapes.resourceLoc("show_snail_type");
    // Vanilla plugin
    protected static final ResourceLocation CONFIG_CROP_PROGRESS = new ResourceLocation("crop_progress");

    @Override
    public void register(IRegistrar iRegistrar) {
        iRegistrar.addConfig(CONFIG_SNAIL_TYPE, true);
        iRegistrar.registerStackProvider(HUDHandler.INSTANCE, BlisterberryThistleBlock.class);
        iRegistrar.registerComponentProvider(HUDHandler.INSTANCE, TooltipPosition.BODY, BlisterberryThistleBlock.class);
        iRegistrar.registerComponentProvider(EntityHUDHandler.INSTANCE, TooltipPosition.BODY, SnailEntity.class);
    }
}
