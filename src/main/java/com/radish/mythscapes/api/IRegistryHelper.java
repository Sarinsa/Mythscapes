package com.radish.mythscapes.api;

import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * This interface contains all the helper methods for
 * interacting with the Mythscapes API.
 */
public interface IRegistryHelper {

    /**
     * Registers an IBrushable instance and associates it with the specified
     * entity class.
     *
     * @param entityClass The entity class to assign the IBrushable to.
     * @param brushable The IBrushable instance to register.
     */
    void registerBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> brushable);

    /**
     * If an entity already has an IBrushable associated with it by the time your
     * plugin is loaded, this method can be used to replace the existing IBrushable.
     *
     * In other words, if you wish to remove an integrated IBrushable for a vanilla entity
     * Mythscapes already has covered and replace it with your own, this is the way to go.
     *
     * @param entityClass The entity class to assign the IBrushable to.
     * @param newBrushable The new IBrushable instance to replace the existing on with.
     */
    void overrideBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> newBrushable);

    /**
     * Registers a new snail type for the Pygmy Snail entity.
     *
     * @param snailType A new instance of an ISnailType.
     */
    void registerSnailType(ISnailType snailType);
}
