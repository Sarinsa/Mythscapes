package com.mythscapes.register;

import com.mythscapes.common.blocks.plant.AbstractHarvestableBlock;
import com.mythscapes.common.entities.MythBoatEntity;
import com.mythscapes.common.entities.StaticCottonEntity;
import com.mythscapes.common.items.*;
import com.mythscapes.common.items.armor.MythArmorItem;
import com.mythscapes.common.items.armor.MythArmorTypes;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.MythFoods;
import com.mythscapes.misc.MythItemGroup;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.mythscapes.register.MythBlocks.*;

public class MythItems {

    // Mod item group
    public static final ItemGroup itemGroup = MythItemGroup.MOD_ITEM_GROUP;
    // Item Register
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Mythscapes.MODID);

    private static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> blockSupplier) {
        return ITEMS.register(name, () -> new BlockItem(blockSupplier.get(), new Item.Properties().group(itemGroup)));
    }

    private static RegistryObject<Item> registerTallBlockItem(String name, Supplier<Block> blockSupplier) {
        return ITEMS.register(name, () -> new TallBlockItem(blockSupplier.get(), new Item.Properties().group(itemGroup)));
    }

    //---------------------------------------------------------------------------------------------------------------------
    //                                              BLOCK ITEMS
    //---------------------------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> GALVITE_ITEM = registerBlockItem("galvite", GALVITE);
    public static final RegistryObject<Item> GALVITE_SLAB_ITEM = registerBlockItem("galvite_slab", GALVITE_SLAB);
    public static final RegistryObject<Item> GALVITE_STAIRS_ITEM = registerBlockItem("galvite_stairs", GALVITE_STAIRS);
    public static final RegistryObject<Item> GALVITE_WALL_ITEM = registerBlockItem("galvite_wall", GALVITE_WALL);
    public static final RegistryObject<Item> POLISHED_GALVITE_ITEM = registerBlockItem("polished_galvite", POLISHED_GALVITE);
    public static final RegistryObject<Item> POLISHED_GALVITE_SLAB_ITEM = registerBlockItem("polished_galvite_slab", POLISHED_GALVITE_SLAB);
    public static final RegistryObject<Item> POLISHED_GALVITE_STAIRS_ITEM = registerBlockItem("polished_galvite_stairs", POLISHED_GALVITE_STAIRS);
    public static final RegistryObject<Item> POLISHED_GALVITE_WALL_ITEM = registerBlockItem("polished_galvite_wall", POLISHED_GALVITE_WALL);
    public static final RegistryObject<Item> POLISHED_GALVITE_PRESSURE_PLATE_ITEM = registerBlockItem("polished_galvite_pressure_plate", POLISHED_GALVITE_PRESSURE_PLATE);
    public static final RegistryObject<Item> POLISHED_GALVITE_BUTTON_ITEM = registerBlockItem("polished_galvite_button", POLISHED_GALVITE_BUTTON);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICKS_ITEM = registerBlockItem("polished_galvite_bricks", POLISHED_GALVITE_BRICKS);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_SLAB_ITEM = registerBlockItem("polished_galvite_brick_slab", POLISHED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_STAIRS_ITEM = registerBlockItem("polished_galvite_brick_stairs", POLISHED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_WALL_ITEM = registerBlockItem("polished_galvite_brick_wall", POLISHED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> CHISELED_POLISHED_GALVITE_ITEM = registerBlockItem("chiseled_polished_galvite", CHISELED_POLISHED_GALVITE);
    public static final RegistryObject<Item> GALVITE_SHINGLES_ITEM = registerBlockItem("galvite_shingles", GALVITE_SHINGLES);
    public static final RegistryObject<Item> GALVITE_SHINGLE_SLAB_ITEM = registerBlockItem("galvite_shingle_slab", GALVITE_SHINGLE_SLAB);
    public static final RegistryObject<Item> GALVITE_SHINGLE_STAIRS_ITEM = registerBlockItem("galvite_shingle_stairs", GALVITE_SHINGLE_STAIRS);
    public static final RegistryObject<Item> GILDED_GALVITE_ITEM = registerBlockItem("gilded_galvite", GILDED_GALVITE);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICKS_ITEM = registerBlockItem("gilded_galvite_bricks", GILDED_GALVITE_BRICKS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_SLAB_ITEM = registerBlockItem("gilded_galvite_brick_slab", GILDED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_STAIRS_ITEM = registerBlockItem("gilded_galvite_brick_stairs", GILDED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_WALL_ITEM = registerBlockItem("gilded_galvite_brick_wall", GILDED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_ITEM = registerBlockItem("bejeweled_galvite", BEJEWELED_GALVITE);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICKS_ITEM = registerBlockItem("bejeweled_galvite_bricks", BEJEWELED_GALVITE_BRICKS);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_SLAB_ITEM = registerBlockItem("bejeweled_galvite_brick_slab", BEJEWELED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_STAIRS_ITEM = registerBlockItem("bejeweled_galvite_brick_stairs", BEJEWELED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_WALL_ITEM = registerBlockItem("bejeweled_galvite_brick_wall", BEJEWELED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> POWERED_GALVITE_ITEM = registerBlockItem("powered_galvite", POWERED_GALVITE);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICKS_ITEM = registerBlockItem("powered_galvite_bricks", POWERED_GALVITE_BRICKS);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_SLAB_ITEM = registerBlockItem("powered_galvite_brick_slab", POWERED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_STAIRS_ITEM = registerBlockItem("powered_galvite_brick_stairs", POWERED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_WALL_ITEM = registerBlockItem("powered_galvite_brick_wall", POWERED_GALVITE_BRICK_WALL);

    public static final RegistryObject<Item> WOLT_LEAVES_ITEM = registerBlockItem("wolt_leaves", WOLT_LEAVES);
    public static final RegistryObject<Item> WOLT_LOG_ITEM = registerBlockItem("wolt_log", WOLT_LOG);
    public static final RegistryObject<Item> WOLT_WOOD_ITEM = registerBlockItem("wolt_wood", WOLT_WOOD);
    public static final RegistryObject<Item> WOLT_LOG_STRIPPED_ITEM = registerBlockItem("wolt_log_stripped", WOLT_LOG_STRIPPED);
    public static final RegistryObject<Item> WOLT_WOOD_STRIPPED_ITEM = registerBlockItem("wolt_wood_stripped", WOLT_WOOD_STRIPPED);
    public static final RegistryObject<Item> WOLT_PLANKS_ITEM = registerBlockItem("wolt_planks", WOLT_PLANKS);
    public static final RegistryObject<Item> WOLT_SLAB_ITEM = registerBlockItem("wolt_slab", WOLT_SLAB);
    public static final RegistryObject<Item> WOLT_STAIRS_ITEM = registerBlockItem("wolt_stairs", WOLT_STAIRS);
    public static final RegistryObject<Item> WOLT_FENCE_ITEM = registerBlockItem("wolt_fence", WOLT_FENCE);
    public static final RegistryObject<Item> WOLT_FENCE_GATE_ITEM = registerBlockItem("wolt_fence_gate", WOLT_FENCE_GATE);
    public static final RegistryObject<Item> WOLT_PRESSURE_PLATE_ITEM = registerBlockItem("wolt_pressure_plate", WOLT_PRESSURE_PLATE);
    public static final RegistryObject<Item> WOLT_BUTTON_ITEM = registerBlockItem("wolt_button", WOLT_BUTTON);
    public static final RegistryObject<Item> WOLT_DOOR_ITEM = registerTallBlockItem("wolt_door", WOLT_DOOR);
    public static final RegistryObject<Item> WOLT_TRAPDOOR_ITEM = registerBlockItem("wolt_trapdoor", WOLT_TRAPDOOR);
    public static final RegistryObject<Item> WOLT_SAPLING_ITEM = registerBlockItem("wolt_sapling", WOLT_SAPLING);

    public static final RegistryObject<Item> VIRIDIAN_STEM_ITEM = registerBlockItem("viridian_stem", VIRIDIAN_STEM);
    public static final RegistryObject<Item> VIRIDIAN_WOOD_ITEM = registerBlockItem("viridian_wood", VIRIDIAN_WOOD);
    public static final RegistryObject<Item> VIRIDIAN_STEM_STRIPPED_ITEM = registerBlockItem("viridian_stem_stripped", VIRIDIAN_STEM_STRIPPED);
    public static final RegistryObject<Item> VIRIDIAN_WOOD_STRIPPED_ITEM = registerBlockItem("viridian_wood_stripped", VIRIDIAN_WOOD_STRIPPED);
    public static final RegistryObject<Item> VIRIDIAN_PLANKS_ITEM = registerBlockItem("viridian_planks", VIRIDIAN_PLANKS);
    public static final RegistryObject<Item> VIRIDIAN_SLAB_ITEM = registerBlockItem("viridian_slab", VIRIDIAN_SLAB);
    public static final RegistryObject<Item> VIRIDIAN_STAIRS_ITEM = registerBlockItem("viridian_stairs", VIRIDIAN_STAIRS);
    public static final RegistryObject<Item> VIRIDIAN_FENCE_ITEM = registerBlockItem("viridian_fence", VIRIDIAN_FENCE);
    public static final RegistryObject<Item> VIRIDIAN_FENCE_GATE_ITEM = registerBlockItem("viridian_fence_gate", VIRIDIAN_FENCE_GATE);
    public static final RegistryObject<Item> VIRIDIAN_PRESSURE_PLATE_ITEM = registerBlockItem("viridian_pressure_plate", VIRIDIAN_PRESSURE_PLATE);
    public static final RegistryObject<Item> VIRIDIAN_BUTTON_ITEM = registerBlockItem("viridian_button", VIRIDIAN_BUTTON);

    public static final RegistryObject<Item> CHARGED_DANDELION_ITEM = registerBlockItem("charged_dandelion", CHARGED_DANDELION);

    public static final RegistryObject<Item> BIOBULB_CLUSTER_ITEM = registerBlockItem("biobulb_cluster", BIOBULB_CLUSTER);
    public static final RegistryObject<Item> ROASTED_BIOBULB_CLUSTER_ITEM = registerBlockItem("roasted_biobulb_cluster", ROASTED_BIOBULB_CLUSTER);
    public static final RegistryObject<Item> BIOBULB_LAMP_ITEM = registerBlockItem("biobulb_lamp", BIOBULB_LAMP);
    public static final RegistryObject<Item> STATIC_COTTON_BLOCK_ITEM = registerBlockItem("static_cotton_block", STATIC_COTTON_BLOCK);
    public static final RegistryObject<Item> STATIC_COTTON_LAYERS_ITEM = registerBlockItem("static_cotton_layers", STATIC_COTTON_LAYERS);


    //---------------------------------------------------------------------------------------------------------------------
    //                                                  ITEMS
    //---------------------------------------------------------------------------------------------------------------------

    // Misc
    public static final RegistryObject<Item> BLISTERBERRY = ITEMS.register("blisterberry", () -> new SeedItem((AbstractHarvestableBlock) BLISTERBERRY_THISTLE.get(), new Item.Properties().group(itemGroup).food(MythFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> ACTIVATED_BLISTERBERRY = ITEMS.register("activated_blisterberry", ActivatedBlisterBerryItem::new);
    public static final RegistryObject<Item> BIOBULB = ITEMS.register("biobulb", () -> new BaseItem(new Item.Properties().group(itemGroup).food(MythFoods.BIOBULB)));
    public static final RegistryObject<Item> ROASTED_BIOBULB = ITEMS.register("roasted_biobulb", () -> new BaseItem(new Item.Properties().group(itemGroup).food(MythFoods.ROASTED_BIOBULB)));
    public static final RegistryObject<Item> GLOWBALL = ITEMS.register("glowball", GlowballItem::new);
    public static final RegistryObject<Item> LIQUID_SULPHUR_BOTTLE = ITEMS.register("liquid_sulfur_bottle", BaseItem::new);
    public static final RegistryObject<Item> POND_SERPENT_FISH_BUCKET = ITEMS.register("pond_serpent_fish_bucket", () -> new FishBucketItem(MythEntities.POND_SERPENT, () -> Fluids.WATER, new Item.Properties().group(itemGroup).maxStackSize(1)));
    public static final RegistryObject<Item> STATIC_COTTON = ITEMS.register("static_cotton", StaticCottonItem::new);

    // Armor
    public static final RegistryObject<Item> COTTON_HOOD = ITEMS.register("cotton_hood", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> COTTON_COAT = ITEMS.register("cotton_coat", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> COTTON_PANTS = ITEMS.register("cotton_pants", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> COTTON_BOOTS = ITEMS.register("cotton_boots", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.FEET));

    // Spawn eggs
    public static final RegistryObject<Item> POND_SERPENT_SPAWN_EGG = ITEMS.register("pond_serpent_spawn_egg", () -> new MythSpawnEggItem(MythEntities.POND_SERPENT, 0xFFFF00, 0x0000FF));
    public static final RegistryObject<Item> LION_SPAWN_EGG = ITEMS.register("lion_spawn_egg", () -> new MythSpawnEggItem(MythEntities.LION, 0xFF00FF, 0x0FFF00));

    // Wood Items
    public static final RegistryObject<Item> WOLT_BOAT = ITEMS.register("wolt_boat", () -> new MythBoatItem(MythBoatEntity.Type.WOLT));
    public static final RegistryObject<Item> VIRIDIAN_BOAT = ITEMS.register("viridian_boat", () -> new MythBoatItem(MythBoatEntity.Type.VIRIDIAN));

    // Fluid buckets
    public static final RegistryObject<Item> LIQUID_SULPHUR_BUCKET = ITEMS.register("liquid_sulfur_bucket", () -> new BucketItem(MythFluids.SULFUR, new Item.Properties().group(itemGroup).maxStackSize(1)));


}
