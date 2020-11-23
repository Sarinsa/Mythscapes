package com.radish.mythscapes.datagen.lang_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.Potion;
import net.minecraft.util.Util;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class MythBaseLangProvider extends LanguageProvider {

    protected static final String VICTIM = "%1$s";
    protected static final String ATTACKER = "%2$s";

    public MythBaseLangProvider(DataGenerator gen, String locale) {
        super(gen, Mythscapes.MODID, locale);
    }

    protected void addSnailType(SnailEntity.SnailType snailType, String localized) {
        this.add("snail_type." + Mythscapes.MODID + "." + snailType.getName(), localized);
    }

    @SafeVarargs
    protected final void addFluid(String localized, Supplier<FlowingFluid>... fluidSuppliers) {
        for (Supplier<FlowingFluid> fluid : fluidSuppliers) {
            String name = Objects.requireNonNull(fluid.get().getRegistryName()).getPath();
            this.add("fluid." + Mythscapes.MODID + "." + name, localized);
        }
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

    protected void addAdvancementTitle(String name, String localized) {
        this.add("advancements." + Mythscapes.MODID + "." + name + ".title", localized);
    }

    protected void addAdvancementDesc(String name, String localized) {
        this.add("advancements." + Mythscapes.MODID + "." + name + ".description", localized);
    }
}
