package com.radish.mythscapes.common.compat.jei;

import com.radish.mythscapes.common.core.Mythscapes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
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
    public @NotNull ResourceLocation getPluginUid() {
        return Mythscapes.resourceLoc("mythscapes_jei_plugin");
    }
}
