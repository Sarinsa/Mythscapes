package com.mythscapes.datagen.lang_providers;

import com.mythscapes.common.entities.living.SnailEntity;
import com.mythscapes.core.Mythscapes;
import net.minecraft.data.DataGenerator;
import net.minecraft.potion.Potion;
import net.minecraft.util.Util;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public abstract class MythBaseLangProvider extends LanguageProvider {

    protected static final String VICTIM = "%1$s";
    protected static final String ATTACKER = "%2$s";

    public MythBaseLangProvider(DataGenerator gen, String locale) {
        super(gen, Mythscapes.MODID, locale);
    }

    protected void addSnailType(SnailEntity.SnailType snailType, String localized) {
        this.add("snail_type." + snailType.getName(), localized);
    }

    protected void addPotion(Supplier<Potion> potionSupplier, String localized) {
        this.add(Util.makeTranslationKey("potion", ForgeRegistries.POTION_TYPES.getKey(potionSupplier.get())), localized);
    }

    protected void addDamageSource(String damageSource, String localized) {
        this.add("attack.death." + damageSource, localized);
    }

    protected void addDamageSourceWithAttacker(String damageSource, String localized) {
        this.add("attack.death." + damageSource + ".player", localized);
    }
}
