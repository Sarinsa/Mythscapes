package com.radish.mythscapes.client.particles;

import com.radish.mythscapes.common.register.MythParticles;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.MetaParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;

public class StaticCottonPoofParticle extends MetaParticle {

    private int timeSinceStart;
    // For the emitted particles
    private final double xSpeed;
    private final double ySpeed;
    private final double zSpeed;

    private StaticCottonPoofParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.zSpeed = zSpeed;
    }

    public void tick() {
        for(int i = 0; i < 8; ++i) {
            double xSpeed = this.xSpeed != 0.0D ? this.zSpeed : rand.nextGaussian();
            double ySpeed = this.ySpeed != 0.0D ? this.ySpeed : rand.nextGaussian() / 2;
            double zSpeed = this.zSpeed != 0.0D ? this.zSpeed : rand.nextGaussian();
            this.world.addParticle(MythParticles.STATIC_COTTON.get(), this.posX, this.posY, this.posZ, xSpeed, ySpeed, zSpeed);
        }
        ++this.timeSinceStart;
        if (this.timeSinceStart >= 5) {
            this.setExpired();
        }
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {

        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new StaticCottonPoofParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
