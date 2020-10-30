package com.mythscapes.datagen;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.datagen.lang_providers.MythLanguageProviderEnglishUS;
import com.mythscapes.datagen.loot_table_providers.MythLootTableProvider;
import com.mythscapes.datagen.tag_providers.MythBlockTagProvider;
import com.mythscapes.datagen.tag_providers.MythEntityTagProvider;
import com.mythscapes.datagen.tag_providers.MythFluidTagProvider;
import com.mythscapes.datagen.tag_providers.MythItemTagProvider;
import net.minecraft.data.BlockStateProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGatherer {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            dataGenerator.addProvider(new MythLanguageProviderEnglishUS(dataGenerator));
            dataGenerator.addProvider(new MythBlockStateProvider(dataGenerator, existingFileHelper));
        }
        if (event.includeServer()) {
            MythBlockTagProvider blockTagProvider = new MythBlockTagProvider(dataGenerator, event.getExistingFileHelper());

            dataGenerator.addProvider(new MythLootTableProvider(dataGenerator));
            dataGenerator.addProvider(new MythRecipeProvider(dataGenerator));
            dataGenerator.addProvider(blockTagProvider);
            dataGenerator.addProvider(new MythItemTagProvider(dataGenerator, blockTagProvider, existingFileHelper));
            dataGenerator.addProvider(new MythEntityTagProvider(dataGenerator));
            dataGenerator.addProvider(new MythFluidTagProvider(dataGenerator));
        }
    }
}
