package com.radish.mythscapes.datagen.recipe;

import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.tags.MythItemTags;
import mekanism.api.datagen.recipe.builder.CombinerRecipeBuilder;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MythRecipeProvider extends AbstractRecipeProvider {

    public MythRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void registerRecipes(@NotNull Consumer<IFinishedRecipe> consumer) {
        this.consumer = consumer;

        // Stonecutting
        this.registerStonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICKS, MythItems.BEJEWELED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICK_SLAB, MythItems.BEJEWELED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICK_STAIRS, MythItems.BEJEWELED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.BEJEWELED_GALVITE_BRICK_WALL, MythItems.BEJEWELED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GILDED_GALVITE_BRICKS, MythItems.GILDED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GILDED_GALVITE_BRICK_SLAB, MythItems.GILDED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.GILDED_GALVITE_BRICK_STAIRS, MythItems.GILDED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GILDED_GALVITE_BRICK_WALL, MythItems.GILDED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POWERED_GALVITE_BRICKS, MythItems.POWERED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POWERED_GALVITE_BRICK_SLAB, MythItems.POWERED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POWERED_GALVITE_BRICK_STAIRS, MythItems.POWERED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POWERED_GALVITE_BRICK_WALL, MythItems.POWERED_GALVITE.get(), 1, consumer);

        this.registerStonecuttingRecipe(MythItems.GALVITE_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_WALL, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_WALL, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.CHISELED_POLISHED_GALVITE, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_WALL, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_STAIRS, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_WALL, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.CHISELED_POLISHED_GALVITE, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_STAIRS, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_WALL, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.POLISHED_GALVITE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.POLISHED_GALVITE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_SLAB, MythItems.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_STAIRS, MythItems.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_GALVITE_BRICK_WALL, MythItems.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.POLISHED_GALVITE_BRICKS.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.POLISHED_GALVITE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_SLAB, MythItems.GALVITE_SHINGLES.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.GALVITE_SHINGLE_STAIRS, MythItems.GALVITE_SHINGLES.get(), 1, consumer);

        this.registerStonecuttingRecipe(MythItems.TROLLSTONE_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.TROLLSTONE_STAIRS, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.TROLLSTONE_WALL, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_STAIRS, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_WALL, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_SLAB, MythItems.TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_STAIRS, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_WALL, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE, MythItems.TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_SLAB, MythItems.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_STAIRS, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_WALL, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_SLAB, MythItems.POLISHED_TROLLSTONE.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_STAIRS, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_WALL, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE, MythItems.POLISHED_TROLLSTONE.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_SLAB, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 2, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_STAIRS, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_BRICK_WALL, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);
        this.registerStonecuttingRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE, MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 1, consumer);

        // Shaped
        this.shapedRecipe(MythItems.POLISHED_GALVITE.get(), 4, MythItems.GALVITE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.GALVITE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.POLISHED_GALVITE_BRICKS.get(), 4, MythItems.POLISHED_GALVITE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.POLISHED_GALVITE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.CHISELED_POLISHED_GALVITE.get(), 1, MythItems.POLISHED_GALVITE_SLAB.get())
                .patternLine("#")
                .patternLine("#")
                .key('#', MythItems.POLISHED_GALVITE_SLAB.get())
                .build(consumer);

        this.shapedRecipe(MythItems.GALVITE_SHINGLES.get(), 4, MythItems.POLISHED_GALVITE.get())
                .patternLine("S#")
                .patternLine("#S")
                .key('#', MythItems.POLISHED_GALVITE.get())
                .key('S', MythItems.GALVITE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.GILDED_GALVITE_BRICKS.get(), 4, MythItems.GILDED_GALVITE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.GILDED_GALVITE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.BEJEWELED_GALVITE_BRICKS.get(), 4, MythItems.BEJEWELED_GALVITE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.BEJEWELED_GALVITE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.POWERED_GALVITE_BRICKS.get(), 4, MythItems.POWERED_GALVITE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.POWERED_GALVITE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.POLISHED_TROLLSTONE.get(), 4, MythItems.TROLLSTONE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.TROLLSTONE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.POLISHED_TROLLSTONE_BRICKS.get(), 4, MythItems.POLISHED_TROLLSTONE.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.POLISHED_TROLLSTONE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.POLISHED_TROLLSTONE_PILLAR.get(), 2, MythItems.POLISHED_TROLLSTONE.get())
                .patternLine("#")
                .patternLine("#")
                .key('#', MythItems.POLISHED_TROLLSTONE.get())
                .build(consumer);

        this.shapedRecipe(MythItems.CHISELED_POLISHED_TROLLSTONE.get(), 1, MythItems.POLISHED_TROLLSTONE_SLAB.get())
                .patternLine("#")
                .patternLine("#")
                .key('#', MythItems.POLISHED_TROLLSTONE_SLAB.get())
                .build(consumer);

        this.shapedRecipe(MythItems.WOLT_WOOD.get(), 3, MythItems.WOLT_LOG.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.WOLT_LOG.get())
                .build(consumer);

        this.shapedRecipe(MythItems.WOLT_WOOD_STRIPPED.get(), 3, MythItems.WOLT_LOG_STRIPPED.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.WOLT_LOG_STRIPPED.get())
                .build(consumer);

        this.shapedRecipe(MythItems.WOLT_DOOR.get(), 3, MythItems.WOLT_PLANKS.get())
                .setGroup("wooden_door")
                .patternLine("##")
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.WOLT_PLANKS.get())
                .build(consumer);

        this.shapedRecipe(MythItems.WOLT_TRAPDOOR.get(), 2, MythItems.WOLT_PLANKS.get())
                .setGroup("wooden_trapdoor")
                .patternLine("###")
                .patternLine("###")
                .key('#', MythItems.WOLT_PLANKS.get())
                .build(consumer);

        this.shapedRecipe(MythItems.WOLT_POWDER_BLOCK.get(), 1, MythItems.WOLT_POWDER.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', MythItems.WOLT_POWDER.get())
                .build(consumer);

        this.shapedRecipe(MythItems.GOLDEN_WOLT_POWDER_BLOCK.get(), 1, MythItems.GOLDEN_WOLT_POWDER.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', MythItems.GOLDEN_WOLT_POWDER.get())
                .build(consumer);

        this.shapedRecipe(MythItems.BIOBULB_CLUSTER.get(), 1, MythItems.BIOBULB.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.BIOBULB.get())
                .build(consumer);

        this.shapedRecipe(MythItems.ROASTED_BIOBULB_CLUSTER.get(), 1, MythItems.ROASTED_BIOBULB.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.ROASTED_BIOBULB.get())
                .build(consumer);

        this.shapedRecipe(MythItems.BIOBULB_LAMP.get(), 1, MythItems.BIOBULB.get())
                .patternLine(" # ")
                .patternLine("#B#")
                .patternLine(" # ")
                .key('#', Tags.Items.DUSTS_REDSTONE)
                .key('B', MythItems.BIOBULB.get())
                .build(consumer);

        this.shapedRecipe(MythItems.STATIC_COTTON_BLOCK.get(), 1, MythItems.STATIC_COTTON.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.STATIC_COTTON.get())
                .build(consumer);

        this.shapedRecipe(MythItems.STATIC_COTTON_PILES.get(), 6, MythItems.STATIC_COTTON_BLOCK.get())
                .patternLine("###")
                .key('#', MythItems.STATIC_COTTON_BLOCK.get())
                .build(consumer);

        this.shapedRecipe(MythItems.LAUNCHER_RAIL.get(), 6, MythItems.WOLT_POWDER.get())
                .patternLine("G G")
                .patternLine("GSG")
                .patternLine("GPG")
                .key('G', Tags.Items.INGOTS_GOLD)
                .key('S', Items.STICK)
                .key('P', MythItems.WOLT_POWDER.get())
                .build(consumer);

        this.shapedRecipe(MythItems.SNAIL_SHELL_BLOCK.get(), 1, MythItems.SNAIL_SHELL.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', MythItems.SNAIL_SHELL.get())
                .build(consumer);

        this.shapedRecipe(MythItems.SNAIL_SHELL_BRICKS.get(), 4, MythItems.SNAIL_SHELL_BLOCK.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.SNAIL_SHELL_BLOCK.get())
                .build(consumer);

        this.shapedRecipe(MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get(), 1, MythItems.BEJEWELED_SNAIL_SHELL.get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .key('#', MythItems.BEJEWELED_SNAIL_SHELL.get())
                .build(consumer);

        this.shapedRecipe(MythItems.BEJEWELED_SNAIL_SHELL_BRICKS.get(), 4, MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .patternLine("##")
                .patternLine("##")
                .key('#', MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .build(consumer);

        this.shapedRecipe(MythItems.GOLDEN_WOLT_FRUIT.get(), 1, MythItems.WOLT_FRUIT.get())
                .patternLine("GGG")
                .patternLine("GPG")
                .patternLine("GGG")
                .key('G', Tags.Items.INGOTS_GOLD)
                .key('P', MythItems.WOLT_FRUIT.get())
                .build(consumer);

        this.shapedRecipe(MythItems.GLOWBALL.get(), 1, MythItems.BIOBULB.get())
                .patternLine("BBB")
                .patternLine("BSG")
                .patternLine("GGG")
                .key('B', MythItems.BIOBULB.get())
                .key('S', Items.SNOWBALL)
                .key('G', Tags.Items.DUSTS_GLOWSTONE)
                .build(consumer);

        this.boatRecipe(MythItems.WOLT_BOAT.get(), MythItems.WOLT_PLANKS.get(), consumer);

        // Shapeless
        this.shapelessPlanksRecipe(MythItems.WOLT_PLANKS.get(), MythItemTags.WOLT_LOGS, consumer);

        this.shapelessRecipe(MythItems.WOLT_POWDER.get(), 9, MythItems.WOLT_POWDER_BLOCK.get())
                .addIngredient(MythItems.WOLT_POWDER_BLOCK.get())
                .build(consumer, "wolt_powder_from_wolt_powder_block");

        this.singleIngredientRecipe(MythItems.WOLT_POWDER.get(), 1, MythItems.WOLT_FRUIT.get());

        this.shapelessRecipe(MythItems.GOLDEN_WOLT_POWDER.get(), 9, MythItems.GOLDEN_WOLT_POWDER_BLOCK.get())
                .addIngredient(MythItems.GOLDEN_WOLT_POWDER_BLOCK.get())
                .build(consumer, "golden_wolt_powder_from_golden_wolt_powder_block");

        this.singleIngredientRecipe(MythItems.GOLDEN_WOLT_POWDER.get(), 1, MythItems.GOLDEN_WOLT_FRUIT.get());

        this.shapelessRecipe(MythItems.COTTON_HIDE.get(), 1, MythItems.STATIC_COTTON.get())
                .addIngredient(Items.LEATHER)
                .addIngredient(Items.LEATHER)
                .addIngredient(MythItems.STATIC_COTTON.get())
                .addIngredient(MythItems.STATIC_COTTON.get())
                .build(consumer);

        this.shapelessRecipe(MythItems.SNAIL_SHELL.get(), 9, MythItems.SNAIL_SHELL_BLOCK.get())
                .addIngredient(MythItems.SNAIL_SHELL_BLOCK.get())
                .build(consumer, "snail_shell_from_snail_shell_block");

        this.singleIngredientRecipe(Items.PURPLE_DYE, 1, MythItems.SNAIL_SHELL.get());

        this.shapelessRecipe(MythItems.BEJEWELED_SNAIL_SHELL.get(), 9, MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .addIngredient(MythItems.BEJEWELED_SNAIL_SHELL_BLOCK.get())
                .build(consumer, "bejeweled_snail_shell_from_bejeweled_snail_shell_block");

        this.shapelessRecipe(MythItems.COTTON_HOOD.get(), 1, MythItems.COTTON_HIDE.get())
                .addIngredient(MythItems.COTTON_HIDE.get())
                .addIngredient(Items.LEATHER_HELMET)
                .build(consumer);

        this.shapelessRecipe(MythItems.COTTON_COAT.get(), 1, MythItems.COTTON_HIDE.get())
                .addIngredient(MythItems.COTTON_HIDE.get())
                .addIngredient(Items.LEATHER_CHESTPLATE)
                .build(consumer);

        this.shapelessRecipe(MythItems.COTTON_PANTS.get(), 1, MythItems.COTTON_HIDE.get())
                .addIngredient(MythItems.COTTON_HIDE.get())
                .addIngredient(Items.LEATHER_LEGGINGS)
                .build(consumer);

        this.shapelessRecipe(MythItems.COTTON_BOOTS.get(), 1, MythItems.COTTON_HIDE.get())
                .addIngredient(MythItems.COTTON_HIDE.get())
                .addIngredient(Items.LEATHER_BOOTS)
                .build(consumer);

        this.slabRecipe(MythBlocks.GALVITE_SLAB.get(), MythBlocks.GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_GALVITE_SLAB.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get(), MythBlocks.POLISHED_GALVITE_BRICKS.get(), consumer);
        this.slabRecipe(MythBlocks.GALVITE_SHINGLE_SLAB.get(), MythBlocks.GALVITE_SHINGLES.get(), consumer);
        this.slabRecipe(MythBlocks.GILDED_GALVITE_BRICK_SLAB.get(), MythBlocks.GILDED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get(), MythBlocks.BEJEWELED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.POWERED_GALVITE_BRICK_SLAB.get(), MythBlocks.POWERED_GALVITE.get(), consumer);
        this.slabRecipe(MythBlocks.TROLLSTONE_SLAB.get(), MythBlocks.TROLLSTONE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_TROLLSTONE_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.slabRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), consumer);
        this.slabRecipe(MythBlocks.SNAIL_SHELL_BRICK_SLAB.get(), MythBlocks.SNAIL_SHELL_BRICKS.get(), consumer);
        this.slabRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get(), consumer);
        this.woodenSlabRecipe(MythBlocks.WOLT_SLAB.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.stairsRecipe(MythBlocks.GALVITE_STAIRS.get(), MythBlocks.GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_GALVITE_STAIRS.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS.get(), MythBlocks.POLISHED_GALVITE_BRICKS.get(), consumer);
        this.stairsRecipe(MythBlocks.GALVITE_SHINGLE_STAIRS.get(), MythBlocks.GALVITE_SHINGLES.get(), consumer);
        this.stairsRecipe(MythBlocks.GILDED_GALVITE_BRICK_STAIRS.get(), MythBlocks.GILDED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS.get(), MythBlocks.BEJEWELED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.POWERED_GALVITE_BRICK_STAIRS.get(), MythBlocks.POWERED_GALVITE.get(), consumer);
        this.stairsRecipe(MythBlocks.TROLLSTONE_STAIRS.get(), MythBlocks.TROLLSTONE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_TROLLSTONE_STAIRS.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.stairsRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS.get(), MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), consumer);
        this.stairsRecipe(MythBlocks.SNAIL_SHELL_BRICK_STAIRS.get(), MythBlocks.SNAIL_SHELL_BRICKS.get(), consumer);
        this.stairsRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_STAIRS.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get(), consumer);
        this.woodenStairsRecipe(MythBlocks.WOLT_STAIRS.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.wallRecipe(MythBlocks.GALVITE_WALL.get(), MythBlocks.GALVITE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_GALVITE_WALL.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_GALVITE_BRICK_WALL.get(), MythBlocks.POLISHED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.GILDED_GALVITE_BRICK_WALL.get(), MythBlocks.GILDED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL.get(), MythBlocks.BEJEWELED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.POWERED_GALVITE_BRICK_WALL.get(), MythBlocks.POWERED_GALVITE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.TROLLSTONE_WALL.get(), MythBlocks.TROLLSTONE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_TROLLSTONE_WALL.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.wallRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL.get(), MythBlocks.POLISHED_TROLLSTONE_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.SNAIL_SHELL_BRICK_WALL.get(), MythBlocks.SNAIL_SHELL_BRICKS.get(), consumer);
        this.wallRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_WALL.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS.get(), consumer);

        this.pressureplateRecipe(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.pressureplateRecipe(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.woodenPressureplateRecipe(MythBlocks.WOLT_PRESSURE_PLATE.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.buttonRecipe(MythBlocks.POLISHED_GALVITE_BUTTON.get(), MythBlocks.POLISHED_GALVITE.get(), consumer);
        this.buttonRecipe(MythBlocks.POLISHED_TROLLSTONE_BUTTON.get(), MythBlocks.POLISHED_TROLLSTONE.get(), consumer);
        this.woodenButtonRecipe(MythBlocks.WOLT_BUTTON.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.woodenFenceRecipe(MythBlocks.WOLT_FENCE.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

        this.woodenFenceGateRecipe(MythBlocks.WOLT_FENCE_GATE.get(), MythBlocks.WOLT_PLANKS.get(), consumer);

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

        // Quark compat
        this.quarkVerticalSlabRecipe(MythBlocks.GALVITE_VERTICAL_SLAB.get(), MythBlocks.GALVITE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_GALVITE_VERTICAL_SLAB.get(), MythBlocks.POLISHED_GALVITE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.POLISHED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.GALVITE_SHINGLE_VERTICAL_SLAB.get(), MythBlocks.GALVITE_SHINGLE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.GILDED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.GILDED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POWERED_GALVITE_BRICK_VERTICAL_SLAB.get(), MythBlocks.POWERED_GALVITE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.WOLT_VERTICAL_SLAB.get(), MythBlocks.WOLT_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.TROLLSTONE_VERTICAL_SLAB.get(), MythBlocks.TROLLSTONE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_TROLLSTONE_VERTICAL_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB.get(), MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(), MythBlocks.SNAIL_SHELL_BRICK_SLAB.get());
        this.quarkVerticalSlabRecipe(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB.get(), MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB.get());

        this.quarkVerticalPlanksRecipe(MythBlocks.WOLT_VERTICAL_PLANKS.get(), MythBlocks.WOLT_PLANKS.get());

        this.woodSignRecipe(MythItems.WOLT_SIGN.get(), MythBlocks.WOLT_PLANKS.get());

        this.quarkLeafCarpetRecipe(MythBlocks.WOLT_LEAF_CARPET.get(), MythBlocks.WOLT_LEAVES.get());

        this.quarkWoodenPostRecipe(MythBlocks.WOLT_POST.get(), MythBlocks.WOLT_FENCE.get());

        this.quarkLadderRecipe(MythBlocks.WOLT_LADDER.get(), MythBlocks.WOLT_PLANKS.get());

        this.quarkBookshelfRecipe(MythBlocks.WOLT_BOOKSHELF.get(), MythBlocks.WOLT_PLANKS.get());

        this.quarkChestRecipe(MythBlocks.WOLT_CHEST.get(), MythBlocks.WOLT_TRAPPED_CHEST.get(), MythBlocks.WOLT_PLANKS.get(), MythItemTags.WOLT_LOGS);

        // Mekanism compat
        this.modCompatRecipe(recipeConsumer -> {
            CombinerRecipeBuilder.combining(ItemStackIngredient.deserialize(itemFromName("mekanism:dust_diamond", 3)), ItemStackIngredient.from(MythItems.GALVITE.get()), new ItemStack(MythItems.BEJEWELED_GALVITE.get())).build(recipeConsumer);
        }, "mekanism");
        this.modCompatRecipe(recipeConsumer -> {
            CombinerRecipeBuilder.combining(ItemStackIngredient.from(Tags.Items.DUSTS_REDSTONE, 12), ItemStackIngredient.from(MythItems.GALVITE.get()), new ItemStack(MythItems.POWERED_GALVITE.get())).build(recipeConsumer);
        }, "mekanism");
        this.modCompatRecipe(recipeConsumer -> {
            CombinerRecipeBuilder.combining(ItemStackIngredient.deserialize(itemFromName("mekanism:dust_gold", 8)), ItemStackIngredient.from(MythItems.GALVITE.get()), new ItemStack(MythItems.GILDED_GALVITE.get())).build(recipeConsumer);
        }, "mekanism");
    }
}
