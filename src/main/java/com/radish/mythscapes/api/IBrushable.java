package com.radish.mythscapes.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * This interface can be implemented into a class to
 * create a "brush recipe" for any living entity.
 */
public interface IBrushable<T extends LivingEntity> {

    /**
     *  Called when the entity is right-clicked with a brush item
     */
    void onBrushed(T livingEntity, World world);

    /**
     * @return whether this entity can be brushed or not
     *         with given conditions.
     */
    boolean canBrush(T livingEntity, World world);

    /**
     * @param fortuneLevel The fortune enchant level on
     *                     the brush used.
     *
     * @return The ItemStacks that can be dropped
     *         when this entity is brushed.
     *         Return ItemStack.EMPTY for no drop.
     */
    @NotNull
    ItemStack itemDropped(T livingEntity, int fortuneLevel);
}
