package com.radish.mythscapes.api;

import java.lang.annotation.*;

/**
 * This annotation is used by Mythscapes to detect mod plugins, so
 * your plugin class must be annotated or else it will not be found!
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MythscapesPlugin {

    /**
     * The modid value is used to determine whether
     * this plugin should be considered a vanilla addon or not.
     *
     * If the value is left empty, the plugin will always be
     * attempted to be loaded since it is not expected that
     * it interacts with anything else than vanilla content.
     *
     * Otherwise, this value should match your mod's modid.
     *
     * @return Your mod's modid or an empty String.
     */
    String modid() default "";
}
