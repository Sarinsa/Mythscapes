package com.mythscapes.common.biomes;

import com.mythscapes.register.MythEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class TestBiome extends BaseBiome {

    public TestBiome() {
        super(new Biome.Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
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
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addTaigaLargeFerns(this);
        DefaultBiomeFeatures.addScatteredOakAndSpruceTrees(this);
    }

    @Override
    public void addEntitySpawns() {
        this.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(MythEntities.POND_SERPENT.get(), 5, 1, 1));
    }
}
