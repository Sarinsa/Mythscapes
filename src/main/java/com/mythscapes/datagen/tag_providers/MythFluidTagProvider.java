package com.mythscapes.datagen.tag_providers;

import com.mythscapes.common.tags.MythFluidTags;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluids;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythFluidTagProvider extends FluidTagsProvider {

    public MythFluidTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Mythscapes.MODID, existingFileHelper);
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
