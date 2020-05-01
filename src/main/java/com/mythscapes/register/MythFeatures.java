package com.mythscapes.register;

import com.mythscapes.common.worldgen.feature.trees.ChargedTreeFeature;
import com.mythscapes.core.Mythscapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythFeatures {

    // Tree features
    public static final Feature<TreeFeatureConfig> CHARGED_TREE_FEATURE = new ChargedTreeFeature(TreeFeatureConfig::func_227338_a_);

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().registerAll(
                CHARGED_TREE_FEATURE.setRegistryName("")
        );
    }
}
