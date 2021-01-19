package com.radish.mythscapes.common.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
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
        super(properties);
        this.bottleItem = null;
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

    public static class Flowing extends BaseFluid {

        public Flowing(Supplier<Item> bottleItem, Properties properties) {
            super(bottleItem, properties);
        }

        public Flowing(Properties properties) {
            super(properties);
            setDefaultState(getStateContainer().getBaseState().with(LEVEL_1_8, 7));
        }

        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        public int getLevel(FluidState state) {
            return state.get(LEVEL_1_8);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends BaseFluid {

        public Source(Supplier<Item> bottleItem, Properties properties) {
            super(bottleItem, properties);
        }

        public Source(Properties properties) {
            super(properties);
        }

        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
