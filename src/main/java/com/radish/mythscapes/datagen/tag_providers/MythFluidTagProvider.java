package com.radish.mythscapes.datagen.tag_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythFluids;
import com.radish.mythscapes.common.tags.MythFluidTags;
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
        this.getOrCreateBuilder(MythFluidTags.SULFUR).add(
                MythFluids.SULFUR.get(),
                MythFluids.FLOWING_SULFUR.get()
        );
        this.getOrCreateBuilder(MythFluidTags.CONDUCTIVE).add(
                Fluids.WATER,
                Fluids.FLOWING_WATER,
                MythFluids.SULFUR.get(),
                MythFluids.FLOWING_SULFUR.get()
        );
    }
}
