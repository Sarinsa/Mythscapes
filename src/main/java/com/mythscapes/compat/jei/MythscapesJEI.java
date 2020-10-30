package com.mythscapes.compat.jei;

import com.mythscapes.compat.jei.brushing.BrushingRecipeCategory;
import com.mythscapes.core.Mythscapes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class MythscapesJEI implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers helpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();

        registration.addRecipeCategories(new BrushingRecipeCategory(guiHelper));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return Mythscapes.resourceLoc("mythscapes_jei_plugin");
    }
}
