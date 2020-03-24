package com.mythscapes.misc;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MythFoods {

    public static final Food BLISTERBERRY = new Food.Builder().hunger(1).saturation(1.0f).fastToEat().effect(() -> new EffectInstance(Effects.POISON, (3 * 20), 1), 1.0f).build();
}
