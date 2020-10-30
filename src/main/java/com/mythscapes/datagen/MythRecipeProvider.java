package com.mythscapes.datagen;

import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MythRecipeProvider extends RecipeProvider {

    public MythRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void registerRecipes(@NotNull Consumer<IFinishedRecipe> consumer) {
        // Stonecutting
        this.registerStonecuttingRecipe(MythBlocks.BEJEWELED_GALVITE_BRICKS, MythBlocks.BEJEWELED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB, MythBlocks.BEJEWELED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS, MythBlocks.BEJEWELED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL, MythBlocks.BEJEWELED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GILDED_GALVITE_BRICKS, MythBlocks.GILDED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GILDED_GALVITE_BRICK_SLAB, MythBlocks.GILDED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GILDED_GALVITE_BRICK_STAIRS, MythBlocks.GILDED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GILDED_GALVITE_BRICK_WALL, MythBlocks.GILDED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POWERED_GALVITE_BRICKS, MythBlocks.POWERED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POWERED_GALVITE_BRICK_SLAB, MythBlocks.POWERED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POWERED_GALVITE_BRICK_STAIRS, MythBlocks.POWERED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POWERED_GALVITE_BRICK_WALL, MythBlocks.POWERED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_SLAB, MythBlocks.POLISHED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_STAIRS, MythBlocks.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_WALL, MythBlocks.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.CHISELED_POLISHED_GALVITE, MythBlocks.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_BRICK_SLAB, MythBlocks.POLISHED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS, MythBlocks.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_BRICK_WALL, MythBlocks.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GALVITE_SHINGLE_SLAB, MythBlocks.POLISHED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GALVITE_SHINGLE_STAIRS, MythBlocks.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_BRICK_SLAB, MythBlocks.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS, MythBlocks.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_GALVITE_BRICK_WALL, MythBlocks.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GALVITE_SHINGLE_SLAB, MythBlocks.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GALVITE_SHINGLE_STAIRS, MythBlocks.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GALVITE_SHINGLE_SLAB, MythBlocks.GALVITE_SHINGLES.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.GALVITE_SHINGLE_STAIRS, MythBlocks.GALVITE_SHINGLES.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.TROLLSTONE_SLAB, MythBlocks.TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.TROLLSTONE_STAIRS, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.TROLLSTONE_WALL, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_SLAB, MythBlocks.TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_STAIRS, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_WALL, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB, MythBlocks.TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_PILLAR, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.CHISELED_POLISHED_TROLLSTONE, MythBlocks.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_SLAB, MythBlocks.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_STAIRS, MythBlocks.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_WALL, MythBlocks.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB, MythBlocks.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS, MythBlocks.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL, MythBlocks.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_PILLAR, MythBlocks.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.CHISELED_POLISHED_TROLLSTONE, MythBlocks.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB, MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS, MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL, MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.POLISHED_TROLLSTONE_PILLAR, MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythBlocks.CHISELED_POLISHED_TROLLSTONE, MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);

        // Shaped
        this.shapedRecipeWithCriterion(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE.get(), 1, MythBlocks.POLISHED_GALVITE.get())
                .key('#', MythBlocks.POLISHED_GALVITE.get())
                .patternLine("##")
                .build(consumer);
        this.shapedRecipeWithCriterion(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE.get(), 1, MythBlocks.POLISHED_TROLLSTONE.get())
                .key('#', MythBlocks.POLISHED_TROLLSTONE.get())
                .patternLine("##")
                .build(consumer);
        this.shapedRecipeWithCriterion(MythBlocks.WOLT_PRESSURE_PLATE.get(), 1, MythBlocks.WOLT_PLANKS.get())
                .key('#', MythBlocks.WOLT_PLANKS.get())
                .patternLine("##")
                .build(consumer);
        /*
        this.shapedRecipeWithCriterion(MythBlocks.VIRIDIAN_PRESSURE_PLATE.get(), 1, MythBlocks.VIRIDIAN_PLANKS.get())
                .key('#', MythBlocks.VIRIDIAN_PLANKS.get())
                .patternLine("##")
                .build(consumer);
         */


        // Shapeless
        this.shapelessRecipeWithCriterion(MythBlocks.POLISHED_GALVITE_BUTTON.get(), 1, MythBlocks.POLISHED_GALVITE.get())
                .addIngredient(MythBlocks.POLISHED_GALVITE.get())
                .build(consumer);
        this.shapelessRecipeWithCriterion(MythBlocks.POLISHED_TROLLSTONE_BUTTON.get(), 1, MythBlocks.POLISHED_TROLLSTONE.get())
                .addIngredient(MythBlocks.POLISHED_TROLLSTONE.get())
                .build(consumer);
        this.shapelessRecipeWithCriterion(MythBlocks.WOLT_BUTTON.get(), 1, MythBlocks.WOLT_PLANKS.get())
                .addIngredient(MythBlocks.WOLT_PLANKS.get())
                .build(consumer);
        /*
        this.shapelessRecipeWithCriterion(MythBlocks.VIRIDIAN_BUTTON.get(), 1, MythBlocks.VIRIDIAN_PLANKS.get())
                .addIngredient(MythBlocks.VIRIDIAN_PLANKS.get())
                .build(consumer);

         */

        // Smelting
        this.smeltingRecipe(MythBlocks.GILDED_GALVITE.get(), Items.GOLD_INGOT, 1.0F, consumer);
        this.smeltingRecipe(MythBlocks.BEJEWELED_GALVITE.get(), Items.DIAMOND, 1.0F, consumer);
        this.smeltingRecipe(MythBlocks.POWERED_GALVITE.get(), Items.REDSTONE, 0.7F, consumer);
        this.smeltingRecipe(MythBlocks.BIOBULB_CLUSTER.get(), MythBlocks.ROASTED_BIOBULB_CLUSTER.get(), 0.50F, consumer);
        this.smeltingRecipe(MythItems.BIOBULB.get(), MythItems.ROASTED_BIOBULB.get(), 0.35F, consumer);
        this.smeltingRecipe(MythItems.BLISTERBERRY.get(), MythItems.ACTIVATED_BLISTERBERRY.get(), 0.35F, consumer);

        // Smoking
        this.smokerRecipe(MythBlocks.BIOBULB_CLUSTER.get(), MythBlocks.ROASTED_BIOBULB_CLUSTER.get(), 0.50F, consumer);
        this.smokerRecipe(MythItems.BIOBULB.get(), MythItems.ROASTED_BIOBULB.get(), 0.35F, consumer);

        // Campfire cooking
        this.campfireRecipe(MythBlocks.BIOBULB_CLUSTER.get(), MythBlocks.ROASTED_BIOBULB_CLUSTER.get(), 0.50F, consumer);
        this.campfireRecipe(MythItems.BIOBULB.get(), MythItems.ROASTED_BIOBULB.get(), 0.35F, consumer);

        // Blasting
        this.blastingRecipe(MythBlocks.GILDED_GALVITE.get(), Items.GOLD_INGOT, 1.0F, consumer);
        this.blastingRecipe(MythBlocks.BEJEWELED_GALVITE.get(), Items.DIAMOND, 1.0F, consumer);
        this.blastingRecipe(MythBlocks.POWERED_GALVITE.get(), Items.REDSTONE, 0.7F, consumer);
    }

    private void registerStonecuttingRecipe(Supplier<Block> resultBlock, Block ingridientBlock, int count, Consumer<IFinishedRecipe> consumer) {
        String ingridientName = Objects.requireNonNull(ingridientBlock.getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(resultBlock.get().getRegistryName()).getPath();

        SingleItemRecipeBuilder
                .stonecuttingRecipe(Ingredient.fromItems(ingridientBlock), resultBlock.get(), count)
                .addCriterion("has_" + ingridientName, hasItem(ingridientBlock))
                .build(consumer, resultName + "_from_" + ingridientName + "_stonecutting");
    }

    private ShapedRecipeBuilder shapedRecipeWithCriterion(IItemProvider result, int count, IItemProvider criterionItem) {
        String criterionName = Objects.requireNonNull(criterionItem.asItem().getRegistryName()).getPath();
        return ShapedRecipeBuilder.shapedRecipe(result, count).addCriterion("has_" + criterionName, hasItem(criterionItem));
    }

    private ShapelessRecipeBuilder shapelessRecipeWithCriterion(IItemProvider result, int count, IItemProvider criterionItem) {
        String criterionName = Objects.requireNonNull(criterionItem.asItem().getRegistryName()).getPath();
        return ShapelessRecipeBuilder.shapelessRecipe(result, count).addCriterion("has_" + criterionName, hasItem(criterionItem));
    }

    private void smeltingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingridientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ingredient), result, experience, 200)
                .addCriterion("has_" + ingridientName, hasItem(ingredient))
                .build(consumer);
    }

    private void blastingRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingridientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ingredient), result, experience, 100)
                .addCriterion("has_" + ingridientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingridientName + "_blasting");
    }

    private void smokerRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingridientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();

        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, experience, 100, CookingRecipeSerializer.SMOKING)
                .addCriterion("has_" + ingridientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingridientName + "_smoking");
    }

    private void campfireRecipe(IItemProvider ingredient, IItemProvider result, float experience, Consumer<IFinishedRecipe> consumer) {
        String ingridientName = Objects.requireNonNull(ingredient.asItem().getRegistryName()).getPath();
        String resultName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();

        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ingredient), result, experience, 600, CookingRecipeSerializer.CAMPFIRE_COOKING)
                .addCriterion("has_" + ingridientName, hasItem(ingredient))
                .build(consumer, resultName + "_from_" + ingridientName + "_campfire");
    }
}
