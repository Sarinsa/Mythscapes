package com.radish.mythscapes.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class MythscapesBaseCommand {

    // This shouldn't happen at all, like ever, but still
    private static final SimpleCommandExceptionType NO_SNAIL_TYPES = new SimpleCommandExceptionType(new TranslationTextComponent("commands.mythscapes.mythscapes.snailtypes.empty"));

    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(Commands.literal("mythscapes")
                .then(SnailTypesCommand.register()));
    }

    private static class SnailTypesCommand {

        private static ArgumentBuilder<CommandSource, ?> register() {
            return Commands.literal("snailtypes")
                    .requires((source) -> source.hasPermission(0))
                    .executes(SnailTypesCommand::showSnailTypes);
        }

        private static int showSnailTypes(CommandContext<CommandSource> context) throws CommandSyntaxException {
            CommandSource commandSource = context.getSource();

            if (SnailTypeRegister.INSTANCE.getRegistry().isEmpty()) {
                throw NO_SNAIL_TYPES.create();
            }

            SnailTypeRegister.INSTANCE.getRegistry().forEach((resourceLocation, snailType) -> {
                String translationKey = SnailTypeRegister.getTranslationKey(snailType);
                commandSource.sendSuccess(new StringTextComponent(resourceLocation.toString() + " ").append(new TranslationTextComponent(translationKey).withStyle(TextFormatting.GRAY)), false);
            });
            return 0;
        }
    }
}
