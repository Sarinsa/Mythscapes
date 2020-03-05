package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Mythscapes.MODID);


    public static final RegistryObject<Block> CHARGED_LOG = BLOCKS.register("charged_log", () -> new LogBlock(MaterialColor.ICE, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.0f)));
    public static final RegistryObject<Block> CHARGED_PLANKS = BLOCKS.register("charged_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.ICE).sound(SoundType.WOOD).hardnessAndResistance(1.0f)));
}
