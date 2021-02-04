package com.radish.mythscapes.client.screen.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.client.GuiTexts;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public final class MythConfigScreen extends Screen {

    /**
     * The standard color that MC uses for
     * most texts rendered in GUIs.
     */
    protected static final int standardColor = 16777215;

    /**
     * The screen to return to when this
     * one has been exited through a button.
     */
    private final Screen homeScreen;
    private final String configPath;

    private static final ITextComponent ohNoMessage = new TranslationTextComponent(GuiTexts.CONFIG_OH_NO);


    public MythConfigScreen(Screen homeScreen) {
        super(new TranslationTextComponent(GuiTexts.CONFIG_MAIN_SCREEN_TITLE));
        this.homeScreen = homeScreen;
        this.configPath = ConfigTracker.INSTANCE.getConfigFileName(Mythscapes.MODID, ModConfig.Type.COMMON);
    }

    @Override
    public void init(@NotNull Minecraft client, int width, int height) {
        super.init(client, width, height);
        Objects.requireNonNull(minecraft);

        // Perhaps I'll get around to making an actual in-game config screen at some point, but we have higher priorities at the moment.
        this.addButton(new Button((width / 2) - 30, height - 60, 60, 20, new TranslationTextComponent(GuiTexts.CONFIG_OOF), button -> {
            minecraft.displayGuiScreen(this.homeScreen);
            minecraft.getSoundHandler().play(SimpleSound.master(MythSounds.OOF.get(), 1.0F));
        }));
        boolean configPresent = this.configPath != null && !this.configPath.isEmpty();

        this.addButton(new Button((width / 2) - 45, height / 2, 90, 20, configPresent ? new TranslationTextComponent(GuiTexts.OPEN_CONFIG) : new TranslationTextComponent(GuiTexts.CONFIG_NOT_PRESENT), button -> {
            if (!configPresent)
                return;

            File config = new File(this.configPath);
            Util.getOSType().openFile(config);
        }));
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int x, int y, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, font, this.getTitle(), width / 2, 30, standardColor);
        drawCenteredString(matrixStack, font, ohNoMessage, width / 2, (height / 2) - 20, standardColor);
        super.render(matrixStack, x, y, partialTicks);
    }
}
