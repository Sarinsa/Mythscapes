package com.mythscapes.register;

import com.mythscapes.common.effects.StaticEffect;
import com.mythscapes.common.effects.VolatileEffect;
import com.mythscapes.core.Mythscapes;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEffects {

    public static final DeferredRegister<Effect> POTION_EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, Mythscapes.MODID);

    public static final RegistryObject<Effect> VOLATILE = POTION_EFFECTS.register("volatile", () -> new VolatileEffect(EffectType.HARMFUL, 0xFFF0FF));
    public static final RegistryObject<Effect> STATIC = POTION_EFFECTS.register("static", () -> new StaticEffect(EffectType.HARMFUL, 0xFF0FF0));
}
