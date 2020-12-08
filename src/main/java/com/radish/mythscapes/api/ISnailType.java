package com.radish.mythscapes.api;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * This interface can be implemented into a class
 * to create your own snail type for the Pygmy Snail.
 *
 * For the time being, customization is limited to
 * a custom texture, shell shed drop and Rarity.
 */
public interface ISnailType {

    /**
     * Returns a ResourceLocation pointing to
     * the texture of this snail type.
     */
    ResourceLocation getSnailTexture();

    /**
     * Returns the unlocalized name for
     * this snail type, for example:
     *
     * "sluggish" or "super_snail"
     */
    String getName();

    /**
     * Return a new ItemStack that this snail
     * type will drop when it sheds it's shell.
     *
     * Return null for the default shell drop, or return
     * ItemStack.EMPTY for no drop.
     */
    @Nullable
    ItemStack getShedDrop(Random random);

    /**
     * Returns the Rarity for this snail type.
     *
     * Rarity determines the text color used
     * for the localized snail type names.
     *
     * A snail bucket will get the enchanted glow
     * effect when containing a snail type with
     * Rarity.EPIC
     */
    Rarity getRarity();
}
