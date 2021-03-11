package com.radish.mythscapes.common.register.registry;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Made this so that the MythFluids registry class
 * won't be filled with both source and flowing registry objects.
 */
public class FluidDeferredRegister<T extends FlowingFluid> extends WrapperDeferredRegister<Fluid> {

    public FluidDeferredRegister() {
        super(ForgeRegistries.FLUIDS);
    }

    public FluidRegistryObject<T> register(String name, Supplier<T> still, Supplier<T> flowing) {
        try {
            RegistryObject<T> stillFluid = this.getInternal().register(name, still);
            RegistryObject<T> flowingFluid = this.getInternal().register("flowing_" + name, flowing);
            return new FluidRegistryObject<>(stillFluid, flowingFluid);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
