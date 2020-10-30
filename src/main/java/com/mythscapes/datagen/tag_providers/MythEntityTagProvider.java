package com.mythscapes.datagen.tag_providers;

import com.mythscapes.common.tags.MythEntityTags;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MythEntityTagProvider extends EntityTypeTagsProvider {

    public MythEntityTagProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Mythscapes.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        this.getOrCreateBuilder(MythEntityTags.DIES_IN_SULFUR).add(
                EntityType.GUARDIAN,
                EntityType.DOLPHIN,
                EntityType.SQUID,
                EntityType.COD,
                EntityType.SALMON,
                EntityType.PUFFERFISH,
                EntityType.TROPICAL_FISH,
                MythEntities.POND_SERPENT.get()
        );
    }
}
