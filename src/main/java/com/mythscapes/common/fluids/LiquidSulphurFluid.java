package com.mythscapes.common.fluids;

import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class LiquidSulphurFluid extends ForgeFlowingFluid {

    public LiquidSulphurFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(IFluidState state) {
        return false;
    }

    @Override
    public int getLevel(IFluidState state) {
        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public IParticleData getDripParticleData() {
        return ParticleTypes.DRIPPING_WATER;
    }
}
