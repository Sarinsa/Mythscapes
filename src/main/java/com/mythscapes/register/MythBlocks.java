package com.mythscapes.register;

import com.mythscapes.common.blocks.BlisterberryThistleBlock;
import com.mythscapes.common.blocks.BlisterberryThistleTopBlock;
import com.mythscapes.common.blocks.wood.ModSaplingBlock;
import com.mythscapes.common.blocks.wood.ModWoodButtonBlock;
import com.mythscapes.common.blocks.wood.ModWoodPressurePlateBlock;
import com.mythscapes.common.blocks.wood.tree.ChargedTree;
import com.mythscapes.core.Mythscapes;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Mythscapes.MODID);

    // Charged Wood
    public static final RegistryObject<Block> CHARGED_LEAVES = BLOCKS.register("charged_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.CYAN).sound(SoundType.PLANT).hardnessAndResistance(0.2F).tickRandomly().notSolid()));
    public static final RegistryObject<Block> CHARGED_LOG = BLOCKS.register("charged_log", () -> new LogBlock(MaterialColor.ICE, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD = BLOCKS.register("charged_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f)));
    public static final RegistryObject<Block> CHARGED_LOG_STRIPPED = BLOCKS.register("charged_log_stripped", () -> new LogBlock(MaterialColor.ICE, Block.Properties.from(CHARGED_LOG.get())));
    public static final RegistryObject<Block> CHARGED_WOOD_STRIPPED = BLOCKS.register("charged_wood_stripped", () -> new RotatedPillarBlock(Block.Properties.from(CHARGED_WOOD.get())));
    public static final RegistryObject<Block> CHARGED_PLANKS = BLOCKS.register("charged_wood_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_SLAB = BLOCKS.register("charged_wood_slab", () -> new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_STAIRS = BLOCKS.register("charged_wood_stairs", () -> new StairsBlock(() -> CHARGED_PLANKS.get().getDefaultState(), Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_FENCE = BLOCKS.register("charged_wood_fence", () -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_FENCE_GATE = BLOCKS.register("charged_wood_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_PRESSURE_PLATE = BLOCKS.register("charged_wood_pressure_plate", () -> new ModWoodPressurePlateBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> CHARGED_WOOD_BUTTON = BLOCKS.register("charged_wood_button", () -> new ModWoodButtonBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> CHARGED_SAPLING = BLOCKS.register("charged_sapling", () -> new ModSaplingBlock(new ChargedTree(), Block.Properties.create(Material.PLANTS, MaterialColor.CYAN).sound(SoundType.PLANT).hardnessAndResistance(0.0f).doesNotBlockMovement().tickRandomly()));

    // Giant Plant Thing
    public static final RegistryObject<Block> VIRIDIAN_STEM = BLOCKS.register("viridian_stem", () -> new LogBlock(MaterialColor.GREEN, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_WOOD = BLOCKS.register("viridian_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STEM_STRIPPED = BLOCKS.register("viridian_stem_stripped", () -> new LogBlock(MaterialColor.GREEN, Block.Properties.from(VIRIDIAN_STEM.get())));
    public static final RegistryObject<Block> VIRIDIAN_WOOD_STRIPPED = BLOCKS.register("viridian_wood_stripped", () -> new RotatedPillarBlock(Block.Properties.from(VIRIDIAN_WOOD.get())));
    public static final RegistryObject<Block> VIRIDIAN_PLANKS = BLOCKS.register("viridian_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_SLAB = BLOCKS.register("viridian_slab", () -> new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_STAIRS = BLOCKS.register("viridian_stairs", () -> new StairsBlock(() -> CHARGED_PLANKS.get().getDefaultState(), Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE = BLOCKS.register("viridian_fence", () -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_FENCE_GATE = BLOCKS.register("viridian_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(1.5f)));
    public static final RegistryObject<Block> VIRIDIAN_PRESSURE_PLATE = BLOCKS.register("viridian_pressure_plate", () -> new ModWoodPressurePlateBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));
    public static final RegistryObject<Block> VIRIDIAN_BUTTON = BLOCKS.register("viridian_button", () -> new ModWoodButtonBlock(Block.Properties.create(Material.WOOD, MaterialColor.GREEN).sound(SoundType.WOOD).hardnessAndResistance(0.5f)));

    // Plants
    public static final RegistryObject<Block> BLISTERBERRY_THISTLE = BLOCKS.register("blisterberry_thistle", () -> new BlisterberryThistleBlock(Block.Properties.create(Material.PLANTS, MaterialColor.BLACK_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).tickRandomly().notSolid(), false));
    public static final RegistryObject<Block> BLISTERBERRY_THISTLE_TOP = BLOCKS.register("blisterberry_thistle_top", () -> new BlisterberryThistleTopBlock(BLISTERBERRY_THISTLE.get(), Block.Properties.create(Material.PLANTS, MaterialColor.BLACK_TERRACOTTA).sound(SoundType.PLANT).hardnessAndResistance(0.0f).notSolid()));
}
