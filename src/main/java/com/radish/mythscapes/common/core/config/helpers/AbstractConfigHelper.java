package com.radish.mythscapes.common.core.config.helpers;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractConfigHelper {

    private final CommentedConfig config;
    private final String modid;

    public AbstractConfigHelper(@NotNull ModConfig modConfig) {
        this.config = modConfig.getConfigData();
        this.modid = modConfig.getModId();
    }

    public final CommentedConfig getConfig() {
        return this.config;
    }

    public abstract List<String> getConfigEntries();

    public <T> T getValue(String entry) {
        return this.getConfig().get(entry);
    }

    /**
     * All this does is hopefully provide some log error info
     * in case a mod's config entries changes name or hierarchy
     * after an update, in which case we need to change the entry
     * names in our config helpers ourselves.
     *
     * @param configEntries A List of Strings representing the entry names
     *                      of all the config values the ConfigHelper contains.
     *
     * @param config The CommentedConfig instance of the mod config.
     */
    public final void validateEntries(List<String> configEntries, CommentedConfig config) {
        for (String entry : configEntries) {
            if (!config.contains(entry))
                Mythscapes.LOGGER.log(Level.ERROR, "[ConfigHelper] " + "Config entry \"{}\" does not exist in mod config for modid \"{}\". Config compat may not work for this mod", entry, this.modid);
        }
    }
}
