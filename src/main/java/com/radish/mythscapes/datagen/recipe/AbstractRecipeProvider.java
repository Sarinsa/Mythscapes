package com.radish.mythscapes.datagen.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.recipe.conditions.QuarkFlagCondition;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.datagen.recipe.builders.NullableItemGroupShapedRecipeBuilder;
import com.radish.mythscapes.datagen.recipe.builders.NullableItemGroupShapelessRecipeBuilder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractRecipeProvider extends RecipeProvider {

    protected Consumer<IFinishedRecipe> consumer;

    public AbstractRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    /**
     * Using this for some Mekanism recipes where we use
     * items from Mekanism.
     *
     * @param name The registry name of the item ingredient
     * @param amount The size of the ingredient stack.
     * @return A JsonObject that represents the required ItemStack ingredient.
     */
    protected static JsonObject itemFromName(String name, int amount) {
        return new JsonParser().parse("{\"ingredient\":{\"item\":" + "\"" + name + "\"" + "},\"amount\":" + amount + "}").getAsJsonObject();
    }

    protected static JsonObject itemFromName(ResourceLocation name, int amount) {
        return itemFromName(name.toString(), amount);
    }

    protected String itemName(IItemProvider criterionItem) {
        return Objects.requireNonNull(criterionItem.asItem().getRegistryName()).getPath();
    }

    protected void conditionalRecipe(Consumer<Consumer<IFinishedRecipe>> recipeBuilder, ICondition condition) {
        recipeBuilder.accept(finishedRecipe -> {
            ConditionalRecipe.builder()
                    .addCondition(condition)
                    .addRecipe(finishedRecipe)
                    .setAdvancement(ConditionalAdvancement.builder().addCondition(condition).addAdvancement(finishedRecipe))
                    .build(this.consumer, finishedRecipe.getID());
        });
    }

    public void modCompatRecipe(Consumer<Consumer<IFinishedRecipe>> recipeBuilder, String modid) {
        ICondition condition = new ModLoadedCondition(modid);

        recipeBuilder.accept(finishedRecipe -> {
            ConditionalRecipe.builder()
                    .addCondition(condition)
                    .addRecipe(finishedRecipe)
                    .setAdvancement(ConditionalAdvancement.builder().addCondition(condition).addAdvancement(finishedRecipe))
                    .build(this.consumer, finishedRecipe.getID());
        });
    }

    public void quarkFlagRecipe(String quarkFlag, Consumer<Consumer<IFinishedRecipe>> recipeBuilder) {
        ICondition condition = new QuarkFlagCondition(quarkFlag);

        recipeBuilder.accept(finishedRecipe -> {
            ConditionalRecipe.builder()
                    .addCondition(condition)
                    .addRecipe(finishedRecipe)
                    .setAdvancement(ConditionalAdvancement.builder().addCondition(condition).addAdvancement(finishedRecipe))
                    .build(this.consumer, finishedRecipe.getID());
        });
    }

    protected void registerStonecuttingRecipe(Supplier<Item> result, IItemProvider ingredient, int count, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result.get());

        SingleItemRecipeBuilder
                .stonecuttingRecipe(Ingredient.fromItems(ingredient), result.get(), count)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_stonecutting");
    }

    protected ShapedRecipeBuilder shapedRecipe(IItemProvider result, int count, IItemProvider criterionItem) {
        String criterionName = itemName(criterionItem);
        return ShapedRecipeBuilder.shapedRecipe(result, count)
                .addCriterion("has_" + criterionName, hasItem(criterionItem));
    }

    protected void stairsRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 4)
                .patternLine("  #")
                .patternLine(" ##")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenStairsRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 4)
                .setGroup("wooden_stairs")
                .patternLine("  #")
                .patternLine(" ##")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void wallRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 6)
                .patternLine("###")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void pressureplateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenPressureplateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .setGroup("wooden_pressure_plate")
                .patternLine("##")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void buttonRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapelessRecipeBuilder.shapelessRecipe(result)
                .addIngredient(ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenButtonRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapelessRecipeBuilder.shapelessRecipe(result)
                .addIngredient(ingredient)
                .setGroup("wooden_button")
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void slabRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 6)
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenSlabRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 6)
                .setGroup("wooden_slab")
                .patternLine("###")
                .key('#', ingredient)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenFenceRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 3)
                .setGroup("wooden_fence")
                .patternLine("#S#")
                .patternLine("#S#")
                .key('#', ingredient)
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void woodenFenceGateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .setGroup("wooden_fence_gate")
                .patternLine("S#S")
                .patternLine("S#S")
                .key('#', ingredient)
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected void boatRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shapedRecipe(result, 1)
                .setGroup("boat")
                .patternLine("# #")
                .patternLine("###")
                .key('#', MythItems.WOLT_PLANKS.get())
                .addCriterion("has_" + criterionName, hasItem(ingredient))
                .build(consumer);
    }

    protected ShapelessRecipeBuilder shapelessRecipe(IItemProvider result, int count, IItemProvider criterionIngredient) {
        String criterionName = itemName(criterionIngredient);
        return ShapelessRecipeBuilder.shapelessRecipe(result, count)
                .addCriterion("has_" + criterionName, hasItem(criterionIngredient));
    }

    protected ShapelessRecipeBuilder shapelessRecipe(IItemProvider result, int count, ITag.INamedTag<Item> criterionTag) {
        String criterionName = criterionTag.getName().getPath();
        return ShapelessRecipeBuilder.shapelessRecipe(result, count)
                .addCriterion("has_" + criterionName, hasItem(criterionTag));
    }

    protected void singleIngredientRecipe(IItemProvider result, int count, IItemProvider ingredient) {
        this.shapelessRecipe(result, count, ingredient)
                .addIngredient(ingredient)
                .build(this.consumer);
    }

    protected void shapelessPlanksRecipe(IItemProvider result, ITag.INamedTag<Item> ingredientTag, Consumer<IFinishedRecipe> consumer) {
        shapelessRecipe(result, 4, ingredientTag)
                .setGroup("planks")
                .addIngredient(ingredientTag)
                .build(consumer);
    }

    protected void smeltingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(result);
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ingredient), result, experience, 200)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, ingredientName + "_from_smelting");
    }

    protected void blastingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ingredient), result, experience, 100)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_blasting");
    }

    protected void smokerRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, experience, 100, CookingRecipeSerializer.SMOKING)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_smoking");
    }

    protected void campfireRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, experience, 600, CookingRecipeSerializer.CAMPFIRE_COOKING)
                .addCriterion("has_" + ingredientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_campfire");
    }

    protected void quarkVerticalSlabRecipe(IItemProvider result, IItemProvider ingredient) {
        String vertical = itemName(ingredient);
        String horizontal = itemName(result);

        this.quarkFlagRecipe("vertical_slabs", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shapedRecipe(result, 3)
                    .patternLine("#")
                    .patternLine("#")
                    .patternLine("#")
                    .key('#', ingredient)
                    .addCriterion("has_" + vertical, hasItem(ingredient))
                    .build(recipeConsumer);
        });

        this.quarkFlagRecipe("vertical_slabs", recipeConsumer -> {
            NullableItemGroupShapelessRecipeBuilder.shapelessRecipe(ingredient)
                    .addIngredient(result)
                    .addCriterion("has_" + horizontal, hasItem(result))
                    .build(recipeConsumer, Mythscapes.MODID + ":" + horizontal + "_from_vertical_slab");
        });
    }

    protected void quarkVerticalPlanksRecipe(IItemProvider result, IItemProvider ingredient) {
        String vertical = itemName(ingredient);
        String horizontal = itemName(result);

        this.quarkFlagRecipe("vertical_planks", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shapedRecipe(result, 3)
                    .patternLine("#")
                    .patternLine("#")
                    .patternLine("#")
                    .key('#', ingredient)
                    .addCriterion("has_" + vertical, hasItem(ingredient))
                    .build(recipeConsumer);
        });

        this.quarkFlagRecipe("vertical_planks", recipeConsumer -> {
            NullableItemGroupShapelessRecipeBuilder.shapelessRecipe(ingredient)
                    .addIngredient(result)
                    .addCriterion("has_" + horizontal, hasItem(result))
                    .build(recipeConsumer, Mythscapes.MODID + ":" + horizontal + "_from_vertical_planks");
        });
    }

    protected void woodSignRecipe(IItemProvider result, IItemProvider ingredient) {
        this.modCompatRecipe(recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shapedRecipe(result, 3)
                    .patternLine("###")
                    .patternLine("###")
                    .patternLine(" S ")
                    .key('#', ingredient)
                    .key('S', Tags.Items.RODS_WOODEN)
                    .addCriterion("has_" + itemName(ingredient), hasItem(ingredient))
                    .build(recipeConsumer);
        }, "quark");
    }

    protected void quarkLeafCarpetRecipe(IItemProvider result, IItemProvider ingredient) {
        this.quarkFlagRecipe("leaf_carpet", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shapedRecipe(result, 3)
                    .patternLine("##")
                    .key('#', ingredient)
                    .addCriterion("has_" + itemName(ingredient), hasItem(ingredient))
                    .build(recipeConsumer);
        });
    }

    protected void quarkLadderRecipe(IItemProvider result, IItemProvider ingredient) {
        this.quarkFlagRecipe("variant_ladders", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shapedRecipe(result, 4)
                    .patternLine("# #")
                    .patternLine("#P#")
                    .patternLine("# #")
                    .key('#', Tags.Items.RODS_WOODEN)
                    .key('P', ingredient)
                    .addCriterion("has_" + itemName(ingredient), hasItem(ingredient))
                    .build(recipeConsumer);
        });
    }

    protected void quarkWoodenPostRecipe(IItemProvider result, IItemProvider ingredient) {
        this.quarkFlagRecipe("wooden_posts", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shapedRecipe(result, 3)
                    .patternLine("#")
                    .patternLine("#")
                    .patternLine("#")
                    .key('#', ingredient)
                    .addCriterion("has_" + itemName(ingredient), hasItem(ingredient))
                    .build(recipeConsumer);
        });
    }
}
