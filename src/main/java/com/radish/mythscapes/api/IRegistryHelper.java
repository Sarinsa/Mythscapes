package com.radish.mythscapes.api;

import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface IRegistryHelper {

    /**
     * @param entityClass - The entity class to assign the IBrushable to.
     * @param iBrushable - Parse a new instance of an IBrushable.
     *                     Type parameter must extend LivingEntity
     */
    void registerBrushable(@NotNull Class<? extends LivingEntity> entityClass, @NotNull IBrushable<?> iBrushable);
}
