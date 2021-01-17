package com.radish.mythscapes.datagen.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractRecipeProvider extends RecipeProvider {

    public AbstractRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    protected JsonObject ingredientFromName(String name, int amount) {
        return new JsonParser().parse("{\"ingredient\":{\"item\":" + "\"" + name + "\"" + "},\"amount\":" + amount + "}").getAsJsonObject();
    }

    protected JsonObject ingredientFromName(ResourceLocation name, int amount) {
        return ingredientFromName(name.toString(), amount);
    }

    protected void registerStonecuttingRecipe(Supplier<Item> result, IItemProvider ingredientBlock, int count, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = Objects.requireNonNull(ingredientBlock.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.get().getRegistryName()).getPath();

        SingleItemRecipeBuilder
                .stonecuttingRecipe(Ingredient.fromItems(ingredientBlock), result.get(), count)
                .addCriterion("has_" + ingredientName, hasItem(ingredientBlock))
                .build(consumer, resultName + "_from_" + ingredientName + "_stonecutting");
    }

    protected ShapedRecipeBuilder shapedRecipe(IItemProvider result, int count, IItemProvider criterionItem) {
        String criterionName = Objects.requireNonNull(criterionItem.asItem().getRegistryName()).getPath();
        return ShapedRecipeBuilder.shapedRecipe(result, count).addCriterion("has_" + criterionName, hasItem(criterionItem));
    }

    protected void stairsRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 4)
                .patternLine("  #")
                .patternLine(" ##")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenStairsRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 4)
                .setGroup("wooden_stairs")
                .patternLine("  #")
                .patternLine(" ##")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void wallRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 6)
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void pressureplateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenPressureplateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .setGroup("wooden_pressure_plate")
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void buttonRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapelessRecipeBuilder.shapelessRecipe(result).addIngredient(ingredient).addCriterion(criterionName, hasItem(ingredient)).build(consumer);
    }

    protected void woodenButtonRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapelessRecipeBuilder.shapelessRecipe(result).addIngredient(ingredient).setGroup("wooden_button").addCriterion(criterionName, hasItem(ingredient)).build(consumer);
    }

    protected void slabRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 6)
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenSlabRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 6)
                .setGroup("wooden_slab")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenFenceRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 3)
                .setGroup("wooden_fence")
                .patternLine("#S#")
                .patternLine("#S#")
                .key('#', ingredient)
                .key('S', Ingredient.fromItems(Items.STICK))
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenFenceGateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .setGroup("wooden_fence_gate")
                .patternLine("S#S")
                .patternLine("S#S")
                .key('#', ingredient)
                .key('S', Ingredient.fromItems(Items.STICK))
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void boatRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .setGroup("boat")
                .patternLine("# #")
                .patternLine("###")
                .key('#', MythItems.WOLT_PLANKS.get())
                .addCriterion(criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected ShapelessRecipeBuilder shapelessRecipe(IItemProvider result, int count, IItemProvider ingredient) {
        String criterionName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        return ShapelessRecipeBuilder.shapelessRecipe(result, count).addCriterion("has_" + criterionName, hasItem(ingredient));
    }

    protected ShapelessRecipeBuilder shapelessRecipe(IItemProvider result, int count, ITag.INamedTag<Item> criterionTag) {
        String criterionName = criterionTag.getName().getPath();
        return ShapelessRecipeBuilder.shapelessRecipe(result, count).addCriterion("has_" + criterionName, hasItem(criterionTag));
    }

    protected void shapelessPlanksRecipe(IItemProvider result, ITag.INamedTag<Item> ingredientTag, Consumer<IFinishedRecipe> consumer) {
        shapelessRecipe(result, 4, ingredientTag).setGroup("planks").addIngredient(ingredientTag).build(consumer);
    }

    protected void smeltingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ingredient), result, experience, 200)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, ingredientName + "_from_smelting");
    }

    protected void blastingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ingredient), result, experience, 100)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_blasting");
    }

    protected void smokerRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();

        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, experience, 100, CookingRecipeSerializer.SMOKING)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_smoking");
    }

    protected void campfireRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();

        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, experience, 600, CookingRecipeSerializer.CAMPFIRE_COOKING)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_campfire");
    }
}
