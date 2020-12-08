package com.radish.mythscapes.api;

import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * This interface contains all the helper methods for
 * registering things to Mythscapes or performing
 * other interactions.
 */
public interface IRegistryHelper {

    /**
     * Registers an IBrushable instance and associates it with the specified
     * entity class. Note that we already have a fair amount of brushables registered
     * for a lot of vanilla animals, and it cannot be overridden.
     *
     * @param entityClass The entity class to assign the IBrushable to.
     * @param iBrushable The IBrushable instance to register.
     */
    void registerBrushable(@NotNull Class<? extends LivingEntity> entityClass, @NotNull IBrushable<?> iBrushable);

    /**
     * Registers a new snail type for the Pygmy Snail.
     *
     * @param snailType A new instance of an ISnailType
     */
    void registerSnailType(@NotNull ISnailType snailType);
}
