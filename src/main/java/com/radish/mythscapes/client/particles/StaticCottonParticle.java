package com.radish.mythscapes.client.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class StaticCottonParticle extends AbstractFadingParticle {

    public StaticCottonParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.xd = (getRandom().nextGaussian() * 0.02) * xSpeed;
        this.yd = (getRandom().nextGaussian() * 0.02) * ySpeed;
        this.zd = (getRandom().nextGaussian() * 0.02) * zSpeed;
        this.setLifetime(40);
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            StaticCottonParticle particle = new StaticCottonParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
