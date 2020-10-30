package com.mythscapes.compat.jei.brushing;

import com.mythscapes.core.Mythscapes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractBrushingRecipeCategory<T extends BrushingRecipe> implements IRecipeCategory<T> {

    protected static final ResourceLocation BRUSH_TEXTURE = Mythscapes.resourceLoc("textures/item/brush.png");
    protected static final ResourceLocation GUI = Mythscapes.resourceLoc("textures/gui/brushing.png");

    protected static final int resultStack = 0;

    protected final IDrawableStatic brush;
    protected final IDrawable gui;

    public AbstractBrushingRecipeCategory(IGuiHelper guiHelper) {
        this.brush = guiHelper.createDrawable(BRUSH_TEXTURE, 10, 14, 100, 100);
        this.gui = guiHelper.createDrawable(GUI, 4, 12, 100, 100);
    }
}
