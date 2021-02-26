package com.radish.mythscapes.common.core.config.helpers;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuarkConfigHelper extends AbstractConfigHelper {

    private static final String QUARK_SIGN_EDIT = "tweaks.Sign Editing";
    private static final String QUARK_SIGN_EDIT_REQUIRE_EMPTY_HAND = "tweaks.sign_editing.Requires Empty Hand";

    public QuarkConfigHelper(@NotNull ModConfig modConfig) {
        super(modConfig);
    }

    @Override
    public List<String> getConfigEntries() {
        return ImmutableList.of(QUARK_SIGN_EDIT, QUARK_SIGN_EDIT_REQUIRE_EMPTY_HAND);
    }

    public boolean signEditingEnabled() {
        return this.getValue(QUARK_SIGN_EDIT);
    }

    public boolean signEditRequireEmptyHand() {
        return this.getValue(QUARK_SIGN_EDIT_REQUIRE_EMPTY_HAND);
    }
}
