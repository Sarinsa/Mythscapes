package com.radish.mythscapes.common.recipe;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.recipe.conditions.QuarkFlagCondition;
import net.minecraftforge.common.crafting.CraftingHelper;

public class CraftingUtility {

    public static void registerConditions() {
        CraftingHelper.register(new QuarkFlagCondition.Serializer(Mythscapes.resourceLoc("quark_flag")));
    }
}
