package com.mythscapes.register;

import com.mythscapes.common.effects.PetrifiedEffect;
import com.mythscapes.common.effects.StaticEffect;
import com.mythscapes.common.effects.VolatileEffect;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEffects {

    public static final DeferredRegister<Effect> POTION_EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Mythscapes.MODID);

    public static final RegistryObject<Effect> VOLATILE = POTION_EFFECTS.register("volatile", () -> new VolatileEffect(EffectType.HARMFUL, 0xC4F247));
    public static final RegistryObject<Effect> STATIC = POTION_EFFECTS.register("static", () -> new StaticEffect(EffectType.HARMFUL, 0x56E5EF));
    public static final RegistryObject<Effect> PETRIFIED = POTION_EFFECTS.register("petrified", () -> new PetrifiedEffect(EffectType.NEUTRAL, 0x605F5A));
}
