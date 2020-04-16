package com.mythscapes.register;

import com.mythscapes.common.worldgen.feature.trees.ChargedTreeFeature;
import com.mythscapes.core.Mythscapes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, Mythscapes.MODID);

    // Tree features
    //public static final RegistryObject<ChargedTreeFeature> CHARGED_TREE_FEATURE = FEATURES.register("charged_tree_feature", ChargedTreeFeature::new);
}
