package com.radish.mythscapes.api.impl.snail_types;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.util.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Bejeweled implements ISnailType {

    private static final ResourceLocation TEXTURE = TextureUtil.entityTexture("pygmy_snail/bejeweled");
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
    public @Nullable ItemStack getShedDrop(Random random) {
        return new ItemStack(MythItems.BEJEWELED_SNAIL_SHELL.get());
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }
}
