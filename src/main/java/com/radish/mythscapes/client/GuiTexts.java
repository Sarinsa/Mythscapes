package com.radish.mythscapes.client;

import com.radish.mythscapes.common.core.Mythscapes;

import static com.radish.mythscapes.client.GuiTexts.TextType.JEI_ITEM_DESC;

/**
 * Keeping all gui text in here for easy reference
 */
public class GuiTexts {

    public static final String BRUSH_INFO = create(JEI_ITEM_DESC, "brush_item");

    private static String create(TextType textType, String translationKey) {
        return Mythscapes.MODID + "." + textType.getKey() + "." + translationKey;
    }

    public enum TextType {
        JEI_ITEM_DESC("jei.item_desc");

        TextType(String key) {
            this.key = key;
        }

        private String getKey() {
            return this.key;
        }

        private final String key;
    }
}
