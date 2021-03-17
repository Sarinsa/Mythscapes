package com.radish.mythscapes.common.worldgen.surface_builder;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;

public class DefaultBlockSurfaceBuilderConfig implements ISurfaceBuilderConfig {

    public static final Codec<DefaultBlockSurfaceBuilderConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BlockState.CODEC.fieldOf("top_material").forGetter((config) -> config.topMaterial),
            BlockState.CODEC.fieldOf("under_material").forGetter((config) -> config.underMaterial),
            BlockState.CODEC.fieldOf("underwater_material").forGetter((config) -> config.underwaterMaterial),
            BlockState.CODEC.fieldOf("default_material").forGetter((config) -> config.defaultMaterial))
            .apply(instance, DefaultBlockSurfaceBuilderConfig::new));

    private final BlockState topMaterial;
    private final BlockState underMaterial;
    private final BlockState underwaterMaterial;
    private final BlockState defaultMaterial;

    public DefaultBlockSurfaceBuilderConfig(BlockState topMaterial, BlockState underMaterial, BlockState underwaterMaterial, BlockState defaultMaterial) {
        this.topMaterial = topMaterial;
        this.underMaterial = underMaterial;
        this.underwaterMaterial = underwaterMaterial;
        this.defaultMaterial = defaultMaterial;
    }

    @Override
    public BlockState getTopMaterial() {
        return this.topMaterial;
    }

    @Override
    public BlockState getUnderMaterial() {
        return this.underMaterial;
    }

    public BlockState getUnderwaterMaterial() {
        return this.underwaterMaterial;
    }

    public BlockState getDefaultMaterial() {
        return this.defaultMaterial;
    }
}
