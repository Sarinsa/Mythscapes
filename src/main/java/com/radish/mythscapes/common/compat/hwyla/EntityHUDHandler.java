package com.radish.mythscapes.common.compat.hwyla;

import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.register.MythEntities;
import mcp.mobius.waila.api.IEntityAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class EntityHUDHandler implements IEntityComponentProvider {

    public static final EntityHUDHandler INSTANCE = new EntityHUDHandler();

    @Override
    public void appendBody(List<ITextComponent> tooltip, IEntityAccessor accessor, IPluginConfig config) {
        if (accessor.getEntity().getType() == MythEntities.PYGMY_SNAIL.get()) {
            SnailEntity snailEntity = ((SnailEntity)accessor.getEntity());
            tooltip.add(new TranslationTextComponent(SnailTypeRegister.getTranslationKey(snailEntity.getSnailType())));
        }
    }
}
