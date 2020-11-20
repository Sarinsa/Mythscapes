package com.radish.mythscapes.common.fluids;

import net.minecraft.fluid.FluidState;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

public class LiquidSulphurFluid extends ForgeFlowingFluid {

    public LiquidSulphurFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(@NotNull FluidState state) {
        return false;
    }

    @Override
    public int getLevel(@NotNull FluidState state) {
        return 0;
    }

    @Override
    public IParticleData getDripParticleData() {
        return ParticleTypes.DRIPPING_WATER;
    }
}
