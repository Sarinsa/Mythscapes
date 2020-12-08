package com.radish.mythscapes.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used by Mythscapes to detect mod plugins, so
 * your plugin class must be annotated or else it will not be loaded!
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MythscapesPlugin {

    /**
     * @return The mod ID of your mod.
     */
    String modid() default "";
}
