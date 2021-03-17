package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.enchantments.SoothingEnchantment;
import com.radish.mythscapes.common.items.BrushItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Mythscapes.MODID);

    public static final EnchantmentType BRUSH = EnchantmentType.create("BRUSH", (item) -> item instanceof BrushItem);

    public static final RegistryObject<Enchantment> SOOTHING = ENCHANTMENTS.register("soothing", () -> new SoothingEnchantment(Enchantment.Rarity.UNCOMMON, BRUSH, EquipmentSlotType.MAINHAND));
}
