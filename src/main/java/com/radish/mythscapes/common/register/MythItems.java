package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.blocks.compat.MythChestBlock;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.misc.MythBoatEntity;
import com.radish.mythscapes.common.items.*;
import com.radish.mythscapes.common.items.armor.MythArmorItem;
import com.radish.mythscapes.common.items.armor.MythArmorTypes;
import com.radish.mythscapes.common.items.MythFoods;
import com.radish.mythscapes.common.misc.MythItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.radish.mythscapes.common.register.MythBlocks.BLISTERBERRY_THISTLE;

public class MythItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Mythscapes.MODID);

    // modid references
    private static final String QUARK = "quark";

    //---------------------------------------------------------------------------------------------------------------------
    //                                              BLOCK ITEMS
    //---------------------------------------------------------------------------------------------------------------------
    public static final RegistryObject<Item> GILDED_GALVITE = registerBlockItem(MythBlocks.GILDED_GALVITE);
    public static final RegistryObject<Item> BEJEWELED_GALVITE = registerBlockItem(MythBlocks.BEJEWELED_GALVITE);
    public static final RegistryObject<Item> POWERED_GALVITE = registerBlockItem(MythBlocks.POWERED_GALVITE);

    public static final RegistryObject<Item> GALVITE = registerBlockItem(MythBlocks.GALVITE);
    public static final RegistryObject<Item> GALVITE_SLAB = registerBlockItem(MythBlocks.GALVITE_SLAB);
    public static final RegistryObject<Item> GALVITE_VERTICAL_SLAB = registerBlockItem(MythBlocks.GALVITE_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> GALVITE_STAIRS = registerBlockItem(MythBlocks.GALVITE_STAIRS);
    public static final RegistryObject<Item> GALVITE_WALL = registerBlockItem(MythBlocks.GALVITE_WALL);
    public static final RegistryObject<Item> POLISHED_GALVITE = registerBlockItem(MythBlocks.POLISHED_GALVITE);
    public static final RegistryObject<Item> POLISHED_GALVITE_SLAB = registerBlockItem(MythBlocks.POLISHED_GALVITE_SLAB);
    public static final RegistryObject<Item> POLISHED_GALVITE_VERTICAL_SLAB = registerBlockItem(MythBlocks.POLISHED_GALVITE_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> POLISHED_GALVITE_STAIRS = registerBlockItem(MythBlocks.POLISHED_GALVITE_STAIRS);
    public static final RegistryObject<Item> POLISHED_GALVITE_WALL = registerBlockItem(MythBlocks.POLISHED_GALVITE_WALL);
    public static final RegistryObject<Item> POLISHED_GALVITE_PRESSURE_PLATE = registerBlockItem(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE);
    public static final RegistryObject<Item> POLISHED_GALVITE_BUTTON = registerBlockItem(MythBlocks.POLISHED_GALVITE_BUTTON);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICKS = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICKS);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> POLISHED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.POLISHED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> CHISELED_POLISHED_GALVITE = registerBlockItem(MythBlocks.CHISELED_POLISHED_GALVITE);
    public static final RegistryObject<Item> GALVITE_SHINGLES = registerBlockItem(MythBlocks.GALVITE_SHINGLES);
    public static final RegistryObject<Item> GALVITE_SHINGLE_SLAB = registerBlockItem(MythBlocks.GALVITE_SHINGLE_SLAB);
    public static final RegistryObject<Item> GALVITE_SHINGLE_VERTICAL_SLAB = registerBlockItem(MythBlocks.GALVITE_SHINGLE_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> GALVITE_SHINGLE_STAIRS = registerBlockItem(MythBlocks.GALVITE_SHINGLE_STAIRS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICKS = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICKS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> GILDED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.GILDED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICKS = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICKS);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> BEJEWELED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICKS = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICKS);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_SLAB = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_SLAB);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_STAIRS = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_STAIRS);
    public static final RegistryObject<Item> POWERED_GALVITE_BRICK_WALL = registerBlockItem(MythBlocks.POWERED_GALVITE_BRICK_WALL);

    public static final RegistryObject<Item> TROLLSTONE = registerBlockItem(MythBlocks.TROLLSTONE);
    public static final RegistryObject<Item> TROLLSTONE_SLAB = registerBlockItem(MythBlocks.TROLLSTONE_SLAB);
    public static final RegistryObject<Item> TROLLSTONE_VERTICAL_SLAB = registerBlockItem(MythBlocks.TROLLSTONE_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> TROLLSTONE_STAIRS = registerBlockItem(MythBlocks.TROLLSTONE_STAIRS);
    public static final RegistryObject<Item> TROLLSTONE_WALL = registerBlockItem(MythBlocks.TROLLSTONE_WALL);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_SLAB = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_SLAB);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_VERTICAL_SLAB = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_STAIRS = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_STAIRS);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_WALL = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_WALL);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_PRESSURE_PLATE = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BUTTON = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BUTTON);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICKS = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICKS);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_SLAB = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_STAIRS = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_BRICK_WALL = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL);
    public static final RegistryObject<Item> POLISHED_TROLLSTONE_PILLAR = registerBlockItem(MythBlocks.POLISHED_TROLLSTONE_PILLAR);
    public static final RegistryObject<Item> CHISELED_POLISHED_TROLLSTONE = registerBlockItem(MythBlocks.CHISELED_POLISHED_TROLLSTONE);

    public static final RegistryObject<Item> WOLT_LOG = registerBlockItem(MythBlocks.WOLT_LOG);
    public static final RegistryObject<Item> WOLT_WOOD = registerBlockItem(MythBlocks.WOLT_WOOD);
    public static final RegistryObject<Item> WOLT_LOG_STRIPPED = registerBlockItem(MythBlocks.WOLT_LOG_STRIPPED);
    public static final RegistryObject<Item> WOLT_WOOD_STRIPPED = registerBlockItem(MythBlocks.WOLT_WOOD_STRIPPED);
    public static final RegistryObject<Item> WOLT_PLANKS = registerBlockItem(MythBlocks.WOLT_PLANKS);
    public static final RegistryObject<Item> WOLT_VERTICAL_PLANKS = registerBlockItem(MythBlocks.WOLT_VERTICAL_PLANKS, compatProperties(QUARK));
    public static final RegistryObject<Item> WOLT_SLAB = registerBlockItem(MythBlocks.WOLT_SLAB);
    public static final RegistryObject<Item> WOLT_VERTICAL_SLAB = registerBurnableBlockItem(MythBlocks.WOLT_VERTICAL_SLAB, compatProperties(QUARK), 150);
    public static final RegistryObject<Item> WOLT_STAIRS = registerBlockItem(MythBlocks.WOLT_STAIRS);
    public static final RegistryObject<Item> WOLT_FENCE = registerBurnableBlockItem(MythBlocks.WOLT_FENCE, properties(), 300);
    public static final RegistryObject<Item> WOLT_FENCE_GATE = registerBurnableBlockItem(MythBlocks.WOLT_FENCE_GATE, properties(), 300);
    public static final RegistryObject<Item> WOLT_PRESSURE_PLATE = registerBlockItem(MythBlocks.WOLT_PRESSURE_PLATE);
    public static final RegistryObject<Item> WOLT_BUTTON = registerBlockItem(MythBlocks.WOLT_BUTTON);
    public static final RegistryObject<Item> WOLT_DOOR = registerTallBlockItem(MythBlocks.WOLT_DOOR);
    public static final RegistryObject<Item> WOLT_TRAPDOOR = registerBlockItem(MythBlocks.WOLT_TRAPDOOR);
    public static final RegistryObject<Item> WOLT_SIGN = registerItem("wolt_sign", () -> new ModSignItem(compatProperties(QUARK).maxStackSize(16), MythBlocks.WOLT_SIGN.get(), MythBlocks.WOLT_WALL_SIGN.get(), 200));
    public static final RegistryObject<Item> WOLT_LADDER = registerBurnableBlockItem(MythBlocks.WOLT_LADDER, compatProperties(QUARK), 300);
    public static final RegistryObject<Item> WOLT_BOOKSHELF = registerBurnableBlockItem(MythBlocks.WOLT_BOOKSHELF, compatProperties(QUARK), 300);
    public static final RegistryObject<Item> WOLT_POST = registerBurnableBlockItem(MythBlocks.WOLT_POST, compatProperties(QUARK), 300);
    public static final RegistryObject<Item> WOLT_POST_STRIPPED = registerBurnableBlockItem(MythBlocks.WOLT_POST_STRIPPED, compatProperties(QUARK), 300);
    public static final RegistryObject<Item> WOLT_HEDGE = registerBurnableBlockItem(MythBlocks.WOLT_HEDGE, compatProperties(QUARK), 300);
    public static final RegistryObject<Item> WOLT_CHEST = registerChestItem(MythBlocks.WOLT_CHEST, compatProperties(QUARK));
    public static final RegistryObject<Item> WOLT_TRAPPED_CHEST = registerChestItem(MythBlocks.WOLT_TRAPPED_CHEST, compatProperties(QUARK));
    public static final RegistryObject<Item> WOLT_SAPLING = registerBlockItem(MythBlocks.WOLT_SAPLING);
    public static final RegistryObject<Item> WOLT_LEAVES = registerBlockItem(MythBlocks.WOLT_LEAVES);
    public static final RegistryObject<Item> WOLT_LEAF_CARPET = registerBlockItem(MythBlocks.WOLT_LEAF_CARPET, compatProperties(QUARK));

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
    public static final RegistryObject<Item> SNAIL_SHELL_BRICKS = registerBlockItem(MythBlocks.SNAIL_SHELL_BRICKS);
    public static final RegistryObject<Item> SNAIL_SHELL_BRICK_SLAB = registerBlockItem(MythBlocks.SNAIL_SHELL_BRICK_SLAB);
    public static final RegistryObject<Item> SNAIL_SHELL_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.SNAIL_SHELL_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> SNAIL_SHELL_BRICK_STAIRS = registerBlockItem(MythBlocks.SNAIL_SHELL_BRICK_STAIRS);
    public static final RegistryObject<Item> SNAIL_SHELL_BRICK_WALL = registerBlockItem(MythBlocks.SNAIL_SHELL_BRICK_WALL);
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BLOCK = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BLOCK);
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BRICKS = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS);
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BRICK_SLAB = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB);
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB, compatProperties(QUARK));
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BRICK_STAIRS = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_STAIRS);
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL_BRICK_WALL = registerBlockItem(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_WALL);


    //---------------------------------------------------------------------------------------------------------------------
    //                                                  ITEMS
    //---------------------------------------------------------------------------------------------------------------------

    // Misc
    public static final RegistryObject<Item> POND_SERPENT = registerItem("pond_serpent", () -> new Item(properties().food(MythFoods.POND_SERPENT)));
    public static final RegistryObject<Item> BLISTERBERRY = registerItem("blisterberry", () -> new BlockNamedItem(BLISTERBERRY_THISTLE.get(), properties().food(MythFoods.BLISTERBERRY)));
    public static final RegistryObject<Item> ACTIVATED_BLISTERBERRY = registerItem("activated_blisterberry", () -> new MythThrowableItem<>(MythEntities.BLISTERBERRY).setCooldown(200));
    public static final RegistryObject<Item> BIOBULB = registerItem("biobulb", () -> new Item(properties().food(MythFoods.BIOBULB)));
    public static final RegistryObject<Item> ROASTED_BIOBULB = registerItem("roasted_biobulb", () -> new Item(properties().food(MythFoods.ROASTED_BIOBULB)));
    public static final RegistryObject<Item> WOLT_FRUIT = registerItem("wolt_fruit", () -> new WoltFruitItem(properties().food(MythFoods.WOLT_FRUIT), false));
    public static final RegistryObject<Item> GOLDEN_WOLT_FRUIT = registerItem("golden_wolt_fruit", () -> new WoltFruitItem(properties().food(MythFoods.GOLDEN_WOLT_FRUIT), true));
    public static final RegistryObject<Item> WOLT_POWDER = registerItem("wolt_powder", () -> new WoltPowderItem(properties(), false));
    public static final RegistryObject<Item> GOLDEN_WOLT_POWDER = registerItem("golden_wolt_powder", () -> new WoltPowderItem(properties(), true));
    public static final RegistryObject<Item> GLOWBALL = registerItem("glowball", () -> new GlowballItem(properties().maxStackSize(16)));
    public static final RegistryObject<Item> LIQUID_SULFUR_BOTTLE = registerItem("liquid_sulfur_bottle", () -> new DrinkableBottleItem(properties().maxStackSize(16), () -> new EffectInstance(MythEffects.VOLATILE.get(), 650)));
    public static final RegistryObject<Item> POND_SERPENT_FISH_BUCKET = registerItem("pond_serpent_fish_bucket", () -> new FishBucketItem(MythEntities.POND_SERPENT, () -> Fluids.WATER, properties().maxStackSize(1)));
    public static final RegistryObject<Item> STATIC_COTTON = registerItem("static_cotton", () -> new MythThrowableItem<>(MythEntities.STATIC_COTTON));
    public static final RegistryObject<Item> COTTON_HIDE = registerItem("cotton_hide", () -> new Item(properties()));
    public static final RegistryObject<Item> LION_FUR = registerItem("lion_fur", () -> new Item(properties()));
    public static final RegistryObject<Item> ANTLER = registerItem("antler", () -> new Item(properties()));
    public static final RegistryObject<Item> SNAIL_SHELL = registerItem("snail_shell", () -> new Item(properties()));
    public static final RegistryObject<Item> BEJEWELED_SNAIL_SHELL = registerItem("bejeweled_snail_shell", () -> new Item(properties()));
    public static final RegistryObject<Item> SNAIL_BUCKET = registerItem("snail_bucket", () -> new SnailBucketItem(properties().maxStackSize(1)));
    public static final RegistryObject<Item> BRUSH = registerItem("brush", () -> new BrushItem(properties().maxDamage(100)));

    // Armor
    public static final RegistryObject<Item> COTTON_HOOD = registerItem("cotton_hood", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> COTTON_COAT = registerItem("cotton_coat", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> COTTON_PANTS = registerItem("cotton_pants", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> COTTON_BOOTS = registerItem("cotton_boots", () -> new MythArmorItem(MythArmorTypes.COTTON, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> BARBARIAN_HOOD = registerItem("barbarian_hood", () -> new MythArmorItem(MythArmorTypes.BARBARIAN, EquipmentSlotType.HEAD));

    // Boats
    public static final RegistryObject<Item> WOLT_BOAT = registerItem("wolt_boat", () -> new MythBoatItem(MythBoatEntity.Type.WOLT));
    //public static final RegistryObject<Item> VIRIDIAN_BOAT = ITEMS.register("viridian_boat", () -> new MythBoatItem(MythBoatEntity.Type.VIRIDIAN));

    // Fluid buckets
    public static final RegistryObject<Item> LIQUID_SULFUR_BUCKET = registerItem("liquid_sulfur_bucket", () -> new BucketItem(MythFluids.SULFUR.getStill(), properties().maxStackSize(1)));

    // Spawn eggs
    public static final RegistryObject<Item> POND_SERPENT_SPAWN_EGG = registerSpawnEgg("pond_serpent_spawn_egg", MythEntities.POND_SERPENT_TYPE, 0x1Fa361, 0xe67008);
    public static final RegistryObject<Item> LION_SPAWN_EGG = registerSpawnEgg("lion_spawn_egg", MythEntities.LION_TYPE, 0xd3964d, 0x5F3c1a);
    public static final RegistryObject<Item> FISHBONES_SPAWN_EGG = registerSpawnEgg("fishbones_spawn_egg", MythEntities.FISHBONES_TYPE, 0xAFDEF5, 0x9CCAE1);
    public static final RegistryObject<Item> PYGMY_SNAIL_SPAWN_EGG = registerSpawnEgg("pygmy_snail_spawn_egg", MythEntities.PYGMY_SNAIL_TYPE, 0x513230, 0xCCAF99);
    public static final RegistryObject<Item> DEER_SPAWN_EGG = registerSpawnEgg("deer_spawn_egg", MythEntities.DEER_TYPE, 0x8A5E46, 0xFFF1E5);

    public static void registerItemData() {
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

    private static Item.Properties properties() {
        return new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP);
    }

    private static Item.Properties compatProperties(String modid) {
        return ModList.get().isLoaded(modid) ? properties() : new Item.Properties();
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> blockSupplier) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new BlockItem(blockSupplier.get(), properties()));
    }

    private static RegistryObject<Item> registerBlockItem(RegistryObject<Block> blockSupplier, Item.Properties properties) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new BlockItem(blockSupplier.get(), properties));
    }

    private static RegistryObject<Item> registerChestItem(RegistryObject<Block> blockSupplier, Item.Properties properties) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new ChestBlockItem((MythChestBlock) blockSupplier.get(), properties));
    }

    private static RegistryObject<Item> registerSpawnEgg(String name, EntityType<?> entityType, int primaryColor, int secondaryColor) {
        return registerItem(name, () -> new SpawnEggItem(entityType, primaryColor, secondaryColor, properties()));
    }

    private static RegistryObject<Item> registerBurnableBlockItem(RegistryObject<Block> blockSupplier, Item.Properties properties, int burnTime) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new BurnableBlockItem(blockSupplier.get(), properties, burnTime));
    }

    private static RegistryObject<Item> registerTallBlockItem(RegistryObject<Block> blockSupplier) {
        return ITEMS.register(blockSupplier.getId().getPath(), () -> new TallBlockItem(blockSupplier.get(), properties()));
    }
}

