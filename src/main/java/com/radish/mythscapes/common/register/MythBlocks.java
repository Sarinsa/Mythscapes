package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.blocks.*;
import com.radish.mythscapes.common.blocks.compat.*;
import com.radish.mythscapes.common.blocks.fluid.LiquidSulphurFluidBlock;
import com.radish.mythscapes.common.blocks.plant.BlisterberryThistleBlock;
import com.radish.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.radish.mythscapes.common.blocks.plant.ModLeavesBlock;
import com.radish.mythscapes.common.blocks.wood.*;
import com.radish.mythscapes.common.blocks.wood.tree.WoltTree;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.AxeItem;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class MythBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Mythscapes.MODID);

    // Galvite
    public static final RegistryObject<Block> GILDED_GALVITE = registerBlock("gilded_galvite", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).sound(SoundType.STONE).strength(3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BEJEWELED_GALVITE = registerBlock("bejeweled_galvite", () -> new OreBlock(AbstractBlock.Properties.copy(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE = registerBlock("powered_galvite", () -> new RedstoneOreBlock(AbstractBlock.Properties.copy(GILDED_GALVITE.get()).randomTicks().lightLevel(lightValueLit(9))));
    public static final RegistryObject<Block> GALVITE = registerBlock("galvite", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BLUE).sound(SoundType.STONE).strength(1.0f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GALVITE_SLAB = registerBlock("galvite_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_VERTICAL_SLAB = registerBlock("galvite_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_STAIRS = registerBlock("galvite_stairs", () -> new StairsBlock(GALVITE.get()::defaultBlockState, AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_WALL = registerBlock("galvite_wall", () -> new WallBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE = registerBlock("polished_galvite", () -> new Block(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_SLAB = registerBlock("polished_galvite_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_VERTICAL_SLAB = registerBlock("polished_galvite_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_STAIRS = registerBlock("polished_galvite_stairs", () -> new StairsBlock(POLISHED_GALVITE.get()::defaultBlockState, AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_WALL = registerBlock("polished_galvite_wall", () -> new WallBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_PRESSURE_PLATE = registerBlock("polished_galvite_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BUTTON = registerBlock("polished_galvite_button", () -> new StoneButtonBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICKS = registerBlock("polished_galvite_bricks", () -> new Block(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_SLAB = registerBlock("polished_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_VERTICAL_SLAB = registerBlock("polished_galvite_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_STAIRS = registerBlock("polished_galvite_brick_stairs", () -> new StairsBlock(POLISHED_GALVITE_BRICKS.get()::defaultBlockState, AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_WALL = registerBlock("polished_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> CHISELED_POLISHED_GALVITE = registerBlock("chiseled_polished_galvite", () -> new Block(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLES = registerBlock("galvite_shingles", () -> new Block(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_SLAB = registerBlock("galvite_shingle_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_VERTICAL_SLAB = registerBlock("galvite_shingle_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_STAIRS = registerBlock("galvite_shingle_stairs", () -> new StairsBlock(GALVITE.get()::defaultBlockState, AbstractBlock.Properties.copy(GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICKS = registerBlock("gilded_galvite_bricks", () -> new Block(AbstractBlock.Properties.copy(GALVITE.get()).strength(1.4f)));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_SLAB = registerBlock("gilded_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_VERTICAL_SLAB = registerBlock("gilded_galvite_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_STAIRS = registerBlock("gilded_galvite_brick_stairs", () -> new StairsBlock(GILDED_GALVITE.get()::defaultBlockState, AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_WALL = registerBlock("gilded_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICKS = registerBlock("bejeweled_galvite_bricks", () -> new Block(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_SLAB = registerBlock("bejeweled_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB = registerBlock("bejeweled_galvite_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_STAIRS = registerBlock("bejeweled_galvite_brick_stairs", () -> new StairsBlock(BEJEWELED_GALVITE.get()::defaultBlockState, AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_WALL = registerBlock("bejeweled_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICKS = registerBlock("powered_galvite_bricks", () -> new Block(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_SLAB = registerBlock("powered_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_VERTICAL_SLAB = registerBlock("powered_galvite_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_STAIRS = registerBlock("powered_galvite_brick_stairs", () -> new StairsBlock(POWERED_GALVITE.get()::defaultBlockState, AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_WALL = registerBlock("powered_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(GILDED_GALVITE_BRICKS.get())));

    // Trollstone
    public static final RegistryObject<Block> TROLLSTONE = registerBlock("trollstone", () -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).sound(SoundType.STONE).strength(2.0F, 6.0F).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TROLLSTONE_SLAB = registerBlock("trollstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> TROLLSTONE_VERTICAL_SLAB = registerBlock("trollstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> TROLLSTONE_STAIRS = registerBlock("trollstone_stairs", () -> new StairsBlock(TROLLSTONE.get()::defaultBlockState, AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> TROLLSTONE_WALL = registerBlock("trollstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE = registerBlock("polished_trollstone", () -> new Block(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_SLAB = registerBlock("polished_trollstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_VERTICAL_SLAB = registerBlock("polished_trollstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_STAIRS = registerBlock("polished_trollstone_stairs", () -> new StairsBlock(POLISHED_TROLLSTONE.get()::defaultBlockState, AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_WALL = registerBlock("polished_trollstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_PRESSURE_PLATE = registerBlock("polished_trollstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BUTTON = registerBlock("polished_trollstone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICKS = registerBlock("polished_trollstone_bricks", () -> new Block(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_SLAB = registerBlock("polished_trollstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB = registerBlock("polished_trollstone_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_STAIRS = registerBlock("polished_trollstone_brick_stairs", () -> new StairsBlock(POLISHED_TROLLSTONE_BRICKS.get()::defaultBlockState, AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_WALL = registerBlock("polished_trollstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_PILLAR = registerBlock("polished_trollstone_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(TROLLSTONE.get())));
    public static final RegistryObject<Block> CHISELED_POLISHED_TROLLSTONE = registerBlock("chiseled_polished_trollstone", () -> new Block(AbstractBlock.Properties.copy(TROLLSTONE.get())));

    // Wolt Wood
    public static final RegistryObject<Block> WOLT_LOG = registerBlock("wolt_log", () -> new ModLogBlock(logProp(Material.WOOD, MaterialColor.ICE, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_WOOD = registerBlock("wolt_wood", () -> new ModLogBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f)));
    public static final RegistryObject<Block> WOLT_LOG_STRIPPED = registerBlock("wolt_log_stripped", () -> new ModLogBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_WOOD_STRIPPED = registerBlock("wolt_wood_stripped", () -> new ModLogBlock(AbstractBlock.Properties.copy(WOLT_WOOD.get())));
    public static final RegistryObject<Block> WOLT_PLANKS = registerBlock("wolt_planks", () -> new ModPlanksBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_VERTICAL_PLANKS = registerBlock("wolt_vertical_planks", () -> new ModPlanksBlock(AbstractBlock.Properties.copy(WOLT_PLANKS.get())));
    public static final RegistryObject<Block> WOLT_SLAB = registerBlock("wolt_slab", () -> new ModWoodSlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_VERTICAL_SLAB = registerBlock("wolt_vertical_slab", () -> new VerticalWoodSlabBlock(AbstractBlock.Properties.copy(WOLT_PLANKS.get())));
    public static final RegistryObject<Block> WOLT_STAIRS = registerBlock("wolt_stairs", () -> new ModWoodStairsBlock(WOLT_PLANKS.get()::defaultBlockState, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_FENCE = registerBlock("wolt_fence", () -> new ModWoodFenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_FENCE_GATE = registerBlock("wolt_fence_gate", () -> new ModWoodFenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_PRESSURE_PLATE = registerBlock("wolt_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> WOLT_BUTTON = registerBlock("wolt_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> WOLT_DOOR = registerBlock("wolt_door", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> WOLT_TRAPDOOR = registerBlock("wolt_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(3.0f).noOcclusion()));
    public static final RegistryObject<Block> WOLT_SIGN = registerBlock("wolt_sign", () -> new ModStandingSignBlock(AbstractBlock.Properties.copy(WOLT_PLANKS.get()).noCollission(), "wolt"));
    public static final RegistryObject<Block> WOLT_WALL_SIGN = registerBlock("wolt_wall_sign", () -> new ModWallSignBlock(AbstractBlock.Properties.copy(WOLT_SIGN.get()).lootFrom(WOLT_SIGN), "wolt"));
    public static final RegistryObject<Block> WOLT_LADDER = registerBlock("wolt_ladder", () -> new LadderBlock(AbstractBlock.Properties.copy(WOLT_PLANKS.get()).sound(SoundType.LADDER).noOcclusion()));
    public static final RegistryObject<Block> WOLT_BOOKSHELF = registerBlock("wolt_bookshelf", () -> new ModBookshelfBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).harvestTool(ToolType.AXE).strength(1.5F)));
    public static final RegistryObject<Block> WOLT_POST = registerBlock("wolt_post", () -> new WoodPostBlock(AbstractBlock.Properties.copy(WOLT_PLANKS.get())));
    public static final RegistryObject<Block> WOLT_POST_STRIPPED = registerBlock("wolt_post_stripped", () -> new WoodPostBlock(AbstractBlock.Properties.copy(WOLT_PLANKS.get())));
    public static final RegistryObject<Block> WOLT_HEDGE = registerBlock("wolt_hedge", () -> new MythHedgeBlock(AbstractBlock.Properties.copy(WOLT_FENCE.get())));
    public static final RegistryObject<Block> WOLT_CHEST = registerBlock("wolt_chest", () -> new MythChestBlock(AbstractBlock.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.5F).harvestTool(ToolType.AXE), MythTileEntities.CHEST::get, "wolt"));
    public static final RegistryObject<Block> WOLT_TRAPPED_CHEST = registerBlock("wolt_trapped_chest", () -> new MythTrappedChestBlock(AbstractBlock.Properties.copy(WOLT_CHEST.get()), MythTileEntities.CHEST::get, "wolt"));
    public static final RegistryObject<Block> WOLT_SAPLING = registerBlock("wolt_sapling", () -> new ModSaplingBlock(new WoltTree(), AbstractBlock.Properties.of(Material.PLANT, MaterialColor.COLOR_CYAN).sound(SoundType.GRASS).strength(0.0f).noCollission().randomTicks()));
    public static final RegistryObject<Block> WOLT_LEAVES = registerBlock("wolt_leaves", () -> new ModLeavesBlock(AbstractBlock.Properties.of(Material.LEAVES, MaterialColor.COLOR_CYAN).sound(SoundType.GRASS).strength(0.2F).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> WOLT_LEAF_CARPET = registerBlock("wolt_leaf_carpet", LeafCarpetBlock::new);

    // Viridian Wood
    /*
    public static final RegistryObject<Block> VIRIDIAN_STEM = registerBlock("viridian_stem", () -> new RotatedPillarBlock(logProp(Material.WOOD, MaterialColor.WHITE_TERRACOTTA, MaterialColor.LIME).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_WOOD = registerBlock("viridian_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STEM_STRIPPED = registerBlock("viridian_stem_stripped", () -> new RotatedPillarBlock(logProp(Material.WOOD, MaterialColor.WHITE_TERRACOTTA, MaterialColor.LIME)));
    public static final RegistryObject<Block> VIRIDIAN_WOOD_STRIPPED = registerBlock("viridian_wood_stripped", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(VIRIDIAN_WOOD.get())));
    public static final RegistryObject<Block> VIRIDIAN_PLANKS = registerBlock("viridian_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_SLAB = registerBlock("viridian_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STAIRS = registerBlock("viridian_stairs", () -> new StairsBlock(WOLT_PLANKS.get()::defaultBlockState, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE = registerBlock("viridian_fence", () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE_GATE = registerBlock("viridian_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_PRESSURE_PLATE = registerBlock("viridian_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(0.5f)));
    public static final RegistryObject<Block> VIRIDIAN_BUTTON = registerBlock("viridian_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).strength(0.5f)));
    */

    // Plants
    public static final RegistryObject<Block> BLISTERBERRY_THISTLE = registerBlock("blisterberry_thistle", () -> new BlisterberryThistleBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_BLACK).sound(SoundType.GRASS).strength(0.0f).randomTicks().noOcclusion().noCollission().lightLevel((state) -> state.getValue(BlockStateProperties.AGE_5) == 5 ? 5 : 0)));
    public static final RegistryObject<Block> CHARGED_DANDELION = registerBlock("charged_dandelion", () -> new ChargedDandelionBlock(MythEffects.STATIC, 17, AbstractBlock.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.GRASS).strength(0.0f).noOcclusion().noCollission().randomTicks()));
    public static final RegistryObject<Block> POTTED_CHARGED_DANDELION = registerBlock("potted_charged_dandelion", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHARGED_DANDELION, AbstractBlock.Properties.of(Material.DECORATION).strength(0.0f).noOcclusion()));
    public static final RegistryObject<Block> POTTED_WOLT_SAPLING = registerBlock("potted_wolt_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WOLT_SAPLING, AbstractBlock.Properties.of(Material.DECORATION).strength(0.0f).noOcclusion()));

    // Misc
    public static final RegistryObject<Block> WOLT_POWDER_BLOCK = registerBlock("wolt_powder_block", () -> new WoltPowderBlock(1.2D, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).sound(SoundType.STONE).strength(0.5f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GOLDEN_WOLT_POWDER_BLOCK = registerBlock("golden_wolt_powder_block", () -> new WoltPowderBlock(1.7D, AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).sound(SoundType.STONE).strength(0.5f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> BIOBULB_CLUSTER = registerBlock("biobulb_cluster", () -> new Block(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_CYAN).sound(SoundType.GLASS).strength(0.3f).lightLevel((state) -> 13).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> ROASTED_BIOBULB_CLUSTER = registerBlock("roasted_biobulb_cluster", () -> new Block(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_GREEN).sound(SoundType.GLASS).strength(0.3f).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> BIOBULB_LAMP = registerBlock("biobulb_lamp", () -> new RedstoneLampBlock(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS, MaterialColor.COLOR_GREEN).sound(SoundType.GLASS).strength(0.3F).lightLevel(lightValueLit(15))));
    public static final RegistryObject<Block> STATIC_COTTON_BLOCK = registerBlock("static_cotton_block", () -> new StaticCottonBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.WOOL).strength(0.8f)));
    public static final RegistryObject<Block> STATIC_COTTON_PILES = registerBlock("static_cotton_piles", () -> new StaticCottonPilesBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.TERRACOTTA_WHITE).sound(SoundType.WOOL).strength(0.8f)));
    public static final RegistryObject<Block> LAUNCHER_RAIL = registerBlock("launcher_rail", () -> new LauncherRailBlock(AbstractBlock.Properties.of(Material.DECORATION).sound(SoundType.METAL).strength(0.7F).noCollission().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> SNAIL_SHELL_BLOCK = registerBlock("snail_shell_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.0f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SNAIL_SHELL_BRICKS = registerBlock("snail_shell_bricks", () -> new Block(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.2f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SNAIL_SHELL_BRICK_SLAB = registerBlock("snail_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));
    public static final RegistryObject<Block> SNAIL_SHELL_BRICK_VERTICAL_SLAB = registerBlock("snail_shell_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));
    public static final RegistryObject<Block> SNAIL_SHELL_BRICK_STAIRS = registerBlock("snail_shell_brick_stairs", () -> new StairsBlock(SNAIL_SHELL_BLOCK.get()::defaultBlockState, AbstractBlock.Properties.copy(SNAIL_SHELL_BLOCK.get())));
    public static final RegistryObject<Block> SNAIL_SHELL_BRICK_WALL = registerBlock("snail_shell_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BLOCK = registerBlock("bejeweled_snail_shell_block", () -> new Block(AbstractBlock.Properties.copy(SNAIL_SHELL_BLOCK.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BRICKS = registerBlock("bejeweled_snail_shell_bricks", () -> new Block(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BRICK_SLAB = registerBlock("bejeweled_snail_shell_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB = registerBlock("bejeweled_snail_shell_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BRICK_STAIRS = registerBlock("bejeweled_snail_shell_brick_stairs", () -> new StairsBlock(SNAIL_SHELL_BLOCK.get()::defaultBlockState, AbstractBlock.Properties.copy(SNAIL_SHELL_BLOCK.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BRICK_WALL = registerBlock("bejeweled_snail_shell_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(SNAIL_SHELL_BRICKS.get())));

    // Fluid Blocks
    public static final RegistryObject<FlowingFluidBlock> LIQUID_SULFUR_FLUID_BLOCK = registerBlock("liquid_sulfur_block", () -> new LiquidSulphurFluidBlock(MythFluids.SULFUR.getStill(), AbstractBlock.Properties.of(Material.WATER, MaterialColor.COLOR_YELLOW).noCollission().strength(100.0f).noDrops()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }
    
    private static AbstractBlock.Properties logProp(Material material, MaterialColor top, MaterialColor side) {
        return AbstractBlock.Properties.of(material, (state) -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side);
    }
    private static ToIntFunction<BlockState> lightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static void registerBlockData() {
        AxeItem.STRIPABLES = new HashMap<>(AxeItem.STRIPABLES);

        registerStrippable(WOLT_LOG, WOLT_LOG_STRIPPED);
        registerStrippable(WOLT_WOOD, WOLT_WOOD_STRIPPED);
        registerStrippable(WOLT_POST, WOLT_POST_STRIPPED);
        //registerStrippable(VIRIDIAN_STEM, VIRIDIAN_STEM_STRIPPED);
        //registerStrippable(VIRIDIAN_WOOD, VIRIDIAN_WOOD_STRIPPED;

        registerPottablePlant(CHARGED_DANDELION, POTTED_CHARGED_DANDELION);
        registerPottablePlant(WOLT_SAPLING, POTTED_WOLT_SAPLING);
    }

    private static void registerStrippable(Supplier<Block> strippable, Supplier<Block> stripped) {
        AxeItem.STRIPABLES.put(strippable.get(), stripped.get());
    }

    private static void registerPottablePlant(Supplier<Block> plant, Supplier<Block> pottedPlant) {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(plant.get().getRegistryName()), pottedPlant);
    }
}
