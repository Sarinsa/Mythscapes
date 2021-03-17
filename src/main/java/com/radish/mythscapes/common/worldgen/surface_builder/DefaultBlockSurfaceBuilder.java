package com.radish.mythscapes.common.worldgen.surface_builder;

import com.mojang.serialization.Codec;
import com.radish.mythscapes.common.register.MythBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class DefaultBlockSurfaceBuilder extends SurfaceBuilder<DefaultBlockSurfaceBuilderConfig> {

    public DefaultBlockSurfaceBuilder(Codec<DefaultBlockSurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, DefaultBlockSurfaceBuilderConfig config) {
        BlockState top = config.getTopMaterial();
        BlockState middle = config.getUnderMaterial();
        BlockState bottom = config.getUnderwaterMaterial();
        BlockState defaultMaterial = config.getDefaultMaterial();
        
        BlockState topState = top;
        BlockState middleState = middle;
        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
        
        int i = -1;
        int j = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int xCoord = x & 15;
        int zCoord = z & 15;

        for(int yCoord = startHeight; yCoord >= 0; --yCoord) {
            mutableBlockPos.set(xCoord, yCoord, zCoord);
            BlockState currentState = chunk.getBlockState(mutableBlockPos);

            if (currentState.isAir(chunk, mutableBlockPos)) {
                i = -1;
            }
            else if (currentState.is(defaultBlock.getBlock())) {
                if (i == -1) {
                    if (j <= 0) {
                        topState = Blocks.AIR.defaultBlockState();
                        middleState = defaultMaterial;
                    }
                    else if (yCoord >= seaLevel - 4 && yCoord <= seaLevel + 1) {
                        topState = top;
                        middleState = middle;
                    }

                    if (yCoord < seaLevel && topState.isAir(chunk, mutableBlockPos)) {
                        if (biome.getTemperature(mutableBlockPos.set(x, yCoord, z)) < 0.15F) {
                            topState = Blocks.ICE.defaultBlockState();
                        }
                        else {
                            topState = defaultFluid;
                        }
                        mutableBlockPos.set(xCoord, yCoord, zCoord);
                    }
                    i = j;

                    if (yCoord >= seaLevel - 1) {
                        chunk.setBlockState(mutableBlockPos, topState, false);
                    }
                    else if (yCoord < seaLevel - 7 - j) {
                        topState = Blocks.AIR.defaultBlockState();
                        middleState = defaultMaterial;
                        chunk.setBlockState(mutableBlockPos, bottom, false);
                    }
                    else {
                        chunk.setBlockState(mutableBlockPos, middleState, false);
                    }
                }
                else if (i > 0) {
                    --i;
                    chunk.setBlockState(mutableBlockPos, middleState, false);

                    if (i == 0 && middleState.is(Blocks.SAND) && j > 1) {
                        i = random.nextInt(4) + Math.max(0, yCoord - 63);
                        middleState = middleState.is(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.defaultBlockState() : Blocks.SANDSTONE.defaultBlockState();
                    }
                }
                else {
                    chunk.setBlockState(mutableBlockPos, defaultMaterial, false);
                }
            }
        }
    }
}
