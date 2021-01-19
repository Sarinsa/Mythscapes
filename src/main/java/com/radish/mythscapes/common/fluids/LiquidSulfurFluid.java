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
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    public IParticleData getDripParticleData() {
        return ParticleTypes.DRIPPING_WATER;
    }

    public static class Flowing extends LiquidSulfurFluid {

        public Flowing() {
            super();
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

    public static class Source extends LiquidSulfurFluid {

        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
