package com.radish.mythscapes.datagen.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.recipe.conditions.QuarkFlagCondition;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.datagen.recipe.builders.NullableItemGroupShapedRecipeBuilder;
import com.radish.mythscapes.datagen.recipe.builders.NullableItemGroupShapelessRecipeBuilder;
import com.radish.mythscapes.datagen.recipe.builders.NullableItemGroupSingleItemRecipeBuilder;
import net.minecraft.block.Block;
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
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractRecipeProvider extends RecipeProvider {

    // Mod-IDs for compat
    protected static final String QUARK = "quark";

    protected Consumer<IFinishedRecipe> consumer;

    public AbstractRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    protected IItemProvider getFromRegistryName(String modid, String name) {
        ResourceLocation registryEntry = new ResourceLocation(modid, name);

        if (ForgeRegistries.ITEMS.containsKey(registryEntry)) {
            return ForgeRegistries.ITEMS.getValue(registryEntry);
        }
        else {
            Mythscapes.LOGGER.error("[{}] Could not find any item by the ID \"" + registryEntry.toString() + "\"", this.getClass().getSimpleName());
            return null;
        }
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
                    .build(this.consumer, finishedRecipe.getId());
        });
    }

    public void modCompatRecipe(String modid, Consumer<Consumer<IFinishedRecipe>> recipeBuilder) {
        ICondition condition = new ModLoadedCondition(modid);

        recipeBuilder.accept(finishedRecipe -> {
            ConditionalRecipe.builder()
                    .addCondition(condition)
                    .addRecipe(finishedRecipe)
                    .setAdvancement(ConditionalAdvancement.builder().addCondition(condition).addAdvancement(finishedRecipe))
                    .build(this.consumer, finishedRecipe.getId());
        });
    }

    public void modCompatRecipeNoAdvancement(String modid, Consumer<Consumer<IFinishedRecipe>> recipeBuilder) {
        ICondition condition = new ModLoadedCondition(modid);

        recipeBuilder.accept(finishedRecipe -> {
            ConditionalRecipe.builder()
                    .addCondition(condition)
                    .addRecipe(finishedRecipe)
                    .build(this.consumer, finishedRecipe.getId());
        });
    }

    public void quarkFlagRecipe(String quarkFlag, Consumer<Consumer<IFinishedRecipe>> recipeBuilder) {
        ICondition modCondition = new ModLoadedCondition(QUARK);
        ICondition condition = new QuarkFlagCondition(quarkFlag);

        recipeBuilder.accept(finishedRecipe -> {
            ConditionalRecipe.builder()
                    .addCondition(modCondition)
                    .addCondition(condition)
                    .addRecipe(finishedRecipe)
                    .setAdvancement(ConditionalAdvancement.builder().addCondition(modCondition).addCondition(condition).addAdvancement(finishedRecipe))
                    .build(this.consumer, finishedRecipe.getId());
        });
    }

    protected void stonecuttingRecipe(Supplier<Item> result, IItemProvider ingredient, int count, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result.get());

        NullableItemGroupSingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.of(ingredient), result.get(), count)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .build(consumer, resultName + "_from_" + ingredientName + "_stonecutting");
    }

    protected void compatStonecuttingRecipe(String modid, Supplier<Item> result, IItemProvider ingredient, int count, Consumer<IFinishedRecipe> consumer) {
        this.modCompatRecipe(modid, (recipeConsumer) -> {
            this.stonecuttingRecipe(result, ingredient, count, consumer);
        });
    }

    protected ShapedRecipeBuilder shaped(IItemProvider result, int count, IItemProvider criterionItem) {
        String criterionName = itemName(criterionItem);
        return ShapedRecipeBuilder.shaped(result, count)
                .unlockedBy("has_" + criterionName, has(criterionItem));
    }

    protected void stairsRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 4)
                .pattern("  #")
                .pattern(" ##")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void woodenStairsRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 4)
                .group("wooden_stairs")
                .pattern("  #")
                .pattern(" ##")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void wallRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 6)
                .pattern("###")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void pressureplateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 1)
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void woodenPressureplateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 1)
                .group("wooden_pressure_plate")
                .pattern("##")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void buttonRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapelessRecipeBuilder.shapeless(result)
                .requires(ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void woodenButtonRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapelessRecipeBuilder.shapeless(result)
                .requires(ingredient)
                .group("wooden_button")
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void slabRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 6)
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void woodenSlabRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 6)
                .group("wooden_slab")
                .pattern("###")
                .define('#', ingredient)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void woodenFenceRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 3)
                .group("wooden_fence")
                .pattern("#S#")
                .pattern("#S#")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void woodenFenceGateRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 1)
                .group("wooden_fence_gate")
                .pattern("S#S")
                .pattern("S#S")
                .define('#', ingredient)
                .define('S', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected void boatRecipe(IItemProvider result, IItemProvider ingredient, Consumer<IFinishedRecipe> consumer) {
        String criterionName = itemName(ingredient);
        ShapedRecipeBuilder.shaped(result, 1)
                .group("boat")
                .pattern("# #")
                .pattern("###")
                .define('#', MythItems.WOLT_PLANKS.get())
                .unlockedBy("has_" + criterionName, has(ingredient))
                .save(consumer);
    }

    protected ShapelessRecipeBuilder shapeless(IItemProvider result, int count, IItemProvider criterionIngredient) {
        String criterionName = itemName(criterionIngredient);
        return ShapelessRecipeBuilder.shapeless(result, count)
                .unlockedBy("has_" + criterionName, has(criterionIngredient));
    }

    protected ShapelessRecipeBuilder shapeless(IItemProvider result, int count, ITag.INamedTag<Item> criterionTag) {
        String criterionName = criterionTag.getName().getPath();
        return ShapelessRecipeBuilder.shapeless(result, count)
                .unlockedBy("has_" + criterionName, has(criterionTag));
    }

    protected void singleIngredientRecipe(IItemProvider result, int count, IItemProvider ingredient) {
        this.shapeless(result, count, ingredient)
                .requires(ingredient)
                .save(this.consumer);
    }

    protected void shapelessPlanksRecipe(IItemProvider result, ITag.INamedTag<Item> ingredientTag, Consumer<IFinishedRecipe> consumer) {
        shapeless(result, 4, ingredientTag)
                .group("planks")
                .requires(ingredientTag)
                .save(consumer);
    }

    protected void smeltingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(result);
        CookingRecipeBuilder.smelting(Ingredient.of(ingredient), result, experience, 200)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, Mythscapes.resourceLoc(ingredientName + "_from_smelting"));
    }

    protected void blastingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        CookingRecipeBuilder.blasting(Ingredient.of(ingredient), result, experience, 100)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, Mythscapes.resourceLoc(resultName + "_from_" + ingredientName + "_blasting"));
    }

    protected void smokerRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        CookingRecipeBuilder.cooking(Ingredient.of(ingredient), result, experience, 100, CookingRecipeSerializer.SMOKING_RECIPE)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, Mythscapes.resourceLoc(resultName + "_from_" + ingredientName + "_smoking"));
    }

    protected void campfireRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingredientName = itemName(ingredient);
        String resultName = itemName(result);

        CookingRecipeBuilder.cooking(Ingredient.of(ingredient), result, experience, 600, CookingRecipeSerializer.CAMPFIRE_COOKING_RECIPE)
                .unlockedBy("has_" + ingredientName, has(ingredient))
                .save(consumer, Mythscapes.resourceLoc(resultName + "_from_" + ingredientName + "_campfire"));
    }

    protected void quarkVerticalSlabRecipe(IItemProvider verticalSlab, IItemProvider normalSlab) {
        String vertical = itemName(verticalSlab);
        String horizontal = itemName(normalSlab);

        this.quarkFlagRecipe("vertical_slabs", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(verticalSlab, 3)
                    .pattern("#")
                    .pattern("#")
                    .pattern("#")
                    .define('#', normalSlab)
                    .unlockedBy("has_" + vertical, has(normalSlab))
                    .save(recipeConsumer);
        });

        this.quarkFlagRecipe("vertical_slabs", recipeConsumer -> {
            NullableItemGroupShapelessRecipeBuilder.shapeless(normalSlab)
                    .requires(verticalSlab)
                    .unlockedBy("has_" + horizontal, has(verticalSlab))
                    .save(recipeConsumer,  Mythscapes.resourceLoc(horizontal + "_from_" + vertical));
        });
    }

    protected void quarkVerticalPlanksRecipe(IItemProvider verticalPlanks, IItemProvider ingredient) {
        String vertical = itemName(verticalPlanks);
        String horizontal = itemName(ingredient);

        this.quarkFlagRecipe("vertical_planks", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(verticalPlanks, 3)
                    .pattern("#")
                    .pattern("#")
                    .pattern("#")
                    .define('#', ingredient)
                    .unlockedBy("has_" + vertical, has(ingredient))
                    .save(recipeConsumer);
        });

        this.quarkFlagRecipe("vertical_planks", recipeConsumer -> {
            NullableItemGroupShapelessRecipeBuilder.shapeless(ingredient)
                    .requires(verticalPlanks)
                    .unlockedBy("has_" + horizontal, has(verticalPlanks))
                    .save(recipeConsumer, Mythscapes.resourceLoc(horizontal + "_from_" + vertical));
        });
    }

    protected void woodSignRecipe(IItemProvider woodSign, IItemProvider ingredient) {
        this.modCompatRecipe(QUARK, recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(woodSign, 3)
                    .pattern("###")
                    .pattern("###")
                    .pattern(" S ")
                    .define('#', ingredient)
                    .define('S', Tags.Items.RODS_WOODEN)
                    .unlockedBy("has_" + itemName(ingredient), has(ingredient))
                    .save(recipeConsumer);
        });
    }

    protected void quarkLeafCarpetRecipe(IItemProvider leafCarpet, IItemProvider ingredient) {
        this.quarkFlagRecipe("leaf_carpet", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(leafCarpet, 3)
                    .pattern("##")
                    .define('#', ingredient)
                    .unlockedBy("has_" + itemName(ingredient), has(ingredient))
                    .save(recipeConsumer);
        });
    }

    protected void quarkLadderRecipe(IItemProvider ladder, IItemProvider ingredient) {
        this.quarkFlagRecipe("variant_ladders", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(ladder, 4)
                    .pattern("# #")
                    .pattern("#P#")
                    .pattern("# #")
                    .define('#', Tags.Items.RODS_WOODEN)
                    .define('P', ingredient)
                    .unlockedBy("has_" + itemName(ingredient), has(ingredient))
                    .save(recipeConsumer);
        });
    }

    protected void quarkWoodenPostRecipe(IItemProvider woodPost, IItemProvider ingredient) {
        this.quarkFlagRecipe("wooden_posts", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(woodPost, 8)
                    .pattern("#")
                    .pattern("#")
                    .pattern("#")
                    .define('#', ingredient)
                    .unlockedBy("has_" + itemName(ingredient), has(ingredient))
                    .save(recipeConsumer);
        });
    }

    protected void quarkBookshelfRecipe(IItemProvider bookshelf, IItemProvider ingredient) {
        this.quarkFlagRecipe("variant_bookshelves", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(bookshelf, 1)
                    .pattern("###")
                    .pattern("BBB")
                    .pattern("###")
                    .define('#', ingredient)
                    .define('B', Items.BOOK)
                    .unlockedBy("has_" + itemName(ingredient), has(ingredient))
                    .save(recipeConsumer);
        });
    }

    /**
     * @param chest The normal chest block from the wood set
     * @param trappedChest The trapped chest block from the wood set
     * @param planks The planks block from the wood set
     * @param logs The Item Tag of logs belonging to the wood set
     */
    protected void quarkChestRecipe(IItemProvider chest, IItemProvider trappedChest, IItemProvider planks, ITag.INamedTag<Item> logs) {
        String chestName = Objects.requireNonNull(chest.asItem().getRegistryName()).getPath();
        String planksName = Objects.requireNonNull(planks.asItem().getRegistryName()).getPath();
        String logsName = logs.getName().getPath();


        this.quarkFlagRecipe("variant_chests", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(chest, 4)
                    .pattern("###")
                    .pattern("# #")
                    .pattern("###")
                    .define('#', logs)
                    .unlockedBy("has_" + logsName, has(logs))
                    .save(recipeConsumer, Mythscapes.resourceLoc(chestName + "_from_" + logsName));
        });

        this.quarkFlagRecipe("variant_chests", recipeConsumer -> {
            NullableItemGroupShapedRecipeBuilder.shaped(chest, 1)
                    .pattern("###")
                    .pattern("# #")
                    .pattern("###")
                    .define('#', planks)
                    .unlockedBy("has_" + planksName, has(planks))
                    .save(recipeConsumer, Mythscapes.resourceLoc(chestName + "_from_" + planksName));
        });

        this.quarkFlagRecipe("variant_chests", recipeConsumer -> {
            NullableItemGroupShapelessRecipeBuilder.shapeless(trappedChest, 1)
                    .requires(chest)
                    .requires(Items.TRIPWIRE_HOOK)
                    .unlockedBy("has_" + chestName, has(chest))
                    .save(recipeConsumer);
        });
    }
}
