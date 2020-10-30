package com.mythscapes.compat.jei.brushing;

import com.mythscapes.compat.jei.RecipeCategories;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.util.ResourceLocation;

public class BrushingRecipeCategory extends AbstractBrushingRecipeCategory<BrushingRecipe> {

    public BrushingRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper);
    }

    @Override
    public ResourceLocation getUid() {
        return RecipeCategories.BRUSHING;
    }

    @Override
    public Class<? extends BrushingRecipe> getRecipeClass() {
        return BrushingRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Creature Brushing";
    }

    @Override
    public IDrawable getBackground() {
        return gui;
    }

    @Override
    public IDrawable getIcon() {
        return brush;
    }

    @Override
    public void setIngredients(BrushingRecipe brushingRecipe, IIngredients ingredients) {

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, BrushingRecipe brushingRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
        stacks.init(resultStack, false, 80, 80);
        stacks.set(iIngredients);
    }
}
