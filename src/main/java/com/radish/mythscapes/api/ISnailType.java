package com.radish.mythscapes.api;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

/**
 * This interface can be implemented into a class
 * to create your own snail type for the Pygmy Snail entity.
 */
public interface ISnailType {

    // Currently not used.
    /**
     * Used to determine if this snail type
     * should render a "growth" render layer.
     * For instance, the mushroom snail type
     * has a "growth" on it's back and the flower
     * snail type has both a "growth" on its back
     * and "tail".
     */
    enum GrowthRenderLayer {
        NONE,
        BACK,
        TAIL,
        BOTH
    }

    /**
     * @return The GrowthRenderLayer type to be used
     *         when rendering the Pygmy Snail entity
     *         depending on snail type.
     *
     *         NONE: No rendered growths.
     *         BACK: Will render the back growth.
     *         TAIL: Will render the tail growth.
     *         BOTH: Will render both tail and back growth.
     */
    default GrowthRenderLayer getGrowthRenderLayer() {
        return GrowthRenderLayer.NONE;
    }

    /**
     * Returns a ResourceLocation pointing to
     * the texture of this snail type.
     */
    ResourceLocation getSnailTexture();

    /**
     * Returns the registry name for this snail
     * type with your mod's namespace.
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

    /**
     * Returns a list of ResourceLocations for the biomes
     * that this snail type should be able to spawn in.
     *
     * Keep in mind that snails will only spawn in biomes
     * that exist in BiomeDictionary.Type.OVERWORLD.
     */
    List<ResourceLocation> getSpawnBiomes();
}
