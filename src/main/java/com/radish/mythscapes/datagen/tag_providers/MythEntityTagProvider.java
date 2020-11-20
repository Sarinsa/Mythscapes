package com.radish.mythscapes.datagen.tag_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.tags.MythEntityTags;
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
        this.getOrCreateBuilder(MythEntityTags.ELECTRIC).add(
                // This is just here for now so that the tag generates
                EntityType.ENDER_DRAGON
        );
        this.getOrCreateBuilder(MythEntityTags.LION_PREY).add(
                MythEntities.DEER.get(),
                EntityType.COW,
                EntityType.SHEEP,
                EntityType.PIG,
                EntityType.CHICKEN,
                EntityType.RABBIT
        );
    }
}
