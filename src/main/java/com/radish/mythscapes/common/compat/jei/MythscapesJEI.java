package com.radish.mythscapes.common.compat.jei;

import com.radish.mythscapes.client.GuiTexts;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class MythscapesJEI implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers helpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        addItemDescription(registration, new ItemStack(MythItems.BRUSH.get()), GuiTexts.BRUSH_INFO);
    }

    private void addItemDescription(IRecipeRegistration registration, ItemStack itemStack, String... message) {
        registration.addIngredientInfo(itemStack, VanillaTypes.ITEM, message);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return Mythscapes.resourceLoc("mythscapes_jei_plugin");
    }
}
