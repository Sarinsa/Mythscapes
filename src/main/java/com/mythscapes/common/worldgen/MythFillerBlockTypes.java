package com.mythscapes.common.worldgen;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythBlocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythFillerBlockTypes {

    //
    // This is for creating filler blocks for ore generation
    //
    
    public static OreFeatureConfig.FillerBlockType GALVITE;

    // This is probably not the smoothest way to do this, but it works for now
    // Items register after blocks, so this is a pretty alright time to init.
    @SubscribeEvent
    public static void initBlockFillerTypes(RegistryEvent.Register<Item> event) {
        GALVITE = OreFeatureConfig.FillerBlockType.create("galvite", "", new BlockMatcher(MythBlocks.GALVITE.get()));
    }
}
