package com.mythscapes.client.particles;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

import java.util.Random;

public abstract class AbstractFadingParticle extends SpriteTexturedParticle {

    public AbstractFadingParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    protected Random getRandom() {
        return this.rand;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void subtractAlpha() {
        double alpha = (1 - ((double)age / (double)maxAge));
        this.particleAlpha = (float) alpha;
    }

    public void tick() {
        super.tick();
        this.subtractAlpha();
    }
}
