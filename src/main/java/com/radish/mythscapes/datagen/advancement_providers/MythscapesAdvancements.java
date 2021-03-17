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
        // Snail achievement
        Advancement.Builder.advancement()
                .display(MythItems.SNAIL_BUCKET.get(),
                        textComponent("root.title"),
                        textComponent("root.description"),
                        new ResourceLocation("textures/gui/advancements/backgrounds/husbandry.png"),
                        FrameType.TASK, true, true, false)
                .addCriterion("bucket_snail", InventoryChangeTrigger.Instance.hasItems(MythItems.SNAIL_BUCKET.get()))
                .save(consumer, "mythscapes/root");
    }

    private static TranslationTextComponent textComponent(String translationKey) {
        return new TranslationTextComponent("advancements." + Mythscapes.MODID + "." + translationKey);
    }
}
