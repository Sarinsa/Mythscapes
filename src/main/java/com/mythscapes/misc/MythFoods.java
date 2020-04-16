package com.mythscapes.misc;

import net.minecraft.item.Food;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MythFoods {

    public static final Food BLISTERBERRY = new Food.Builder().hunger(1).saturation(0.1f).fastToEat().effect(() -> new EffectInstance(Effects.POISON, (20 * 3), 1), 1.0f).build();
    public static final Food BIOBULB = new Food.Builder().hunger(4).saturation(0.2f).fastToEat().effect(() -> new EffectInstance(Effects.GLOWING, (20 * 30), 0), 1.0f).build();
    public static final Food ROASTED_BIOBULB = new Food.Builder().hunger(5).saturation(0.3f).fastToEat().build();
}
