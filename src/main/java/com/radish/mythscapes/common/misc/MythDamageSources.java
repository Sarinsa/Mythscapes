package com.radish.mythscapes.common.misc;

import net.minecraft.util.DamageSource;

public class MythDamageSources {

    public static final DamageSource VOLATILE_EXPLOSION = new DamageSource("volatile_explosion").setExplosion();
    public static final DamageSource STATIC_SHOCK = new DamageSource("static_shock");
    public static final DamageSource SALT_DEHYDRATION = new DamageSource("salt_dehydration").setDamageIsAbsolute().setDamageBypassesArmor();
}
