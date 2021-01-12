package com.radish.mythscapes.api;

import org.jetbrains.annotations.NotNull;

/**
 * This is the main interface that your plugin class must implement
 * in order to interact with the rest of the API.
 */
public interface IMythscapesPlugin {

    /**
     * Called during FMLLoadCompleteEvent, when this plugin has been detected and validated.
     *
     * @param iRegistryHelper The IRegistryHelper instance parsed
     *                        by Mythscapes.
     */
    void register(final IRegistryHelper iRegistryHelper);

    /**
     * @return The name of this plugin.
     *         Used for debug and logging.
     */
    @NotNull
    String getPluginName();
}
