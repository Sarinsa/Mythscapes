package com.radish.mythscapes.client.particles;

import com.radish.mythscapes.common.register.MythParticles;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.MetaParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class StaticCottonPoofParticle extends MetaParticle {

    private int timeSinceStart;

    private StaticCottonPoofParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public void tick() {
        for(int i = 0; i < 8; ++i) {
            double xSpeed = rand.nextGaussian();
            double ySpeed = rand.nextGaussian() / 2;
            double zSpeed = rand.nextGaussian();
            this.world.addParticle(MythParticles.STATIC_COTTON.get(), this.posX, this.posY, this.posZ, xSpeed, ySpeed, zSpeed);
        }
        ++this.timeSinceStart;
        if (this.timeSinceStart >= 5) {
            this.setExpired();
        }
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {

        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new StaticCottonPoofParticle(world, x, y, z);
        }
    }
}
