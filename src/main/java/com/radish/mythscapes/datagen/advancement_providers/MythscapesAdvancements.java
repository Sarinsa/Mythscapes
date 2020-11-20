package com.radish.mythscapes.datagen.advancement_providers;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class MythscapesAdvancements implements Consumer<Consumer<Advancement>>{

    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.builder()
                .withDisplay(MythItems.SNAIL_BUCKET.get(),
                        textComponent("root.title"),
                        textComponent("root.description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"),
                        FrameType.TASK, true, true, false)
                .withCriterion("bucket_snail", InventoryChangeTrigger.Instance.forItems(MythItems.SNAIL_BUCKET.get()))
                .register(consumer, "mythscapes/root");
    }

    private static TranslationTextComponent textComponent(String translationKey) {
        return new TranslationTextComponent("advancements." + Mythscapes.MODID + "." + translationKey);
    }
}
