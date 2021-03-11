package com.radish.mythscapes.common.register.registry;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class WrapperDeferredRegister<T extends IForgeRegistryEntry<T>> {

    private final DeferredRegister<T> internal;

    public WrapperDeferredRegister(IForgeRegistry<T> forgeRegistry) {
        this.internal = DeferredRegister.create(forgeRegistry, Mythscapes.MODID);
    }

    public final void register(IEventBus eventBus) {
        this.internal.register(eventBus);
    }

    public final DeferredRegister<T> getInternal() {
        return this.internal;
    }
}
