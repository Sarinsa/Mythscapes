package com.mythscapes.api;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public interface IRegistryHelper {

    /**
     * @param entityType - The entity type to assign the IBrushable to.
     * @param iBrushable - Parse a new instance of an IBrushable.
     *                     Type parameter must extend LivingEntity
     */
    void registerBrushable(@NotNull EntityType<? extends LivingEntity> entityType, @NotNull IBrushable<?> iBrushable);
}
