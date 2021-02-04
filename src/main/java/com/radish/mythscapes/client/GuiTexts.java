package com.radish.mythscapes.client;

import com.radish.mythscapes.common.core.Mythscapes;

import static com.radish.mythscapes.client.GuiTexts.TextType.JEI_ITEM_DESC;
import static com.radish.mythscapes.client.GuiTexts.TextType.MOD_CONFIG;

/**
 * Keeping all gui text in here for easy reference
 */
public class GuiTexts {

    // JEI stuff
    public static final String BRUSH_INFO = create(JEI_ITEM_DESC, "brush_item");

    // Mod config
    public static final String CONFIG_OOF = create(MOD_CONFIG, "oof");
    public static final String CONFIG_MAIN_SCREEN_TITLE = create(MOD_CONFIG, "main_screen_title");
    public static final String CONFIG_OH_NO = create(MOD_CONFIG, "oh_no");
    public static final String OPEN_CONFIG = create(MOD_CONFIG, "open_config");
    public static final String CONFIG_NOT_PRESENT = create(MOD_CONFIG, "config_not_present");

    private static String create(TextType textType, String translationKey) {
        return Mythscapes.MODID + "." + textType.getKey() + "." + translationKey;
    }

    public enum TextType {
        JEI_ITEM_DESC("jei.item_desc"),
        MOD_CONFIG("screen.config");

        TextType(String key) {
            this.key = key;
        }

        private String getKey() {
            return this.key;
        }

        private final String key;
    }
}
