package com.radish.mythscapes.common.misc;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MythFoods {

    public static final Food POND_SERPENT = new Food.Builder()
            .hunger(1)
            .saturation(0.2f)
            .build();
    public static final Food BLISTERBERRY = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.POISON, 20 * 3, 1), 1.0f)
            .hunger(1).saturation(0.1f)
            .fastToEat()
            .build();
    public static final Food BIOBULB = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.GLOWING, 20 * 15, 0), 1.0f)
            .hunger(4)
            .saturation(0.2f)
            .fastToEat()
            .build();
    public static final Food ROASTED_BIOBULB = new Food.Builder()
            .hunger(5)
            .saturation(0.3f)
            .fastToEat()
            .build();
    public static final Food WOLT_FRUIT = new Food.Builder()
            .hunger(3)
            .saturation(0.3f)
            .setAlwaysEdible()
            .build();
    public static final Food GOLDEN_WOLT_FRUIT = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.JUMP_BOOST, 20 * 15), 1.0f)
            .effect(() -> new EffectInstance(Effects.SPEED, 20 * 15), 1.0f)
            .hunger(3)
            .saturation(0.3f)
            .setAlwaysEdible()
            .build();
}
