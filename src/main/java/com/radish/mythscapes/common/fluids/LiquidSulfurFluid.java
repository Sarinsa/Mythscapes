package com.radish.mythscapes.common.fluids;

import com.radish.mythscapes.common.register.MythFluids;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LiquidSulfurFluid extends BaseFluid {

    public LiquidSulfurFluid() {
        super(MythItems.LIQUID_SULFUR_BOTTLE, MythFluids.Properties.PROPERTIES_SULFUR);
    }

    @Override
    public boolean isSource(FluidState state) {
        return false;
    }

    @Override
    public int getAmount(FluidState state) {
        return 0;
    }

    @Override
    public IParticleData getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    public static class Flowing extends LiquidSulfurFluid {

        public Flowing() {
            super();
            this.registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends LiquidSulfurFluid {

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
