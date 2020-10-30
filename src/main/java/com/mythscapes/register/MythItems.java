package com.mythscapes.register;

import com.mythscapes.common.entities.misc.MythBoatEntity;
import com.mythscapes.common.items.*;
import com.mythscapes.common.items.armor.MythArmorItem;
import com.mythscapes.common.items.armor.MythArmorTypes;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.MythFoods;
import com.mythscapes.misc.MythItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.mythscapes.register.MythBlocks.BLISTERBERRY_THISTLE;

public class MythItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Mythscapes.MODID);

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    private static Item.Properties properties() {
        return new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP);
    }

    private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> blockSupplier) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new BlockItem(blockSupplier.get(), new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP)));
    }

    private static RegistryObject<Item> registerTallBlockItem(RegistryObject<Block> blockSupplier) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new TallBlockItem(blockSupplier.get(), new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP)));
    }



    //---------------------------------------------------------------------------------------------------------------------
    //                                              BLOCK ITEMS
    //---------------------------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> GILDED_GALVITE = registerBlockItem(MythBlocks.GILDED_GALVITE);
    public static final RegistryObject<Item> BEJEWELED_GALVITE = registerBlockItem(MythBlocks.BEJEWELED_GALVITE);
    public static final RegistryObject<Item> POWERED_GALVITE = registerBlockItem(MythBlocks.POWERED_GALVITE);

    public static final RegistryObject<Item> GALVITE = registerBlockItem(MythBlocks.GALVITE);
    public static final RegistryObject<Item> GALVITE_SLAB = registerBlockItem(MythBlocks.GALVITE_SLAB);
    public static final RegistryObject<Item> GALVITE_STAIRS = registerBlockItem(MythBlocks.GALVITE_STAIRS);
    public static final RegistryObject<Item> GALVITE_WALL = registerBlockItem(MythBlocks.GALVITE_WALL);
    public static final RegistryObject<Item> POLISHED_GALVITE = registerBlockItem(MythBlocks.POLISHED_GALVITE);
    public static final RegistryObject<Item> POLISHED_GALVITE_SLAB= registerBlockItem(MythBlocks.POLISHED_GALVITE_SLAB);
    public static final RegistryObject<Item> POLISHED_GALVITE_STAIRS = registerBlockItem(MythBlocks.POLISHED_GALVITE_STAIRS);
    public static final RegistryObject<Item> POLISHED_GALVITE_WALL = registerBlockItem(MythBlocks.POLISHED_GALVITE_WALL);
    public static final RegistryObject<Item> POLISHED_GALVITE_PRESSURE_PLATE = registerBlockItem(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE);
    public static final RegistryObject<Item> POLISHED_GALVITE_BUTTON = registerBlockItem(MythBlocks.POLISHED_GALVITE_BUTTON);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICKS = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICKS);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> CHISELED_POLISHED_GALVITE = registerBlockItem(MythBlocks.CHISELED_POLISHED_GALVITE);
    public static final RegistryObject<Item> GALVITE_SHINGLES = registerBlockItem(MythBlocks.GALVITE_SHINGLES);
    public static final RegistryObject<Item> GALVITE_SHINGLE_SLAB = registerBlockItem(MythBlocks.GALVITE_SHINGLE_SLAB);
    public static final RegistryObject<Item> GALVITE_SHINGLE_STAIRS = registerBlockItem(MythBlocks.GALVITE_SHINGLE_STAIRS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICKS = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICKS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICKS = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICKS);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICKS = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICKS);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_WALL);

    public static final RegistryObject<Item> TROLLSTONE = registerBlockItem(MythBlocks.TROLLSTONE);
    public static final RegistryObject<Item> TROLLSTONE_SLAB = registerBlockItem(MythBlocks.TROLLSTONE_SLAB);
    public static final RegistryObject<Item> TROLLSTONE_STAIRS = registerBlockItem(MythBlocks.TROLLSTONE_STAIRS);
    public static final RegistryObject<Item> TROLLSTONE_WALL = registerBlockItem(MythBlocks.TROLLSTONE_WALL);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_SLAB = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_SLAB);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_STAIRS = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_STAIRS);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_WALL = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_WALL);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_PRESSURE_PLATE = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BUTTON = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BUTTON);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICKS = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICKS);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_SLAB = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_STAIRS = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_WALL_ = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_PILLAR = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_PILLAR);
    public static final RegistryObject<Item> POLISHED_CHISELED_TROLLSTONE = registerBlockItem(MythBlocks.CHISELED_POLISHED_TROLLSTONE);

    public static final RegistryObject<Item> WOLT_LEAVES = registerBlockItem(MythBlocks.WOLT_LEAVES);

    public static final RegistryObject<Item> WOLT_LOG = registerBlockItem(MythBlocks.WOLT_LOG);
    public static final RegistryObject<Item> WOLT_WOOD = registerBlockItem(MythBlocks.WOLT_WOOD);
    public static final RegistryObject<Item> WOLT_LOG_STRIPPED = registerBlockItem(MythBlocks.WOLT_LOG_STRIPPED);
    public static final RegistryObject<Item> WOLT_WOOD_STRIPPED = registerBlockItem(MythBlocks.WOLT_WOOD_STRIPPED);
    public static final RegistryObject<Item> WOLT_PLANKS = registerBlockItem(MythBlocks.WOLT_PLANKS);
    public static final RegistryObject<Item> WOLT_SLAB = registerBlockItem(MythBlocks.WOLT_SLAB);
    public static final RegistryObject<Item> WOLT_STAIRS = registerBlockItem(MythBlocks.WOLT_STAIRS);
    public static final RegistryObject<Item> WOLT_FENCE = registerBlockItem(MythBlocks.WOLT_FENCE);
    public static final RegistryObject<Item> WOLT_FENCE_GATE = registerBlockItem(MythBlocks.WOLT_FENCE_GATE);
    public static final RegistryObject<Item> WOLT_PRESSURE_PLATE = registerBlockItem(MythBlocks.WOLT_PRESSURE_PLATE);
    public static final RegistryObject<Item> WOLT_BUTTON = registerBlockItem(MythBlocks.WOLT_BUTTON);
    public static final RegistryObject<Item> WOLT_DOOR = registerTallBlockItem(MythBlocks.WOLT_DOOR);
    public static final RegistryObject<Item> WOLT_TRAPDOOR = registerBlockItem(MythBlocks.WOLT_TRAPDOOR);
    public static final RegistryObject<Item> WOLT_SAPLING = registerBlockItem(MythBlocks.WOLT_SAPLING);

    /*
    public static final RegistryObject<Item> VIRIDIAN_STEM = registerBlockItem(MythBlocks.VIRIDIAN_STEM);
    public static final RegistryObject<Item> VIRIDIAN_WOOD = registerBlockItem(MythBlocks.VIRIDIAN_WOOD);
    public static final RegistryObject<Item> VIRIDIAN_STEM_STRIPPED = registerBlockItem(MythBlocks.VIRIDIAN_STEM_STRIPPED);
    public static final RegistryObject<Item> VIRIDIAN_WOOD_STRIPPED = registerBlockItem(MythBlocks.VIRIDIAN_WOOD_STRIPPED);
    public static final RegistryObject<Item> VIRIDIAN_PLANKS = registerBlockItem(MythBlocks.VIRIDIAN_PLANKS);
    public static final RegistryObject<Item> VIRIDIAN_SLAB = registerBlockItem(MythBlocks.VIRIDIAN_SLAB);
    public static final RegistryObject<Item> VIRIDIAN_STAIRS = registerBlockItem(MythBlocks.VIRIDIAN_STAIRS);
    public static final RegistryObject<Item> VIRIDIAN_FENCE = registerBlockItem(MythBlocks.VIRIDIAN_FENCE);
    public static final RegistryObject<Item> VIRIDIAN_FENCE_GATE = registerBlockItem(MythBlocks.VIRIDIAN_FENCE_GATE);
    public static final RegistryObject<Item> VIRIDIAN_PRESSURE_PLATE = registerBlockItem(MythBlocks.VIRIDIAN_PRESSURE_PLATE);
    public static final RegistryObject<Item> VIRIDIAN_BUTTON = registerBlockItem(MythBlocks.VIRIDIAN_BUTTON);
     */

    public static final RegistryObject<Item> CHARGED_DANDELION = registerBlockItem(MythBlocks.CHARGED_DANDELION);

    public static final RegistryObject<Item> WOLT_POWDER_BLOCK = registerBlockItem(MythBlocks.WOLT_POWDER_BLOCK);
    public static final RegistryObject<Item> GOLDEN_WOLT_POWDER_BLOCK = registerBlockItem(MythBlocks.GOLDEN_WOLT_POWDER_BLOCK);
    public static final RegistryObject<Item> BIOBULB_CLUSTER = registerBlockItem(MythBlocks.BIOBULB_CLUSTER);
    public static final RegistryObject<Item> ROASTED_BIOBULB_CLUSTER = registerBlockItem(MythBlocks.ROASTED_BIOBULB_CLUSTER);
    public static final RegistryObject<Item> BIOBULB_LAMP = registerBlockItem(MythBlocks.BIOBULB_LAMP);
    public static final RegistryObject<Item> STATIC_COTTON_BLOCK = registerBlockItem(MythBlocks.STATIC_COTTON_BLOCK);
    public static final RegistryObject<Item> STATIC_COTTON_PILES = registerBlockItem(MythBlocks.STATIC_COTTON_PILES);
    public static final RegistryObject<Item> LAUNCHER_RAIL = registerBlockItem(MythBlocks.LAUNCHER_RAIL);
    public static final RegistryObject<Item> SNAIL_SHELL_BLOCK = registerBlockItem(MythBlocks.SNAIL_SHELL_BLOCK);
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BLOCK = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BLOCK);


    //---------------------------------------------------------------------------------------------------------------------
    //                                                  ITEMS
    //---------------------------------------------------------------------------------------------------------------------

    // Misc
    public static final RegistryObject<Item> POND_SERPENT = registerItem("pond_serpent", () -> new Item(properties().food(MythFoods.POND_SERPENT)));
    public static final RegistryObject<Item> BLISTERBERRY = registerItem("blisterberry", () -> new SeedItem(BLISTERBERRY_THISTLE, properties().food(MythFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> ACTIVATED_BLISTERBERRY = registerItem("activated_blisterberry", () -> new MythThrowableItem<>(MythEntities.BLISTERBERRY).setCooldown(200));
    public static final RegistryObject<Item> BIOBULB = registerItem("biobulb", () -> new Item(properties().food(MythFoods.BIOBULB)));
    public static final RegistryObject<Item> ROASTED_BIOBULB = registerItem("roasted_biobulb", () -> new Item(properties().food(MythFoods.ROASTED_BIOBULB)));
    public static final RegistryObject<Item> WOLT_FRUIT = registerItem("wolt_fruit", () -> new WoltFruitItem(properties().food(MythFoods.WOLT_FRUIT), false));
    public static final RegistryObject<Item> GOLDEN_WOLT_FRUIT = registerItem("golden_wolt_fruit", () -> new WoltFruitItem(properties().food(MythFoods.GOLDEN_WOLT_FRUIT), true));
    public static final RegistryObject<Item> WOLT_POWDER = registerItem("wolt_powder", () -> new WoltPowderItem(properties(), false));
    public static final RegistryObject<Item> GOLDEN_WOLT_POWDER = registerItem("golden_wolt_powder", () -> new WoltPowderItem(properties(), true));
    public static final RegistryObject<Item> GLOWBALL = registerItem("glowball", () -> new GlowballItem(properties().maxStackSize(16)));
    public static final RegistryObject<Item> LIQUID_SULPHUR_BOTTLE = registerItem("liquid_sulfur_bottle", () -> new Item(properties().maxStackSize(16)));
    public static final RegistryObject<Item> POND_SERPENT_FISH_BUCKET = registerItem("pond_serpent_fish_bucket", () -> new FishBucketItem(MythEntities.POND_SERPENT, () -> Fluids.WATER, properties().maxStackSize(1)));
    public static final RegistryObject<Item> STATIC_COTTON = registerItem("static_cotton", () -> new MythThrowableItem<>(MythEntities.STATIC_COTTON));
    public static final RegistryObject<Item> COTTON_HIDE = registerItem("cotton_hide", () -> new Item(properties()));
    public static final RegistryObject<Item> LION_FUR = registerItem("lion_fur", () -> new Item(properties()));
    public static final RegistryObject<Item> SNAIL_SHELL = registerItem("snail_shell", () -> new Item(properties()));
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL = registerItem("bejeweled_snail_shell", () -> new Item(properties()));
    public static final RegistryObject<Item> SNAIL_BUCKET = registerItem("snail_bucket", () -> new SnailBucketItem(properties().maxStackSize(1)));
    public static final RegistryObject<Item> BRUSH = registerItem("brush", () -> new BrushItem(properties().maxDamage(100)));

    // Armor
    public static final RegistryObject<Item> COTTON_HOOD = ITEMS.register("cotton_hood", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> COTTON_COAT = ITEMS.register("cotton_coat", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> COTTON_PANTS = ITEMS.register("cotton_pants", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> COTTON_BOOTS = ITEMS.register("cotton_boots", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BARBARIAN_HOOD = ITEMS.register("barbarian_hood", () -> new MythArmorItem(MythArmorTypes.BARBARIAN, EquipmentSlotType.HEAD));

    // Boats
    public static final RegistryObject<Item> WOLT_BOAT = ITEMS.register("wolt_boat", () -> new MythBoatItem(MythBoatEntity.Type.WOLT));
    //public static final RegistryObject<Item> VIRIDIAN_BOAT = ITEMS.register("viridian_boat", () -> new MythBoatItem(MythBoatEntity.Type.VIRIDIAN));

    // Fluid buckets
    public static final RegistryObject<Item> LIQUID_SULFUR_BUCKET = ITEMS.register("liquid_sulfur_bucket", () -> new BucketItem(MythFluids.SULFUR, properties().maxStackSize(1)));

    // Spawn eggs
    public static final RegistryObject<Item> POND_SERPENT_SPAWN_EGG = ITEMS.register("pond_serpent_spawn_egg", () -> new MythSpawnEggItem(MythEntities.POND_SERPENT, 0x1Fa361, 0xe67008));
    public static final RegistryObject<Item> LION_SPAWN_EGG = ITEMS.register("lion_spawn_egg", () -> new MythSpawnEggItem(MythEntities.LION, 0xd3964d, 0x5F3c1a));
    public static final RegistryObject<Item> FISHBONES_SPAWN_EGG = ITEMS.register("fishbones_spawn_egg", () -> new MythSpawnEggItem(MythEntities.FISHBONES, 0xAFDEF5, 0x9CCAE1));
    public static final RegistryObject<Item> PYGMY_SNAIL_SPAWN_EGG = ITEMS.register("pygmy_snail_spawn_egg", () -> new MythSpawnEggItem(MythEntities.PYGMY_SNAIL, 0x513230, 0xCCAF99));

    public static void registerItemInfo() {
        registerCompostable(BIOBULB, 0.5f);
        registerCompostable(BIOBULB_CLUSTER, 0.85f);
        registerCompostable(BLISTERBERRY, 0.65f);
        registerCompostable(CHARGED_DANDELION, 0.3f);
        registerCompostable(ROASTED_BIOBULB, 0.65f);
        registerCompostable(ROASTED_BIOBULB_CLUSTER, 1.0f);
        registerCompostable(WOLT_FRUIT, 0.5f);
        registerCompostable(WOLT_LEAVES, 0.3f);
        registerCompostable(WOLT_POWDER_BLOCK, 0.65f);
        registerCompostable(WOLT_SAPLING, 0.3f);
    }

    private static void registerCompostable(Supplier<? extends IItemProvider> blockSupplier, float chance) {
        ComposterBlock.CHANCES.put(blockSupplier.get(), chance);
    }
}

