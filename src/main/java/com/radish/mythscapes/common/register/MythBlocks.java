package com.radish.mythscapes.common.register;

import com.google.common.collect.Maps;
import com.radish.mythscapes.common.blocks.LauncherRailBlock;
import com.radish.mythscapes.common.blocks.StaticCottonBlock;
import com.radish.mythscapes.common.blocks.StaticCottonPilesBlock;
import com.radish.mythscapes.common.blocks.WoltPowderBlock;
import com.radish.mythscapes.common.blocks.fluid.LiquidSulphurFluidBlock;
import com.radish.mythscapes.common.blocks.plant.BlisterberryThistleBlock;
import com.radish.mythscapes.common.blocks.plant.ChargedDandelionBlock;
import com.radish.mythscapes.common.blocks.plant.ModLeavesBlock;
import com.radish.mythscapes.common.blocks.wood.*;
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

import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class MythBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Mythscapes.MODID);

    //
    // Ores
    //
    public static final RegistryObject<Block> GILDED_GALVITE = BLOCKS.register("gilded_galvite", () -> new OreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(3.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
    public static final RegistryObject<Block> BEJEWELED_GALVITE = BLOCKS.register("bejeweled_galvite", () -> new OreBlock(AbstractBlock.Properties.from(GILDED_GALVITE.get())));
    public static final RegistryObject<Block> POWERED_GALVITE = BLOCKS.register("powered_galvite", () -> new RedstoneOreBlock(AbstractBlock.Properties.from(GILDED_GALVITE.get()).tickRandomly().setLightLevel(lightValueLit(9))));

    //
    // Wolt Wood
    //
    public static final RegistryObject<Block> WOLT_LOG = BLOCKS.register("wolt_log", () -> new ModLogBlock(logProp(Material.WOOD, MaterialColor.ICE, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_WOOD = BLOCKS.register("wolt_wood", () -> new ModLogBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f)));
    public static final RegistryObject<Block> WOLT_LOG_STRIPPED = BLOCKS.register("wolt_log_stripped", () -> new ModLogBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_WOOD_STRIPPED = BLOCKS.register("wolt_wood_stripped", () -> new ModLogBlock(AbstractBlock.Properties.from(WOLT_WOOD.get())));
    public static final RegistryObject<Block> WOLT_PLANKS = BLOCKS.register("wolt_planks", () -> new ModPlanksBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_SLAB = BLOCKS.register("wolt_slab", () -> new ModWoodSlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_STAIRS = BLOCKS.register("wolt_stairs", () -> new ModWoodStairsBlock(WOLT_PLANKS.get()::getDefaultState, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_FENCE = BLOCKS.register("wolt_fence", () -> new ModFenceBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_FENCE_GATE = BLOCKS.register("wolt_fence_gate", () -> new ModFenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> WOLT_PRESSURE_PLATE = BLOCKS.register("wolt_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> WOLT_BUTTON = BLOCKS.register("wolt_button", () -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> WOLT_DOOR = BLOCKS.register("wolt_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(3.0f).notSolid()));
    public static final RegistryObject<Block> WOLT_TRAPDOOR = BLOCKS.register("wolt_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(3.0f).notSolid()));
    public static final RegistryObject<Block> WOLT_SAPLING = BLOCKS.register("wolt_sapling", () -> new ModSaplingBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.CYAN).sound(SoundType.PLANT).hardnessAndResistance(0.0f).doesNotBlockMovement().tickRandomly()));

    //
    // Viridian Wood
    //

    /*
    public static final RegistryObject<Block> VIRIDIAN_STEM = BLOCKS.register("viridian_stem", () -> new RotatedPillarBlock(logProp(Material.WOOD, MaterialColor.WHITE_TERRACOTTA, MaterialColor.LIME).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_WOOD = BLOCKS.register("viridian_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STEM_STRIPPED = BLOCKS.register("viridian_stem_stripped", () -> new RotatedPillarBlock(logProp(Material.WOOD, MaterialColor.WHITE_TERRACOTTA, MaterialColor.LIME)));
    public static final RegistryObject<Block> VIRIDIAN_WOOD_STRIPPED = BLOCKS.register("viridian_wood_stripped", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(VIRIDIAN_WOOD.get())));
    public static final RegistryObject<Block> VIRIDIAN_PLANKS = BLOCKS.register("viridian_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_SLAB = BLOCKS.register("viridian_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STAIRS = BLOCKS.register("viridian_stairs", () -> new StairsBlock(WOLT_PLANKS.get()::getDefaultState, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE = BLOCKS.register("viridian_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE_GATE = BLOCKS.register("viridian_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_PRESSURE_PLATE = BLOCKS.register("viridian_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> VIRIDIAN_BUTTON = BLOCKS.register("viridian_button", () -> new WoodButtonBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    */

    //
    // Leaves
    //
    public static final RegistryObject<Block> WOLT_LEAVES = BLOCKS.register("wolt_leaves", () -> new ModLeavesBlock(AbstractBlock.Properties.create(Material.LEAVES, MaterialColor.CYAN).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly().notSolid()));

    //
    // Galvite
    //
    public static final RegistryObject<Block> GALVITE = BLOCKS.register("galvite", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).sound(SoundType.STONE).hardnessAndResistance(1.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Block> GALVITE_SLAB = BLOCKS.register("galvite_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_STAIRS = BLOCKS.register("galvite_stairs", () -> new StairsBlock(GALVITE.get()::getDefaultState, AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_WALL = BLOCKS.register("galvite_wall", () -> new WallBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE = BLOCKS.register("polished_galvite", () -> new Block(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_SLAB = BLOCKS.register("polished_galvite_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_STAIRS = BLOCKS.register("polished_galvite_stairs", () -> new StairsBlock(POLISHED_GALVITE.get()::getDefaultState, AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_WALL = BLOCKS.register("polished_galvite_wall", () -> new WallBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_PRESSURE_PLATE = BLOCKS.register("polished_galvite_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BUTTON = BLOCKS.register("polished_galvite_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICKS = BLOCKS.register("polished_galvite_bricks", () -> new Block(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_SLAB = BLOCKS.register("polished_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_STAIRS = BLOCKS.register("polished_galvite_brick_stairs", () -> new StairsBlock(POLISHED_GALVITE_BRICKS.get()::getDefaultState, AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> POLISHED_GALVITE_BRICK_WALL = BLOCKS.register("polished_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> CHISELED_POLISHED_GALVITE = BLOCKS.register("chiseled_polished_galvite", () -> new Block(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLES = BLOCKS.register("galvite_shingles", () -> new Block(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_SLAB = BLOCKS.register("galvite_shingle_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GALVITE_SHINGLE_STAIRS = BLOCKS.register("galvite_shingle_stairs", () -> new StairsBlock(GALVITE.get()::getDefaultState, AbstractBlock.Properties.from(GALVITE.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICKS = BLOCKS.register("gilded_galvite_bricks", () -> new Block(AbstractBlock.Properties.from(GALVITE.get()).hardnessAndResistance(1.4f)));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_SLAB = BLOCKS.register("gilded_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_STAIRS = BLOCKS.register("gilded_galvite_brick_stairs", () -> new StairsBlock(GILDED_GALVITE.get()::getDefaultState, AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> GILDED_GALVITE_BRICK_WALL = BLOCKS.register("gilded_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICKS = BLOCKS.register("bejeweled_galvite_bricks", () -> new Block(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_SLAB = BLOCKS.register("bejeweled_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_STAIRS = BLOCKS.register("bejeweled_galvite_brick_stairs", () -> new StairsBlock(BEJEWELED_GALVITE.get()::getDefaultState, AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> BEJEWELED_GALVITE_BRICK_WALL = BLOCKS.register("bejeweled_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICKS = BLOCKS.register("powered_galvite_bricks", () -> new Block(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_SLAB = BLOCKS.register("powered_galvite_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_STAIRS = BLOCKS.register("powered_galvite_brick_stairs", () -> new StairsBlock(POWERED_GALVITE.get()::getDefaultState, AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));
    public static final RegistryObject<Block> POWERED_GALVITE_BRICK_WALL = BLOCKS.register("powered_galvite_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(GILDED_GALVITE_BRICKS.get())));

    //
    // Trollstone
    //
    public static final RegistryObject<Block> TROLLSTONE = BLOCKS.register("trollstone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.LIGHT_GRAY).sound(SoundType.STONE).hardnessAndResistance(2.0F, 6.0F).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Block> TROLLSTONE_SLAB = BLOCKS.register("trollstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> TROLLSTONE_STAIRS = BLOCKS.register("trollstone_stairs", () -> new StairsBlock(TROLLSTONE.get()::getDefaultState, AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> TROLLSTONE_WALL = BLOCKS.register("trollstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE = BLOCKS.register("polished_trollstone", () -> new Block(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_SLAB = BLOCKS.register("polished_trollstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_STAIRS = BLOCKS.register("polished_trollstone_stairs", () -> new StairsBlock(POLISHED_TROLLSTONE.get()::getDefaultState, AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_WALL = BLOCKS.register("polished_trollstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_PRESSURE_PLATE = BLOCKS.register("polished_trollstone_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BUTTON = BLOCKS.register("polished_trollstone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICKS = BLOCKS.register("polished_trollstone_bricks", () -> new Block(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_SLAB = BLOCKS.register("polished_trollstone_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_STAIRS = BLOCKS.register("polished_trollstone_brick_stairs", () -> new StairsBlock(POLISHED_TROLLSTONE_BRICKS.get()::getDefaultState, AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_BRICK_WALL = BLOCKS.register("polished_trollstone_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> POLISHED_TROLLSTONE_PILLAR = BLOCKS.register("polished_trollstone_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(TROLLSTONE.get())));
    public static final RegistryObject<Block> CHISELED_POLISHED_TROLLSTONE = BLOCKS.register("chiseled_polished_trollstone", () -> new Block(AbstractBlock.Properties.from(TROLLSTONE.get())));

    //
    // Plants
    //
    public static final RegistryObject<Block> BLISTERBERRY_THISTLE = BLOCKS.register("blisterberry_thistle", () -> new BlisterberryThistleBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BLACK_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).tickRandomly().notSolid().doesNotBlockMovement().setLightLevel((state) -> state.get(BlockStateProperties.AGE_0_5) == 5 ? 5 : 0)));
    public static final RegistryObject<Block> CHARGED_DANDELION = BLOCKS.register("charged_dandelion", () -> new ChargedDandelionBlock(MythEffects.STATIC, 17, AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).notSolid().doesNotBlockMovement().tickRandomly()));
    public static final RegistryObject<Block> POTTED_CHARGED_DANDELION = BLOCKS.register("potted_charged_dandelion", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHARGED_DANDELION, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final RegistryObject<Block> POTTED_WOLT_SAPLING = BLOCKS.register("potted_wolt_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, WOLT_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));

    //
    // Misc
    //
    public static final RegistryObject<Block> WOLT_POWDER_BLOCK = BLOCKS.register("wolt_powder_block", () -> new WoltPowderBlock(false, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GREEN).sound(SoundType.STONE).hardnessAndResistance(0.5f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> GOLDEN_WOLT_POWDER_BLOCK = BLOCKS.register("golden_wolt_powder_block", () -> new WoltPowderBlock(true, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.YELLOW).sound(SoundType.STONE).hardnessAndResistance(0.5f).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> BIOBULB_CLUSTER = BLOCKS.register("biobulb_cluster", () -> new Block(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.CYAN).sound(SoundType.GLASS).hardnessAndResistance(0.3f).setLightLevel((state) -> 13).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> ROASTED_BIOBULB_CLUSTER = BLOCKS.register("roasted_biobulb_cluster", () -> new Block(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.GREEN).sound(SoundType.GLASS).hardnessAndResistance(0.3f).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> BIOBULB_LAMP = BLOCKS.register("biobulb_lamp", () -> new RedstoneLampBlock(AbstractBlock.Properties.create(Material.REDSTONE_LIGHT, MaterialColor.GREEN).sound(SoundType.GLASS).hardnessAndResistance(0.3F).setLightLevel(lightValueLit(15))));
    public static final RegistryObject<Block> STATIC_COTTON_BLOCK = BLOCKS.register("static_cotton_block", () -> new StaticCottonBlock(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.CLOTH).hardnessAndResistance(0.8f)));
    public static final RegistryObject<Block> STATIC_COTTON_PILES = BLOCKS.register("static_cotton_piles", () -> new StaticCottonPilesBlock(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.CLOTH).hardnessAndResistance(0.8f)));
    public static final RegistryObject<Block> LAUNCHER_RAIL = BLOCKS.register("launcher_rail", () -> new LauncherRailBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).sound(SoundType.METAL).hardnessAndResistance(0.7F).doesNotBlockMovement().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> SNAIL_SHELL_BLOCK = BLOCKS.register("snail_shell_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.0f).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Block> SNAIL_SHELL_BRICKS = BLOCKS.register("snail_shell_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.2f).harvestTool(ToolType.PICKAXE).setRequiresTool()));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BLOCK = BLOCKS.register("bejeweled_snail_shell_block", () -> new Block(AbstractBlock.Properties.from(SNAIL_SHELL_BLOCK.get())));
    public static final RegistryObject<Block> BEJEWELED_SNAIL_SHELL_BRICKS = BLOCKS.register("bejeweled_snail_shell_bricks", () -> new Block(AbstractBlock.Properties.from(SNAIL_SHELL_BRICKS.get())));

    //
    // Fluid Blocks
    //
    public static final RegistryObject<FlowingFluidBlock> LIQUID_SULFUR_FLUID_BLOCK = BLOCKS.register("liquid_sulfur_block", () -> new LiquidSulphurFluidBlock(MythFluids.SULFUR, AbstractBlock.Properties.create(Material.WATER, MaterialColor.YELLOW).doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops()));

    private static AbstractBlock.Properties logProp(Material material, MaterialColor top, MaterialColor side) {
        return AbstractBlock.Properties.create(material, (state) -> state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? top : side);
    }
    private static ToIntFunction<BlockState> lightValueLit(int lightValue) {
        return (state) -> state.get(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static void registerBlockInfo() {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);

        registerStrippable(WOLT_LOG, WOLT_LOG_STRIPPED);
        registerStrippable(WOLT_WOOD, WOLT_WOOD_STRIPPED);
        //registerStrippable(VIRIDIAN_STEM, VIRIDIAN_STEM_STRIPPED);
        //registerStrippable(VIRIDIAN_WOOD, VIRIDIAN_WOOD_STRIPPED;

        registerPottablePlant(CHARGED_DANDELION, POTTED_CHARGED_DANDELION);
        registerPottablePlant(WOLT_SAPLING, POTTED_WOLT_SAPLING);
    }

    private static void registerStrippable(Supplier<Block> strippable, Supplier<Block> stripped) {
        AxeItem.BLOCK_STRIPPING_MAP.put(strippable.get(), stripped.get());
    }

    private static void registerPottablePlant(Supplier<Block> plant, Supplier<Block> pottedPlant) {
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(Objects.requireNonNull(plant.get().getRegistryName()), pottedPlant);
    }
}
