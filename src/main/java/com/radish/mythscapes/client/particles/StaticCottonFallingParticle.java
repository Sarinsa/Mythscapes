package com.radish.mythscapes.client.particles;

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
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.setSize(0.15f, 0.15f);
        this.setLifetime(50);
        this.rotationSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.roll = (float)Math.random() * ((float)Math.PI * 2F);
    }

    @Override
    public float getQuadSize(float scale) {
        return this.quadSize * MathHelper.clamp(((float)this.age + scale) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    public void tick() {
        this.subtractAlpha();
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
        else {
            this.oRoll = this.roll;
            this.roll += (float)Math.PI * this.rotationSpeed * 2.0F;

            if (this.onGround) {
                this.oRoll = this.roll = 0.0F;
            }
            this.move(this.xd, this.yd, this.zd);
            this.yd -= 0.003F;
            this.yd = Math.max(this.yd, -0.14F);
        }
    }

    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            StaticCottonFallingParticle particle = new StaticCottonFallingParticle(world, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
