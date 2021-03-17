package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.worldgen.surface_builder.DefaultBlockSurfaceBuilder;
import com.radish.mythscapes.common.worldgen.surface_builder.DefaultBlockSurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythSurfaceBuilders {

    public static DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Mythscapes.MODID);

    public static final RegistryObject<SurfaceBuilder<DefaultBlockSurfaceBuilderConfig>> DEFAULT_BLOCK_SURFACE_BUILDER = register("default_block", () -> new DefaultBlockSurfaceBuilder(DefaultBlockSurfaceBuilderConfig.CODEC));

    private static <T extends SurfaceBuilder<?>> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return SURFACE_BUILDERS.register(name + "_surface_builder", supplier);
    }
}
