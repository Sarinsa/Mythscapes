package com.mythscapes.register;

import com.mythscapes.common.enchantments.SoothingEnchantment;
import com.mythscapes.common.items.BrushItem;
import com.mythscapes.core.Mythscapes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEnchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Mythscapes.MODID);

    public static final EnchantmentType SOOTHING_TYPE = EnchantmentType.create("BRUSH", (item) -> item instanceof BrushItem);

    public static final RegistryObject<Enchantment> SOOTHING = ENCHANTMENTS.register("soothing", () -> new SoothingEnchantment(Enchantment.Rarity.UNCOMMON, SOOTHING_TYPE, EquipmentSlotType.MAINHAND));
}
