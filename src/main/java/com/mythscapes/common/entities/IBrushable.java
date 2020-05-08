package com.mythscapes.common.entities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IBrushable {

    /**
     *  Called when the entity is right-clicked with a brush
     */
    void onBrushed(LivingEntity livingEntity, World world);

    /**
     * @return if this entity can be brushed as a child
     */
    boolean canBrush();

    /**
     *@return  ItemStack that should be dropped
     *         when this entity is brushed
     */
    ItemStack itemDropped();
}
