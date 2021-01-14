package com.radish.mythscapes.datagen.lang_providers;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.core.Mythscapes;
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

    protected void addSnailType(ISnailType snailType, String localized) {
        this.add(SnailTypeRegister.getTranslationKey(snailType), localized);
    }

    @SafeVarargs
    protected final void addFluid(String localized, Supplier<FlowingFluid>... fluidSuppliers) {
        for (Supplier<FlowingFluid> fluid : fluidSuppliers) {
            String name = Objects.requireNonNull(fluid.get().getRegistryName()).getPath();
            this.add("fluid." + Mythscapes.MODID + "." + name, localized);
        }
    }

    protected void addPotionItem(Supplier<Potion> potionSupplier, String localized) {
        String name = Objects.requireNonNull(potionSupplier.get().getRegistryName()).getPath();
        this.add("item.minecraft.potion.effect." + name, localized);
    }

    protected void addSplashPotionItem(Supplier<Potion> potionSupplier, String localized) {
        String name = Objects.requireNonNull(potionSupplier.get().getRegistryName()).getPath();
        this.add("item.minecraft.splash_potion.effect." + name, localized);
    }

    protected void addLingeringPotionItem(Supplier<Potion> potionSupplier, String localized) {
        String name = Objects.requireNonNull(potionSupplier.get().getRegistryName()).getPath();
        this.add("item.minecraft.lingering_potion.effect." + name, localized);
    }

    protected void addTippedArrowItem(Supplier<Potion> potionSupplier, String localized) {
        String name = Objects.requireNonNull(potionSupplier.get().getRegistryName()).getPath();
        this.add("item.minecraft.tipped_arrow.effect." + name, localized);
    }


    protected void addPotion(Supplier<Potion> potionSupplier, String localized) {
        this.add(Util.makeTranslationKey("potion", ForgeRegistries.POTION_TYPES.getKey(potionSupplier.get())), localized);
    }

    protected void addDamageSource(String damageSource, String localized) {
        this.add("death.attack." + damageSource, localized);
    }

    protected void addDamageSourceWithAttacker(String damageSource, String localized) {
        this.add("death.attack." + damageSource + ".player", localized);
    }

    protected void addAdvancementTitle(String name, String localized) {
        this.add("advancements." + Mythscapes.MODID + "." + name + ".title", localized);
    }

    protected void addAdvancementDesc(String name, String localized) {
        this.add("advancements." + Mythscapes.MODID + "." + name + ".description", localized);
    }

    protected void addHwylaConfig(String name, String localized) {
        this.add("config.waila.plugin_mythscapes." + name, localized);
    }

    protected void addHwylaTooltip(String name, String localized) {
        this.add("tooltip.waila.plugin_mythscapes." + name, localized);
    }
}
