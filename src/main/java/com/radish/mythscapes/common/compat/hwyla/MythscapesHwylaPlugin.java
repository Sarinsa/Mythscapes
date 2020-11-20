package com.radish.mythscapes.common.compat.hwyla;

import com.radish.mythscapes.common.blocks.plant.BlisterberryThistleBlock;
import mcp.mobius.waila.api.*;

@WailaPlugin
public class MythscapesHwylaPlugin implements IWailaPlugin {

    @Override
    public void register(IRegistrar iRegistrar) {
        iRegistrar.registerStackProvider(HUDHandler.INSTANCE, BlisterberryThistleBlock.class);
        iRegistrar.registerComponentProvider((IComponentProvider) HUDHandler.INSTANCE, TooltipPosition.BODY, BlisterberryThistleBlock.class);
    }
}
