package com.radish.mythscapes.datagen;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.datagen.advancement_providers.MythAdvancementProvider;
import com.radish.mythscapes.datagen.advancement_providers.MythscapesAdvancements;
import com.radish.mythscapes.datagen.lang_providers.MythLanguageProviderEnglishUS;
import com.radish.mythscapes.datagen.loot_table_providers.MythLootTableProvider;
import com.radish.mythscapes.datagen.recipe.MythRecipeProvider;
import com.radish.mythscapes.datagen.tag_providers.MythBlockTagProvider;
import com.radish.mythscapes.datagen.tag_providers.MythEntityTagProvider;
import com.radish.mythscapes.datagen.tag_providers.MythFluidTagProvider;
import com.radish.mythscapes.datagen.tag_providers.MythItemTagProvider;
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
        }
        if (event.includeServer()) {
            MythBlockTagProvider blockTagProvider = new MythBlockTagProvider(dataGenerator, existingFileHelper);

            dataGenerator.addProvider(new MythLootTableProvider(dataGenerator));
            dataGenerator.addProvider(new MythRecipeProvider(dataGenerator));
            dataGenerator.addProvider(new MythAdvancementProvider(dataGenerator, new MythscapesAdvancements()));
            dataGenerator.addProvider(blockTagProvider);
            dataGenerator.addProvider(new MythItemTagProvider(dataGenerator, blockTagProvider, existingFileHelper));
            dataGenerator.addProvider(new MythEntityTagProvider(dataGenerator, existingFileHelper));
            dataGenerator.addProvider(new MythFluidTagProvider(dataGenerator, existingFileHelper));
        }
    }
}
