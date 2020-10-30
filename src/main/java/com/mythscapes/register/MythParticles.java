package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Mythscapes.MODID);

    private static RegistryObject<BasicParticleType> registerBasic(String name, boolean alwaysRender) {
        return PARTICLES.register(name, () -> new BasicParticleType(alwaysRender));
    }

    public static final RegistryObject<BasicParticleType> STATIC_COTTON = registerBasic("static_cotton", false);
    public static final RegistryObject<BasicParticleType> STATIC_COTTON_FALLING = registerBasic("static_cotton_falling", false);
    public static final RegistryObject<BasicParticleType> STATIC_COTTON_POOF = registerBasic("static_cotton_poof", true);
}
