package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.api.IRegistryHelper;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEntities;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public final class RegistryUtil implements IRegistryHelper {

    public final void registerBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> iBrushable) {
        Objects.requireNonNull(entityClass);
        Objects.requireNonNull(iBrushable);

        if (MythEntities.BRUSHABLES.containsKey(entityClass)) {
            Mythscapes.LOGGER.warn("Tried to register brushable for duplicate entity class: " + entityClass.getSimpleName());
            return;
        }
        MythEntities.BRUSHABLES.putIfAbsent(entityClass, iBrushable);
    }
}

    /*
    private void registerBrushable(EntityType<? extends LivingEntity> entityType, IBrushable<?> iBrushable, boolean doWarn) {
        if (MythEntities.BRUSHABLES.containsKey(entityType)) {
            Mythscapes.LOGGER.error("Brushable already registered for entity type: " + entityType.getTranslationKey());
        }
        else {
            if (entityType.getRegistryName() != null && entityType.getRegistryName().getNamespace().equals("minecraft") && doWarn) {
                Mythscapes.LOGGER.warn("Registered brushable for vanilla entity type. If other plugins that loads later adds to the same entity type it will not be accepted.");
            }
            MythEntities.BRUSHABLES.put(entityType, iBrushable);
        }
    }

*/

    /**
     *  Used only for Mythscapes, not printing warn message for vanilla entity types.
     */

    /*
    public final void registerBrushableInternal(EntityType<? extends LivingEntity> entityType, IBrushable<?> iBrushable) {
        registerBrushable(entityType, iBrushable, false);
    }

    @Override
    public void registerBrushable(@NotNull EntityType<? extends LivingEntity> entityType, @NotNull IBrushable<?> iBrushable) {
        registerBrushable(entityType, iBrushable, true);
    }
}

     */
