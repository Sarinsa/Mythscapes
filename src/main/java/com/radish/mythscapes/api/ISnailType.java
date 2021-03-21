package com.radish.mythscapes.api;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * This interface can be implemented into a class
 * to create your own snail type for the Pygmy Snail entity.
 */
public interface ISnailType {

    /**
     * This is used to determine if either of the extra
     * "growth" models should be rendered for this snail type.
     *
     * For example, the Flower Forest snail type uses BOTH,
     * rendering the cross pattern flower on it's back and the
     * lesser flower on it's tail.
     *
     * NONE: Render none of the growth models.
     * BACK: Render the cross pattern model on the snail's back.
     * TAIL: Render the flat growth model behind the snail.
     * BOTH: Render both the back and tail growth model.
     */
    enum GrowthRenderType {
        NONE,
        BACK,
        TAIL,
        BOTH
    }

    /**
     * @return The GrowthRenderType to be used
     *         when rendering the snail.
     */
    default GrowthRenderType getGrowthRenderType() {
        return GrowthRenderType.NONE;
    }

    /**
     * @return The ResourceLocation pointing to
     *         the texture of this snail type.
     */
    ResourceLocation getSnailTexture();

    /**
     * @return A ResourceLocation with your mod's
     *         namespace and an unlocalized name.
     *
     *         Example: <code>new ResourceLocation("epic_mod", "super_snail")</code>
     */
    ResourceLocation getName();

    /**
     * Return a new ItemStack that this snail
     * type will drop when it sheds it's shell.
     *
     * Return null for the default shell drop, or return
     * ItemStack.EMPTY for no drop.
     */
    @Nullable
    default ItemStack getShedDrop(Random random) {
        return null;
    }

    /**
     * Returns the Rarity for this snail type.
     *
     * Rarity determines the text color used to
     * display the snail type name on snail buckets.
     *
     * A snail bucket will also get the enchanted glow
     * effect when containing a snail type with
     * Rarity.EPIC
     */
    default Rarity getRarity() {
        return Rarity.COMMON;
    }
}
