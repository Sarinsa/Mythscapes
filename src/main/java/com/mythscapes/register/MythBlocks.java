package com.mythscapes.register;

import com.mythscapes.common.blocks.BlisterBerryBushBlock;
import com.mythscapes.common.blocks.BlisterBerryBushTopBlock;
import com.mythscapes.common.blocks.DoublePlantBlock;
import com.mythscapes.common.blocks.wood.ModWoodButtonBlock;
import com.mythscapes.common.blocks.wood.ModWoodPressurePlateBlock;
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
    public static final RegistryObject<Block> CHARGED_PLANKS = BLOCKS.register("charged_wood_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_SLAB = BLOCKS.register("charged_wood_slab", () -> new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_STAIRS = BLOCKS.register("charged_wood_stairs", () -> new StairsBlock(() -> CHARGED_PLANKS.get().getDefaultState(), Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_FENCE = BLOCKS.register("charged_wood_fence", () -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_FENCE_GATE = BLOCKS.register("charged_wood_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_PRESSURE_PLATE = BLOCKS.register("charged_wood_pressure_plate", () -> new ModWoodPressurePlateBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));
    public static final RegistryObject<Block> CHARGED_WOOD_BUTTON = BLOCKS.register("charged_wood_button", () -> new ModWoodButtonBlock(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(2.0f, 3.0f)));

    // Plants
    public static final RegistryObject<Block> BLISTER_BERRY_BUSH = BLOCKS.register("blister_berry_bush", () -> new BlisterBerryBushBlock(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0f).tickRandomly().notSolid()));
    public static final RegistryObject<Block> BLISTER_BERRY_BUSH_TOP = BLOCKS.register("blister_berry_bush_top", () -> new BlisterBerryBushTopBlock(Block.Properties.create(Material.PLANTS, MaterialColor.BLACK).sound(SoundType.PLANT).hardnessAndResistance(0.0f).notSolid(), BLISTER_BERRY_BUSH.get()));
}
