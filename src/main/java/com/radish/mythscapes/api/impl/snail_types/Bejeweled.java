package com.radish.mythscapes.api.impl.snail_types;

import com.google.common.collect.ImmutableList;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class Bejeweled implements ISnailType {

    private static final ResourceLocation TEXTURE = Mythscapes.resourceLoc("textures/entity/pygmy_snail/bejeweled.png");
    private static final ResourceLocation NAME = Mythscapes.resourceLoc("bejeweled");

    @Override
    public ResourceLocation getSnailTexture() {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getName() {
        return NAME;
    }

    @Override
    public @Nullable ItemStack getShedDrop(@NotNull Random random) {
        return new ItemStack(MythItems.BEJEWELED_SNAIL_SHELL.get());
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public List<ResourceLocation> getSpawnBiomes() {
        return ImmutableList.of(Biomes.BEACH.getLocation());
    }
}
