package com.mythscapes.register;

import com.mythscapes.common.items.BlisterBerryItem;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.MythItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.mythscapes.register.MythBlocks.*;

public class MythItems {

    // Default item properties
    public static final Item.Properties defaultProp = new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP);
    // Item Register
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Mythscapes.MODID);


    // Items
    public static final RegistryObject<Item> BLISTER_BERRY = ITEMS.register("blister_berry", () -> new BlisterBerryItem(false));
    public static final RegistryObject<Item> ACTIVATED_BLISTER_BERRY = ITEMS.register("activated_blister_berry", () -> new BlisterBerryItem(true));


    // Block Items
    public static final RegistryObject<BlockItem> CHARGED_LEAVES_ITEM = ITEMS.register("charged_leaves", () -> new BlockItem(CHARGED_LEAVES.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_LOG_ITEM = ITEMS.register("charged_log", () -> new BlockItem(CHARGED_LOG.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_ITEM = ITEMS.register("charged_wood", () -> new BlockItem(CHARGED_WOOD.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_PLANKS_ITEM = ITEMS.register("charged_wood_planks", () -> new BlockItem(CHARGED_PLANKS.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_SLAB_ITEM = ITEMS.register("charged_wood_slab", () -> new BlockItem(CHARGED_WOOD_SLAB.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_STAIRS_ITEM = ITEMS.register("charged_wood_stairs", () -> new BlockItem(CHARGED_WOOD_STAIRS.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_FENCE_ITEM = ITEMS.register("charged_wood_fence", () -> new BlockItem(CHARGED_WOOD_FENCE.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_FENCE_GATE_ITEM = ITEMS.register("charged_wood_fence_gate", () -> new BlockItem(CHARGED_WOOD_FENCE_GATE.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_PRESSURE_PLATE_ITEM = ITEMS.register("charged_wood_pressure_plate", () -> new BlockItem(CHARGED_WOOD_PRESSURE_PLATE.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_WOOD_BUTTON_ITEM = ITEMS.register("charged_wood_button", () -> new BlockItem(CHARGED_WOOD_BUTTON.get(), defaultProp));
}
