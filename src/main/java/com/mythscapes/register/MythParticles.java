package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Mythscapes.MODID);

    public static final RegistryObject<BasicParticleType> STATIC_COTTON = PARTICLES.register("static_cotton", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> STATIC_COTTON_FALLING = PARTICLES.register("static_cotton_falling", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> STATIC_COTTON_POOF = PARTICLES.register("static_cotton_poof", () -> new BasicParticleType(true));
}
