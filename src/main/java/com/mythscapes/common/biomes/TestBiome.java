package com.mythscapes.common.biomes;

import com.mythscapes.common.worldgen.MythFillerBlockTypes;
import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythEntities;
import com.mythscapes.register.MythSurfaceBuilders;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class TestBiome extends BaseBiome {

    public TestBiome() {
        super(new Biome.Builder()
                .surfaceBuilder(MythSurfaceBuilders.STATIC_FOREST_TEMP, MythSurfaceBuilders.GRASS_GALVITE_GRAVEL_CONFIG)
                .precipitation(RainType.NONE)
                .category(Category.TAIGA)
                .depth(0.2F)
                .scale(0.2F)
                .temperature(0.25F)
                .downfall(0.8F)
                .waterColor(4159204)
                .waterFogColor(349011)
                .parent(null));
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addTaigaLargeFerns(this);
        DefaultBiomeFeatures.addScatteredOakAndSpruceTrees(this);
        this.addOre(MythFillerBlockTypes.GALVITE, MythBlocks.BEJEWELED_GALVITE, 9, 1, 18);
        this.addOre(MythFillerBlockTypes.GALVITE, MythBlocks.GILDED_GALVITE, 9, 1, 20);
        this.addOre(MythFillerBlockTypes.GALVITE, MythBlocks.POWERED_GALVITE, 9, 1, 22);
    }

    @Override
    public void addEntitySpawns() {
        this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(MythEntities.POND_SERPENT.get(), 5, 1, 1));
    }
}
