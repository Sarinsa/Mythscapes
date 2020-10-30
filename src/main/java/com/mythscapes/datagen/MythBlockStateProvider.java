package com.mythscapes.datagen;

import com.mythscapes.core.Mythscapes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythBlockStateProvider extends BlockStateProvider {


    public MythBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Mythscapes.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
