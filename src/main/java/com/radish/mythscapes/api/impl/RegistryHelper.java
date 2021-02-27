package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.api.IMythscapesPlugin;
import com.radish.mythscapes.api.IRegistryHelper;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEntities;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.radish.mythscapes.common.core.Mythscapes.LOGGER;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("all")
public final class RegistryHelper implements IRegistryHelper {

    /**
     * Used for printing debug info to the log.
     * Updates each time a new plugin is found.
     */
    private String pluginID = "missingno";


    public void setCurrentPluginID(IMythscapesPlugin pluginInstance) {
        this.pluginID = pluginInstance.getPluginName();
    }

    public void registerBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> iBrushable) {
        if (entityClass == null || iBrushable == null) {
            LOGGER.warn("Plugin " + this.pluginID + " tried to register null brushable! This can't be right?");
            return;
        }
        if (MythEntities.BRUSHABLES.containsKey(entityClass)) {
            LOGGER.warn("Plugin " + this.pluginID + " tried to register duplicate brushable for entity class: " + entityClass.getName());
            return;
        }
        MythEntities.BRUSHABLES.putIfAbsent(entityClass, iBrushable);
    }

    @Override
    public void registerSnailType(ISnailType snailType) {
        SnailTypeRegister register = Mythscapes.getInstance().getSnailTypeRegister();

        if (snailType == null || (snailType.getName() == null || snailType.getName() == null)) {
            LOGGER.warn("Plugin " + this.pluginID + " tried to register a snail type that is either null or has no name. Oof.");
            return;
        }
        if (snailType.getRarity() == null) {
            LOGGER.warn("Plugin " + this.pluginID + " tried to register snail type with null rarity. For shame! Type: " + snailType.getName());
            return;
        }
        if (register.getRegistry().containsKey(snailType.getName())) {
            LOGGER.warn("Plugin " + this.pluginID + " tried to register duplicate snail type! Type: " + snailType.getName());
            return;
        }
        register.register(snailType);
    }

    public void postPluginSetup() {
        SnailTypeRegister register = Mythscapes.getInstance().getSnailTypeRegister();
        register.invalidate();
        register.setupSnailSpawns();
    }
}
