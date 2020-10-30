package com.mythscapes.common.dimension;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Dimension;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nullable;
import java.util.Set;

/*
public class PrimeDimension extends Dimension {

    public static class PrimeBiomeProvider extends BiomeProvider {

        protected PrimeBiomeProvider(Set<Biome> biomesIn) {
            super(biomesIn);
        }

        @Override
        public Biome getNoiseBiome(int x, int y, int z) {
            return null;
        }
    }


    public PrimeDimension(World world, DimensionType dimensionType) {
        super(world, dimensionType, 1.0f);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {

        OverworldGenSettings genSettings = ChunkGeneratorType.SURFACE.createSettings();

        genSettings.setDefaultBlock(Blocks.STONE.getDefaultState());
        genSettings.setDefaultFluid(Blocks.WATER.getDefaultState());

        return ChunkGeneratorType.SURFACE.create(this.world, BiomeProviderType.FIXED.create(BiomeProviderType.VANILLA_LAYERED.createSettings(this.world.getWorldInfo()).setBiome(CruxBiomes.CRUX_BIOME.get())), genSettings);
        return null;
    }

    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        return null;
    }

    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return null;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }
}
*/
