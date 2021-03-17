package com.radish.mythscapes.common.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MythFoods {

    public static final Food POND_SERPENT = new Food.Builder()
            .nutrition(1)
            .saturationMod(0.2f)
            .build();
    public static final Food BLISTERBERRY = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.POISON, 20 * 3, 1), 1.0f)
            .nutrition(1)
            .saturationMod(0.1f)
            .fast()
            .build();
    public static final Food BIOBULB = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.GLOWING, 20 * 15, 0), 1.0f)
            .nutrition(4)
            .saturationMod(0.2f)
            .fast()
            .build();
    public static final Food ROASTED_BIOBULB = new Food.Builder()
            .nutrition(5)
            .saturationMod(0.3f)
            .fast()
            .build();
    public static final Food WOLT_FRUIT = new Food.Builder()
            .nutrition(3)
            .saturationMod(0.3f)
            .alwaysEat()
            .build();
    public static final Food GOLDEN_WOLT_FRUIT = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.JUMP, 20 * 15), 1.0f)
            .effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 20 * 15), 1.0f)
            .nutrition(3)
            .saturationMod(0.3f)
            .alwaysEat()
            .build();
}
