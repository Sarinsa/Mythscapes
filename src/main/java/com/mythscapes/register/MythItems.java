package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.MythItemGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.mythscapes.register.MythBlocks.*;

public class MythItems {

    // Default item properties
    public static final Item.Properties defaultProp = new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP);
    // Item Register
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Mythscapes.MODID);


    public static final RegistryObject<BlockItem> CHARGED_LOG_ITEM = ITEMS.register("charged_log", () -> new BlockItem(CHARGED_LOG.get(), defaultProp));
    public static final RegistryObject<BlockItem> CHARGED_PLANKS_ITEM = ITEMS.register("charged_planks", () -> new BlockItem(CHARGED_PLANKS.get(), defaultProp));
}
