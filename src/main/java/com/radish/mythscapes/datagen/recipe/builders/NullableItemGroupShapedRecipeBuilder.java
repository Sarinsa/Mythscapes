package com.radish.mythscapes.datagen.recipe.builders;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Essentially a copy of ShapedRecipeBuilder except
 * the result item can have null ItemGroup without
 * everything crashing and going to shit during data gen.
 */
public class NullableItemGroupShapedRecipeBuilder {

    public final Item result;
    public final int count;
    public final List<String> pattern = Lists.newArrayList();
    public final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    public final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    public String group;

    public NullableItemGroupShapedRecipeBuilder(IItemProvider resultIn, int countIn) {
        this.result = resultIn.asItem();
        this.count = countIn;
    }

    /**
     * Creates a new builder for a shaped recipe.
     */
    public static NullableItemGroupShapedRecipeBuilder shaped(IItemProvider resultIn) {
        return shaped(resultIn, 1);
    }

    /**
     * Creates a new builder for a shaped recipe.
     */
    public static NullableItemGroupShapedRecipeBuilder shaped(IItemProvider resultIn, int countIn) {
        return new NullableItemGroupShapedRecipeBuilder(resultIn, countIn);
    }

    /**
     * Adds a key to the recipe pattern.
     */
    public NullableItemGroupShapedRecipeBuilder define(Character symbol, ITag<Item> tagIn) {
        return this.define(symbol, Ingredient.of(tagIn));
    }

    /**
     * Adds a key to the recipe pattern.
     */
    public NullableItemGroupShapedRecipeBuilder define(Character symbol, IItemProvider itemIn) {
        return this.define(symbol, Ingredient.of(itemIn));
    }

    /**
     * Adds a key to the recipe pattern.
     */
    public NullableItemGroupShapedRecipeBuilder define(Character symbol, Ingredient ingredientIn) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(symbol, ingredientIn);
            return this;
        }
    }

    /**
     * Adds a new entry to the patterns for this recipe.
     */
    public NullableItemGroupShapedRecipeBuilder pattern(String patternIn) {
        if (!this.pattern.isEmpty() && patternIn.length() != this.pattern.get(0).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.pattern.add(patternIn);
            return this;
        }
    }

    /**
     * Adds a criterion needed to unlock the recipe.
     */
    public NullableItemGroupShapedRecipeBuilder unlockedBy(String name, ICriterionInstance criterionIn) {
        this.advancementBuilder.addCriterion(name, criterionIn);
        return this;
    }

    public NullableItemGroupShapedRecipeBuilder group(String groupIn) {
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
            throw new IllegalStateException("Shaped Recipe " + save + " should remove its 'save' argument");
        } else {
            this.save(consumerIn, new ResourceLocation(save));
        }
    }

    /**
     * Builds this recipe into an {@link IFinishedRecipe}.
     */
    public void save(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
        this.validate(id);
        String advancementPath = this.result.getItemCategory() == null ? Mythscapes.MODID : this.result.getItemCategory().getRecipeFolderName();
        this.advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(IRequirementsStrategy.OR);
        consumer.accept(new Result(id, this.result, this.count, this.group == null ? "" : this.group, this.pattern, this.key, this.advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + advancementPath + "/" + id.getPath())));
    }

    /**
     * Makes sure that this recipe is valid and obtainable.
     */
    public void validate(ResourceLocation id) {
        if (this.pattern.isEmpty()) {
            throw new IllegalStateException("No pattern is defined for shaped recipe " + id + "!");
        } else {
            Set<Character> set = Sets.newHashSet(this.key.keySet());
            set.remove(' ');

            for(String s : this.pattern) {
                for(int i = 0; i < s.length(); ++i) {
                    char c0 = s.charAt(i);
                    if (!this.key.containsKey(c0) && c0 != ' ') {
                        throw new IllegalStateException("Pattern in recipe " + id + " uses undefined symbol '" + c0 + "'");
                    }

                    set.remove(c0);
                }
            }

            if (!set.isEmpty()) {
                throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + id);
            } else if (this.pattern.size() == 1 && this.pattern.get(0).length() == 1) {
                throw new IllegalStateException("Shaped recipe " + id + " only takes in a single item - should it be a shapeless recipe instead?");
            } else if (this.advancementBuilder.getCriteria().isEmpty()) {
                throw new IllegalStateException("No way of obtaining recipe " + id);
            }
        }
    }

    public static class Result implements IFinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final List<String> pattern;
        private final Map<Character, Ingredient> key;
        private final Advancement.Builder advancementBuilder;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, List<String> patternIn, Map<Character, Ingredient> keyIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn) {
            this.id = idIn;
            this.result = resultIn;
            this.count = countIn;
            this.group = groupIn;
            this.pattern = patternIn;
            this.key = keyIn;
            this.advancementBuilder = advancementBuilderIn;
            this.advancementId = advancementIdIn;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();

            for(String s : this.pattern) {
                jsonarray.add(s);
            }

            json.add("pattern", jsonarray);
            JsonObject jsonobject = new JsonObject();

            for(Map.Entry<Character, Ingredient> entry : this.key.entrySet()) {
                jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
            }

            json.add("key", jsonobject);
            JsonObject jsonobject1 = new JsonObject();
            jsonobject1.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonobject1.addProperty("count", this.count);
            }
            json.add("result", jsonobject1);
        }

        @Override
        public IRecipeSerializer<?> getType() {
            return IRecipeSerializer.SHAPED_RECIPE;
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