package com.mythscapes.client.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;

public class StaticCottonFallingParticle extends AbstractFadingParticle {

    private final float rotationSpeed;

    protected StaticCottonFallingParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.motionX *= 0.1F;
        this.motionY *= 0.1F;
        this.motionZ *= 0.1F;
        this.setSize(0.15f, 0.15f);
        this.setMaxAge(50);
        this.rotationSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.particleAngle = (float)Math.random() * ((float)Math.PI * 2F);
    }

    @Override
    public float getScale(float scale) {
        return this.particleScale * MathHelper.clamp(((float)this.age + scale) / (float)this.maxAge * 32.0F, 0.0F, 1.0F);
    }

    public void tick() {
        this.subtractAlpha();
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        }
        else {
            this.prevParticleAngle = this.particleAngle;
            this.particleAngle += (float)Math.PI * this.rotationSpeed * 2.0F;

            if (this.onGround) {
                this.prevParticleAngle = this.particleAngle = 0.0F;
            }
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionY -= 0.003F;
            this.motionY = Math.max(this.motionY, -0.14F);
        }
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle makeParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            StaticCottonFallingParticle particle = new StaticCottonFallingParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.selectSpriteRandomly(this.spriteSet);
            return particle;
        }
    }
}
