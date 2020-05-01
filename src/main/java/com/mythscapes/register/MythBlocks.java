package com.mythscapes.register;

import com.google.common.collect.Maps;
import com.mythscapes.common.blocks.LauncherRailBlock;
import com.mythscapes.common.blocks.StaticCottonBlock;
import com.mythscapes.common.blocks.StaticCottonLayersBlock;
import com.mythscapes.common.blocks.WoltPowderBlock;
import com.mythscapes.common.blocks.fluid.LiquidSulphurFluidBlock;
import com.mythscapes.common.blocks.plant.BlisterberryThistleBlock;
import com.mythscapes.common.blocks.plant.BlisterberryThistleTopBlock;
import com.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.mythscapes.common.blocks.wood.MythSaplingBlock;
import com.mythscapes.common.blocks.wood.tree.ChargedTree;
import com.mythscapes.core.Mythscapes;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

public class MythBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Mythscapes.MODID);

    //
    // Ores
    //
    public static final RegistryObject<Block> GILDED_GALVITE = BLOCKS.register("gilded_galvite", () -> new OreBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(3.0f)));
    public static final RegistryObject<Block> BEJEWELED_GALVITE = BLOCKS.register("bejeweled_galvite", () -> new OreBlock(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE = BLOCKS.register("powered_galvite", () -> new RedstoneOreBlock(Block.Properties.from(GILDED_GALVITE.get()).tickRandomly().lightValue(9)));

    //
    // Wolt Wood
    //
    public static final RegistryObject<Block> WOLT_LOG = BLOCKS.register("wolt_log", () -> new LogBlock(MaterialColor.ICE, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_WOOD = BLOCKS.register("wolt_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f)));
    public static final RegistryObject<Block> WOLT_LOG_STRIPPED = BLOCKS.register("wolt_log_stripped", () -> new LogBlock(MaterialColor.ICE, Block.Properties.from(WOLT_LOG.get())));
    public static final RegistryObject<Block> WOLT_WOOD_STRIPPED = BLOCKS.register("wolt_wood_stripped", () -> new RotatedPillarBlock(Block.Properties.from(WOLT_WOOD.get())));
    public static final RegistryObject<Block> WOLT_PLANKS = BLOCKS.register("wolt_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_SLAB = BLOCKS.register("wolt_slab", () -> new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_STAIRS = BLOCKS.register("wolt_stairs", () -> new StairsBlock(WOLT_PLANKS.get()::getDefaultState, Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_FENCE = BLOCKS.register("wolt_fence", () -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_FENCE_GATE = BLOCKS.register("wolt_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_PRESSURE_PLATE = BLOCKS.register("wolt_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> WOLT_BUTTON = BLOCKS.register("wolt_button", () -> new WoodButtonBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> WOLT_DOOR = BLOCKS.register("wolt_door", () -> new DoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(3.0f).notSolid()));
    public static final RegistryObject<Block> WOLT_TRAPDOOR = BLOCKS.register("wolt_trapdoor", () -> new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(3.0f).notSolid()));
    public static final RegistryObject<Block> WOLT_SAPLING = BLOCKS.register("wolt_sapling", () -> new MythSaplingBlock(new ChargedTree(), Block.Properties.create(Material.PLANTS, MaterialColor.CYAN).sound(SoundType.PLANT).hardnessAndResistance(0.0f).doesNotBlockMovement().tickRandomly()));

    //
    // Viridian Wood
    //
    public static final RegistryObject<Block> VIRIDIAN_STEM = BLOCKS.register("viridian_stem", () -> new LogBlock(MaterialColor.GREEN, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_WOOD = BLOCKS.register("viridian_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STEM_STRIPPED = BLOCKS.register("viridian_stem_stripped", () -> new LogBlock(MaterialColor.GREEN, Block.Properties.from(VIRIDIAN_STEM.get())));
    public static final RegistryObject<Block> VIRIDIAN_WOOD_STRIPPED = BLOCKS.register("viridian_wood_stripped", () -> new RotatedPillarBlock(Block.Properties.from(VIRIDIAN_WOOD.get())));
    public static final RegistryObject<Block> VIRIDIAN_PLANKS = BLOCKS.register("viridian_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_SLAB = BLOCKS.register("viridian_slab", () -> new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STAIRS = BLOCKS.register("viridian_stairs", () -> new StairsBlock(WOLT_PLANKS.get()::getDefaultState, Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE = BLOCKS.register("viridian_fence", () -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE_GATE = BLOCKS.register("viridian_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_PRESSURE_PLATE = BLOCKS.register("viridian_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> VIRIDIAN_BUTTON = BLOCKS.register("viridian_button", () -> new WoodButtonBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));

    //
    // Leaves
    //
    public static final RegistryObject<Block> WOLT_LEAVES = BLOCKS.register("wolt_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.CYAN).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly().notSolid()));

    //
    // Galvite
    //
    public static final RegistryObject<Block> GALVITE = BLOCKS.register("galvite", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.0f)));
    public static final RegistryObject<Block> GALVITE_SLAB = BLOCKS.register("galvite_slab", () -> new SlabBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_STAIRS = BLOCKS.register("galvite_stairs", () -> new StairsBlock(GALVITE.get()::getDefaultState, Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_WALL = BLOCKS.register("galvite_wall", () -> new WallBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE = BLOCKS.register("polished_galvite", () -> new Block(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_SLAB = BLOCKS.register("polished_galvite_slab", () -> new SlabBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_STAIRS = BLOCKS.register("polished_galvite_stairs", () -> new StairsBlock(POLISHED_GALVITE.get()::getDefaultState, Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_WALL = BLOCKS.register("polished_galvite_wall", () -> new WallBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_PRESSURE_PLATE = BLOCKS.register("polished_galvite_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BUTTON = BLOCKS.register("polished_galvite_button", () -> new StoneButtonBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICKS = BLOCKS.register("polished_galvite_bricks", () -> new Block(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_SLAB = BLOCKS.register("polished_galvite_brick_slab", () -> new SlabBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_STAIRS = BLOCKS.register("polished_galvite_brick_stairs", () -> new StairsBlock(POLISHED_GALVITE_BRICKS.get()::getDefaultState, Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_WALL = BLOCKS.register("polished_galvite_brick_wall", () -> new WallBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> CHISELED_POLISHED_GALVITE = BLOCKS.register("chiseled_polished_galvite", () -> new Block(Block.Properties.from(POLISHED_GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLES = BLOCKS.register("galvite_shingles", () -> new Block(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_SLAB = BLOCKS.register("galvite_shingle_slab", () -> new SlabBlock(Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_STAIRS = BLOCKS.register("galvite_shingle_stairs", () -> new StairsBlock(GALVITE.get()::getDefaultState, Block.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICKS = BLOCKS.register("gilded_galvite_bricks", () -> new Block(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_SLAB = BLOCKS.register("gilded_galvite_brick_slab", () -> new SlabBlock(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_STAIRS = BLOCKS.register("gilded_galvite_brick_stairs", () -> new StairsBlock(GILDED_GALVITE.get()::getDefaultState, Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_WALL = BLOCKS.register("gilded_galvite_brick_wall", () -> new WallBlock(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICKS = BLOCKS.register("bejeweled_galvite_bricks", () -> new Block(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_SLAB = BLOCKS.register("bejeweled_galvite_brick_slab", () -> new SlabBlock(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_STAIRS = BLOCKS.register("bejeweled_galvite_brick_stairs", () -> new StairsBlock(BEJEWELED_GALVITE.get()::getDefaultState, Block.Properties.from(BEJEWELED_GALVITE.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_WALL = BLOCKS.register("bejeweled_galvite_brick_wall", () -> new WallBlock(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICKS = BLOCKS.register("powered_galvite_bricks", () -> new Block(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_SLAB = BLOCKS.register("powered_galvite_brick_slab", () -> new SlabBlock(Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_STAIRS = BLOCKS.register("powered_galvite_brick_stairs", () -> new StairsBlock(POWERED_GALVITE.get()::getDefaultState, Block.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_WALL = BLOCKS.register("powered_galvite_brick_wall", () -> new WallBlock(Block.Properties.from(GILDED_GALVITE.get())));

    //
    // Plants
    //
    public static final RegistryObject<Block> BLISTERBERRY_THISTLE = BLOCKS.register("blisterberry_thistle", () -> new BlisterberryThistleBlock(2, Block.Properties.create(Material.PLANTS, MaterialColor.BLACK_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).tickRandomly().notSolid().doesNotBlockMovement()));
    public static final RegistryObject<Block> BLISTERBERRY_THISTLE_TOP = BLOCKS.register("blisterberry_thistle_top", () -> new BlisterberryThistleTopBlock(Block.Properties.create(Material.PLANTS, MaterialColor.BLACK_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).notSolid().doesNotBlockMovement()));
    public static final RegistryObject<Block> CHARGED_DANDELION = BLOCKS.register("charged_dandelion", () -> new ChargedDandelionBlock(MythEffects.STATIC, 17, Block.Properties.create(Material.PLANTS, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).notSolid().doesNotBlockMovement().tickRandomly()));
    public static final RegistryObject<Block> POTTED_CHARGED_DANDELION = BLOCKS.register("potted_charged_dandelion", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHARGED_DANDELION, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final RegistryObject<Block> POTTED_WOLT_SAPLING = BLOCKS.register("potted_wolt_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WOLT_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));

    //
    // Misc
    //
    public static final RegistryObject<Block> WOLT_POWDER_BLOCK = BLOCKS.register("wolt_powder_block", () -> new WoltPowderBlock(false, Block.Properties.create(Material.ROCK, MaterialColor.GREEN).sound(SoundType.STONE).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> GOLDEN_WOLT_POWDER_BLOCK = BLOCKS.register("golden_wolt_powder_block", () -> new WoltPowderBlock(true, Block.Properties.create(Material.ROCK, MaterialColor.YELLOW).sound(SoundType.STONE).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> BIOBULB_CLUSTER = BLOCKS.register("biobulb_cluster", () -> new Block(Block.Properties.create(Material.GLASS, MaterialColor.CYAN).sound(SoundType.GLASS).hardnessAndResistance(0.3f).lightValue(15).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> ROASTED_BIOBULB_CLUSTER = BLOCKS.register("roasted_biobulb_cluster", () -> new Block(Block.Properties.create(Material.GLASS, MaterialColor.GREEN).sound(SoundType.GLASS).hardnessAndResistance(0.3f).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> BIOBULB_LAMP = BLOCKS.register("biobulb_lamp", () -> new RedstoneLampBlock(Block.Properties.create(Material.REDSTONE_LIGHT, MaterialColor.GREEN).sound(SoundType.GLASS).hardnessAndResistance(0.3F).lightValue(15)));
    public static final RegistryObject<Block> STATIC_COTTON_BLOCK = BLOCKS.register("static_cotton_block", () -> new StaticCottonBlock(Block.Properties.create(Material.WOOL, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.CLOTH).hardnessAndResistance(0.8f)));
    public static final RegistryObject<Block> STATIC_COTTON_LAYERS = BLOCKS.register("static_cotton_layers", () -> new StaticCottonLayersBlock(Block.Properties.create(Material.WOOL, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.CLOTH).hardnessAndResistance(0.8f)));
    public static final RegistryObject<Block> LAUNCHER_RAIL = BLOCKS.register("launcher_rail", () -> new LauncherRailBlock(Block.Properties.create(Material.MISCELLANEOUS).sound(SoundType.METAL).hardnessAndResistance(0.7F).doesNotBlockMovement()));
    public static final RegistryObject<Block> SNAIL_SHELL_BLOCK = BLOCKS.register("snail_shell_block", () -> new Block(Block.Properties.create(Material.SAND).sound(SoundType.GROUND).hardnessAndResistance(1.0f)));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BLOCK = BLOCKS.register("bejeweled_snail_shell_block", () -> new Block(Block.Properties.from(SNAIL_SHELL_BLOCK.get())));

    //
    // Fluid Blocks
    //
    public static final RegistryObject<FlowingFluidBlock> LIQUID_SULPHUR_FLUID_BLOCK = BLOCKS.register("liquid_sulphur_block", () -> new LiquidSulphurFluidBlock(MythFluids.SULFUR, Block.Properties.create(Material.WATER, MaterialColor.YELLOW).doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()));



    public static void registerBlockInfo() {
        // Assign new mutable map
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);

        registerStrippable(WOLT_LOG, WOLT_LOG_STRIPPED);
        registerStrippable(WOLT_WOOD, WOLT_WOOD_STRIPPED);
        registerStrippable(VIRIDIAN_STEM, VIRIDIAN_STEM_STRIPPED);
        registerStrippable(VIRIDIAN_WOOD, VIRIDIAN_WOOD_STRIPPED);

        registerPottablePlant(CHARGED_DANDELION, POTTED_CHARGED_DANDELION);
        registerPottablePlant(WOLT_SAPLING, POTTED_WOLT_SAPLING);

        // Logs & stripped
        registerFlammable(WOLT_LOG, 5, 5);
        registerFlammable(WOLT_LOG_STRIPPED, 5, 5);
        registerFlammable(VIRIDIAN_STEM, 5, 5);
        registerFlammable(VIRIDIAN_STEM_STRIPPED, 5, 5);
        registerFlammable(WOLT_WOOD, 5, 5);
        registerFlammable(WOLT_WOOD_STRIPPED, 5, 5);
        registerFlammable(VIRIDIAN_WOOD, 5, 5);
        registerFlammable(VIRIDIAN_WOOD_STRIPPED, 5, 5);
        // Wood
        registerFlammable(WOLT_PLANKS, 5, 20);
        registerFlammable(VIRIDIAN_PLANKS, 5, 20);
        registerFlammable(WOLT_SLAB, 5, 20);
        registerFlammable(VIRIDIAN_SLAB, 5, 20);
        registerFlammable(WOLT_FENCE, 5, 20);
        registerFlammable(VIRIDIAN_FENCE, 5, 20);
        registerFlammable(WOLT_FENCE_GATE, 5, 20);
        registerFlammable(VIRIDIAN_FENCE_GATE, 5, 20);
        registerFlammable(WOLT_STAIRS, 5, 20);
        registerFlammable(VIRIDIAN_STAIRS, 5, 20);
        // Leaves
        registerFlammable(WOLT_LEAVES, 30, 60);
        // Plants
        registerFlammable(CHARGED_DANDELION, 60, 100);
        // Other
        registerFlammable(STATIC_COTTON_BLOCK, 30, 60);
        registerFlammable(STATIC_COTTON_LAYERS, 30, 60);
    }

    private static void registerStrippable(Supplier<Block> strippable, Supplier<Block> stripped) {
        AxeItem.BLOCK_STRIPPING_MAP.put(strippable.get(), stripped.get());
    }

    private static void registerFlammable(Supplier<Block> blockSupplier, int encouragement, int flammability) {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFireInfo(blockSupplier.get(), encouragement, flammability);
    }

    private static void registerPottablePlant(Supplier<Block> plant, Supplier<Block> pottedPlant) {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(plant.get().getRegistryName()), pottedPlant);
    }
}
