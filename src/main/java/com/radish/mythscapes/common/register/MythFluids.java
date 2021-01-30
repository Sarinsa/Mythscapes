package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.fluids.BaseFluid;
import com.radish.mythscapes.common.fluids.LiquidSulfurFluid;
import com.radish.mythscapes.common.register.registry.FluidDeferredRegister;
import com.radish.mythscapes.common.register.registry.FluidRegistryObject;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class MythFluids {

    public static final FluidDeferredRegister<FlowingFluid> FLUIDS = new FluidDeferredRegister<>();

    public static final FluidRegistryObject<FlowingFluid> SULFUR = FLUIDS.register("liquid_sulfur", LiquidSulfurFluid.Source::new, LiquidSulfurFluid.Flowing::new);


    public static class Properties {
        public static final ForgeFlowingFluid.Properties PROPERTIES_SULFUR = new ForgeFlowingFluid.Properties(SULFUR.getStill(), SULFUR.getFlowing(), FluidAttributes
                        .builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"))
                        .color(0xDCBF2E)
                        .rarity(Rarity.COMMON))
                .block(MythBlocks.LIQUID_SULFUR_FLUID_BLOCK)
                .bucket(MythItems.LIQUID_SULFUR_BUCKET);
    }

    private static ResourceLocation texture(String path) {
        return Mythscapes.resourceLoc("fluid/" + path);
    }
}
