package com.mythscapes.register;

import com.mythscapes.common.entities.MythBoatEntity;
import com.mythscapes.common.items.BaseItem;
import com.mythscapes.common.items.BlisterBerryItem;
import com.mythscapes.common.items.MythBoatItem;
import com.mythscapes.misc.MythFoods;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.MythItemGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.datafix.fixes.ItemSpawnEggSplit;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.mythscapes.register.MythBlocks.*;

public class MythItems {

    // Mod item group
    public static final ItemGroup itemGroup = MythItemGroup.MOD_ITEM_GROUP;
    // Default item properties
    public static final Item.Properties defaultProp = new Item.Properties().group(itemGroup);
    // Item Register
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Mythscapes.MODID);



    // Items
    public static final RegistryObject<Item> BLISTERBERRY = ITEMS.register("blisterberry", () -> new BaseItem(new Item.Properties().group(itemGroup).food(MythFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> ACTIVATED_BLISTERBERRY = ITEMS.register("activated_blisterberry", BlisterBerryItem::new);

    // public static final RegistryObject<Item> POND_SERPENT_EGG = ITEMS.register("pond_serpent_egg", () -> new SpawnEggItem(MythEntities.POND_SERPENT.get(), 0xFFFF00, 0x0000FF, defaultProp));

    // Boat Items
    public static final RegistryObject<Item> CHARGED_WOOD_BOAT = ITEMS.register("charged_wood_boat", () -> new MythBoatItem(MythBoatEntity.Type.CHARGED_WOOD, defaultProp));
    public static final RegistryObject<Item> VIRIDIAN_BOAT = ITEMS.register("viridian_boat", () -> new MythBoatItem(MythBoatEntity.Type.VIRIDIAN, defaultProp));

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
    public static final RegistryObject<BlockItem> CHARGED_SAPLING_ITEM = ITEMS.register("charged_sapling", () -> new BlockItem(CHARGED_SAPLING.get(), defaultProp));

    public static final RegistryObject<BlockItem> VIRIDIAN_STEM_ITEM = ITEMS.register("viridian_stem", () -> new BlockItem(VIRIDIAN_STEM.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_PLANKS_ITEM = ITEMS.register("viridian_planks", () -> new BlockItem(VIRIDIAN_PLANKS.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_SLAB_ITEM = ITEMS.register("viridian_slab", () -> new BlockItem(VIRIDIAN_SLAB.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_STAIRS_ITEM = ITEMS.register("viridian_stairs", () -> new BlockItem(VIRIDIAN_STAIRS.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_FENCE_ITEM = ITEMS.register("viridian_fence", () -> new BlockItem(VIRIDIAN_FENCE.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_FENCE_GATE_ITEM = ITEMS.register("viridian_fence_gate", () -> new BlockItem(VIRIDIAN_FENCE_GATE.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_PRESSURE_PLATE_ITEM = ITEMS.register("viridian_pressure_plate", () -> new BlockItem(VIRIDIAN_PRESSURE_PLATE.get(), defaultProp));
    public static final RegistryObject<BlockItem> VIRIDIAN_BUTTON_ITEM = ITEMS.register("viridian_button", () -> new BlockItem(VIRIDIAN_BUTTON.get(), defaultProp));

    public static final RegistryObject<BlockItem> BLISTER_BERRY_BUSH_TEMP = ITEMS.register("blisterberry_temp", () -> new BlockItem(BLISTERBERRY_THISTLE.get(), defaultProp));
}
