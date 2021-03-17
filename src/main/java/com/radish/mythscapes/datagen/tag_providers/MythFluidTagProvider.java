package com.radish.mythscapes.datagen.tag_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythFluids;
import com.radish.mythscapes.common.tags.MythFluidTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythFluidTagProvider extends FluidTagsProvider {

    public MythFluidTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(MythFluidTags.SULFUR).add(
                MythFluids.SULFUR.getStill().get(),
                MythFluids.SULFUR.getFlowing().get()
        );
        this.tag(MythFluidTags.CONDUCTIVE).add(
                Fluids.WATER,
                Fluids.FLOWING_WATER,
                MythFluids.SULFUR.getStill().get(),
                MythFluids.SULFUR.getFlowing().get()
        );
        this.tag(MythFluidTags.SWIMMABLE).addTag(
                FluidTags.WATER
        ).addTag(
                MythFluidTags.SULFUR
        );
    }
}
