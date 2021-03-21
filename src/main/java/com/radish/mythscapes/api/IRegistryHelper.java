package com.radish.mythscapes.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

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

    /**
     * Registers a spawn entry for the specified snail type.
     *
     * A spawn entry is needed for your snail type to spawn
     * naturally in the world.
     *
     * @param biomeName The registry name of the biome that this snail type should spawn in.
     * @param snailType The snail type for this spawn entry.
     * @param weight The chance for this snail type to be chosen over others that might spawn in the same biome.
     *               Should be a positive number. (Most of Mythscapes' default snail types have a weight of 100)
     */
    void registerSnailSpawn(ResourceLocation biomeName, ISnailType snailType, int weight);
}
