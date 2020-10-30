package com.mythscapes.datagen.tag_providers;

import com.mythscapes.common.tags.MythFluidTags;
import com.mythscapes.register.MythFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluids;

public class MythFluidTagProvider extends FluidTagsProvider {

    public MythFluidTagProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(MythFluidTags.CONDUCTIVE).add(
                Fluids.WATER,
                Fluids.FLOWING_WATER,
                MythFluids.SULFUR.get(),
                MythFluids.FLOWING_SULFUR.get()
        );
        this.getOrCreateBuilder(MythFluidTags.SULFUR).add(
                MythFluids.SULFUR.get(),
                MythFluids.FLOWING_SULFUR.get()
        );
    }
}
