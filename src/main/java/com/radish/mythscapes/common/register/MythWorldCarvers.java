package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.Block;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

public class MythWorldCarvers {

    private static final List<Block> ADDITIONAL_CARVABLES = new ArrayList<>();

    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Mythscapes.MODID);

    public static void addCarvableBlocks() {
        addCarvable(MythBlocks.GALVITE);

        registerCarvablesToAll();
    }

    private static void addCarvable(Supplier<Block> blockSupplier) {
        ADDITIONAL_CARVABLES.add(blockSupplier.get());
    }

    /**
     * Loops through the WorldCarver Forge registry
     * and adds our carvable blocks to every
     * carver's carvable block set.
     */
    private static void registerCarvablesToAll() {
        for (WorldCarver<?> worldCarver : ForgeRegistries.WORLD_CARVERS.getValues()) {
            worldCarver.replaceableBlocks = new HashSet<>(worldCarver.replaceableBlocks);
            worldCarver.replaceableBlocks.addAll(ADDITIONAL_CARVABLES);
        }
    }
}
