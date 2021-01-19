package com.radish.mythscapes.common.register.registry;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/*
 * Oh man, you just got pranked!! Xd
 *
 * This is just a wrapper class that I created to
 * have the still and flowing fluid registry objects
 * chill together in a wrapper object to make
 * MythFluids look a little less hideous.
 * Did it work? A little bit, probably(...?).
 *
 * - Sarinsa
 */
public class FluidDeferredRegister<T extends FlowingFluid> {

    // Internal register
    private final DeferredRegister<Fluid> internal;

    public FluidDeferredRegister() {
        this.internal = DeferredRegister.create(ForgeRegistries.FLUIDS, Mythscapes.MODID);
    }

    public void register(IEventBus eventBus) {
        this.internal.register(eventBus);
    }

    public FluidRegistryObject<T> register(String name, Supplier<T> still, Supplier<T> flowing) {
        try {
            RegistryObject<T> stillFluid = this.internal.register(name, still);
            RegistryObject<T> flowingFluid = this.internal.register("flowing_" + name, flowing);
            return new FluidRegistryObject<>(stillFluid, flowingFluid);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DeferredRegister<Fluid> getInternal() {
        return this.internal;
    }
}
