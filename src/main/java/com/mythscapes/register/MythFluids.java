package com.mythscapes.register;

import com.mythscapes.common.fluids.LiquidSulphurFluid;
import com.mythscapes.core.Mythscapes;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Mythscapes.MODID);

    public static final RegistryObject<FlowingFluid> SULFUR = FLUIDS.register("liquid_sulfur", () -> new LiquidSulphurFluid.Source(MythFluids.SULFUR_PROP));
    public static final RegistryObject<FlowingFluid> FLOWING_SULFUR = FLUIDS.register("flowing_liquid_sulfur", () -> new LiquidSulphurFluid.Flowing(MythFluids.SULFUR_PROP));


    // Sulfur
    public static final ForgeFlowingFluid.Properties SULFUR_PROP = new ForgeFlowingFluid.Properties(SULFUR, FLOWING_SULFUR,
            FluidAttributes.builder(texture("fluid/liquid_sulfur_still"), texture("fluid/liquid_sulfur_flowing"))
                    .viscosity(500)
                    .density(500)
                    .rarity(Rarity.COMMON)
                    .overlay(texture("fluid/liquid_sulfur_still")))
            .block(MythBlocks.LIQUID_SULFUR_FLUID_BLOCK)
            .bucket(MythItems.LIQUID_SULFUR_BUCKET);

    private static ResourceLocation texture(String path) {
        return Mythscapes.resourceLoc(path);
    }
}
