package com.radish.mythscapes.common.fluids;

import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraftforge.client.IWeatherParticleRenderHandler;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BaseFluid extends ForgeFlowingFluid {

    @Nullable
    private final Supplier<Item> bottleItem;

    public BaseFluid(@Nullable Supplier<Item> bottleItem, Properties properties) {
        super(properties);
        this.bottleItem = bottleItem;
    }

    public BaseFluid(Properties properties) {
        this(null, properties);
    }

    @Nullable
    public Item getBottleItem() {
        return this.bottleItem == null ? null : this.bottleItem.get();
    }

    @Override
    public boolean isSource(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }
}
