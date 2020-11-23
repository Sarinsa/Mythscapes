package com.radish.mythscapes.api;

import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * This interface contains all the helper methods for
 * registering things from our mod.
 */
public interface IRegistryHelper {

    /**
     * Registers an IBrushable instance and associates it with the specified
     * entity class.
     *
     * @param entityClass - The entity class to assign the IBrushable to.
     * @param iBrushable - The IBrushable instance to register.
     */
    void registerBrushable(@NotNull Class<? extends LivingEntity> entityClass, @NotNull IBrushable<?> iBrushable);
}
