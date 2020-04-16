package com.mythscapes.client.particles;

import com.mythscapes.register.MythParticles;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.MetaParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StaticCottonPoofParticle extends MetaParticle {

    private int timeSinceStart;
    private final int maxTime = 5;

    private StaticCottonPoofParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z);
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
    }

    private double getVelocityMultiplier() {
        return ((double)this.timeSinceStart / (double)this.maxTime);
    }

    public void tick() {
        for(int i = 0; i < 8; ++i) {
            double xSpeed = this.motionX;
            double ySpeed = this.motionY / 2;
            double zSpeed = this.motionZ;
            this.world.addParticle(MythParticles.STATIC_COTTON.get(), this.posX, this.posY, this.posZ, xSpeed, ySpeed, zSpeed);
        }
        ++this.timeSinceStart;
        if (this.timeSinceStart == maxTime) {
            this.setExpired();
        }
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        public Particle makeParticle(BasicParticleType type, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new StaticCottonPoofParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}
