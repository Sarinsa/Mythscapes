package com.radish.mythscapes.datagen.recipe.builders;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Essentially a copy of ShapelessRecipeBuilder except
 * the result item can have null ItemGroup without
 * everything crashing and going to shit during data gen.
 */
public class NullableItemGroupShapelessRecipeBuilder {

    private final Item result;
    private final int count;
    private final List<Ingredient> ingredients = Lists.newArrayList();
    private final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    private String group;

    public NullableItemGroupShapelessRecipeBuilder(IItemProvider resultIn, int countIn) {
        this.result = resultIn.asItem();
        this.count = countIn;
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static NullableItemGroupShapelessRecipeBuilder shapeless(IItemProvider resultIn) {
        return new NullableItemGroupShapelessRecipeBuilder(resultIn, 1);
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static NullableItemGroupShapelessRecipeBuilder shapeless(IItemProvider resultIn, int countIn) {
        return new NullableItemGroupShapelessRecipeBuilder(resultIn, countIn);
    }

    /**
     * Adds an ingredient that can be any item in the given tag.
     */
    public NullableItemGroupShapelessRecipeBuilder requires(ITag<Item> tagIn) {
        return this.requires(Ingredient.of(tagIn));
    }

    /**
     * Adds an ingredient of the given item.
     */
    public NullableItemGroupShapelessRecipeBuilder requires(IItemProvider itemIn) {
        return this.requires(itemIn, 1);
    }

    /**
     * Adds the given ingredient multiple times.
     */
    public NullableItemGroupShapelessRecipeBuilder requires(IItemProvider itemIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.requires(Ingredient.of(itemIn));
        }

        return this;
    }

    /**
     * Adds an ingredient.
     */
    public NullableItemGroupShapelessRecipeBuilder requires(Ingredient ingredientIn) {
        return this.requires(ingredientIn, 1);
    }

    /**
     * Adds an ingredient multiple times.
     */
    public NullableItemGroupShapelessRecipeBuilder requires(Ingredient ingredientIn, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredientIn);
        }
        return this;
    }

    /**
     * Adds a criterion needed to unlock the recipe.
     */
    public NullableItemGroupShapelessRecipeBuilder unlockedBy(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public NullableItemGroupShapelessRecipeBuilder group(String groupIn) {
        this.group = groupIn;
        return this;
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void save(Consumer<IFinishedRecipe> consumerIn) {
        this.save(consumerIn, Registry.ITEM.getKey(this.result));
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}. Use {@link #save(Consumer)} if save is the same as the ID for
     * the result.
     */
    public void save(Consumer<IFinishedRecipe> consumerIn, String save) {
        ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
        if ((new ResourceLocation(save)).equals(resourcelocation)) {
            throw new IllegalStateException("Shapeless Recipe " + save + " should remove its 'save' argument");
        } else {
            this.save(consumerIn, new ResourceLocation(save));
        }
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void save(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
        this.validate(id);
        String advancementPath = this.result.getItemCategory() == null ? Mythscapes.MODID : this.result.getItemCategory().getRecipeFolderName();
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(IRequirementsStrategy.OR);
        consumerIn.accept(new NullableItemGroupShapelessRecipeBuilder.Result(id, this.result, this.count, this.group == null ? "" : this.group, this.ingredients, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + advancementPath + "/" + id.getPath())));
    }

    /**
     * Makes sure that this recipe is valid and obtainable.
     */
    private void validate(ResourceLocation id) {
        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<Ingredient> ingredients;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<Ingredient> ingredientsIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = resultIn;
            this.count = countIn;
            this.group = groupIn;
            this.ingredients = ingredientsIn;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();

            for(Ingredient ingredient : this.ingredients) {
                jsonarray.add(ingredient.toJson());
            }

            json.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            json.add("result", jsonobject);
        }

        @Override
        public IRecipeSerializer<?> getType() {
            return IRecipeSerializer.SHAPELESS_RECIPE;
        }

        /**
         * Gets the ID for the recipe.
         */
        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancementBuilder.serializeToJson();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {@link #serializeAdvancement}
         * is non-null.
         */
        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
