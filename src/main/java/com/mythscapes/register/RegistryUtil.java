package com.mythscapes.register;

import com.mythscapes.api.IBrushable;
import com.mythscapes.api.IRegistryHelper;
import com.mythscapes.core.Mythscapes;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class RegistryUtil implements IRegistryHelper {

    public final void registerBrushable(EntityType<? extends LivingEntity> entityType, IBrushable<?> iBrushable, boolean doWarn) {
        if (MythEntities.BRUSHABLES.containsKey(entityType)) {
            Mythscapes.LOGGER.error("Brushable already registered for entity type: " + entityType.getTranslationKey());
        }
        else {
            if (entityType.getRegistryName().getNamespace().equals("minecraft") && doWarn) {
                Mythscapes.LOGGER.warn("Registered brushable for vanilla entity type. If other plugins that loads later adds to the same entity type it will not be accepted.");
            }
            MythEntities.BRUSHABLES.put(entityType, iBrushable);
        }
    }

    public final void registerBrushable(EntityType<? extends LivingEntity> entityType, IBrushable<?> iBrushable) {
        registerBrushable(entityType, iBrushable, true);
    }

    /**
     *  Used only for Mythscapes, not printing warn message for vanilla entity types.
     */
    public final void registerBrushableInternal(EntityType<? extends LivingEntity> entityType, IBrushable<?> iBrushable) {
        registerBrushable(entityType, iBrushable, false);
    }
}
