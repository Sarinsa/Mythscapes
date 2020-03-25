package com.mythscapes.register;

import com.mythscapes.core.Mythscapes;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MythEffects {

    public static final DeferredRegister<Effect> POTION_EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, Mythscapes.MODID);
}
