package com.radish.mythscapes.common.biomes;

/*
public abstract class BaseBiome extends Biome {

    public BaseBiome(Builder builder) {
        super(builder);
        MythBiomes.biome_list.add(this);
    }

    /**
     * When extending this class, entity spawns
     * should be added in this method which is
     * called during FMLServerAboutToStartEvent.
     * Biomes are registered before entities, so
     * any attempts to add entity spawns in the
     * biome constructor will give a NullPointer.

    public abstract void addEntitySpawns();

    protected void addOre(OreFeatureConfig.FillerBlockType fillerBlock, BlockState ore, int veinSize, int minY, int maxY) {
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(fillerBlock, ore, veinSize)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(minY, 0, 0, maxY))));
    }

    protected void addOre(OreFeatureConfig.FillerBlockType fillerBlock, Supplier<Block> ore, int veinSize, int minY, int maxY) {
        this.addOre(fillerBlock, ore.get().getDefaultState(), veinSize, minY, maxY);
    }
}
*/


