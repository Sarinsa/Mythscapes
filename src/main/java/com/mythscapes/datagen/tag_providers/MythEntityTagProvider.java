package com.mythscapes.datagen.tag_providers;

import com.mythscapes.common.tags.MythEntityTags;
import com.mythscapes.register.MythEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.entity.EntityType;

public class MythEntityTagProvider extends EntityTypeTagsProvider {

    public MythEntityTagProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
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
