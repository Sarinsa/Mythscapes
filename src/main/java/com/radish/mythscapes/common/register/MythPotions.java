package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Mythscapes.MODID);

    public static final RegistryObject<Potion> PETRIFICATION = register("petrified", MythEffects.PETRIFIED, (20 * 15), 0);
    public static final RegistryObject<Potion> LONG_PETRIFICATION = register("long_petrified", MythEffects.PETRIFIED, (20 * 25), 0);

    public static void registerBrewingRecipes() {
        registerBrewingRecipe(Potions.AWKWARD, MythBlocks.TROLLSTONE.get(), PETRIFICATION.get());
        registerBrewingRecipe(PETRIFICATION.get(), Items.GLOWSTONE_DUST, LONG_PETRIFICATION.get());
    }

    private static RegistryObject<Potion> register(String name, Supplier<Effect> effectSupplier, int duration, int amplifier) {
        return POTIONS.register(name, () -> new Potion(new EffectInstance(effectSupplier.get(), duration, amplifier)));
    }

    private static void registerBrewingRecipe(Potion potionIngredient, IItemProvider itemIngredient, Potion potionResult) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(
                Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), potionIngredient)),
                Ingredient.fromStacks(new ItemStack(itemIngredient)),
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), potionResult)
        ));
    }

    private static void registerBrewingRecipe(IItemProvider potionIngredient, IItemProvider itemIngredient, IItemProvider potionResult) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(
                Ingredient.fromItems(potionIngredient),
                Ingredient.fromItems(itemIngredient),
                new ItemStack(potionResult)
        ));
    }
}
