package com.radish.mythscapes.common.register.registry;

import net.minecraft.fluid.FlowingFluid;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class FluidRegistryObject<T extends FlowingFluid> {

    private final RegistryObject<T> still;
    private final RegistryObject<T> flowing;

    protected FluidRegistryObject(RegistryObject<T> still, RegistryObject<T> flowing) {
        this.still = still;
        this.flowing = flowing;
    }

    public Supplier<T> getStill() {
        return still;
    }

    public Supplier<T> getFlowing() {
        return flowing;
    }
}
