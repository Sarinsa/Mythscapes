package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;
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
        registerBrewingRecipe(PETRIFICATION.get(), Potions.AWKWARD, Ingredient.of(MythBlocks.TROLLSTONE.get()));
        registerBrewingRecipe(PETRIFICATION.get(), LONG_PETRIFICATION.get(), Ingredient.of(Tags.Items.DUSTS_GLOWSTONE));
    }

    private static RegistryObject<Potion> register(String name, Supplier<Effect> effectSupplier, int duration, int amplifier) {
        return POTIONS.register(name, () -> new Potion(new EffectInstance(effectSupplier.get(), duration, amplifier)));
    }

    private static void registerBrewingRecipe(Potion potionResult, Potion potionIngredient, Ingredient itemIngredient) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), potionIngredient)),
                itemIngredient,
                PotionUtils.setPotion(new ItemStack(Items.POTION), potionResult)
        ));
    }

    private static void registerBrewingRecipe(IItemProvider potionResult, Ingredient potionIngredient, Ingredient itemIngredient) {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(
                potionIngredient,
                itemIngredient,
                new ItemStack(potionResult)
        ));
    }
}
