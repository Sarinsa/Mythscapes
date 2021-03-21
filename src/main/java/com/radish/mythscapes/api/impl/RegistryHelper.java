package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.api.IMythscapesPlugin;
import com.radish.mythscapes.api.IRegistryHelper;
import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.register.MythEntities;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

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
    private String pluginID;

    public RegistryHelper() {
        this.pluginID = "missingno";
    }

    public void setCurrentPluginID(IMythscapesPlugin pluginInstance) {
        this.pluginID = pluginInstance.getPluginName();
    }

    public void registerBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> iBrushable) {
        if (!validBrushable(entityClass, iBrushable))
            return;
        MythEntities.BRUSHABLES.putIfAbsent(entityClass, iBrushable);
    }

    @Override
    public void overrideBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> newBrushable) {
        if (!validBrushable(entityClass, newBrushable))
            return;

        if (MythEntities.BRUSHABLES.containsKey(entityClass)) {
            MythEntities.BRUSHABLES.put(entityClass, newBrushable);
        }
        else {
            LOGGER.warn("Mythscapes plugin '{}' tried to override a brushable that does not exist.", this.pluginID);
        }
    }

    @Override
    public void registerSnailType(ISnailType snailType) {
        SnailTypeRegister register = SnailTypeRegister.INSTANCE;

        if (snailType == null || (snailType.getName() == null || snailType.getName() == null)) {
            LOGGER.warn("Plugin {} tried to register a snail type that is either null or has no name. Oof.", this.pluginID);
            return;
        }
        if (snailType.getRarity() == null) {
            LOGGER.warn("Plugin {} tried to register snail type with null rarity. For shame! Type: {}", this.pluginID, snailType.getName());
            return;
        }
        if (register.getRegistry().containsKey(snailType.getName())) {
            LOGGER.warn("Plugin {} tried to register duplicate snail type! Type: {}", this.pluginID, snailType.getName());
            return;
        }
        register.register(snailType);
    }

    @Override
    public void registerSnailSpawn(ResourceLocation biomeName, ISnailType snailType, int weight) {
        if (weight < 1) {
            LOGGER.warn("Plugin {} tried to register a null snail spawn entry with 0 or negative weight!", this.pluginID);
            return;
        }

        if (snailType == null || biomeName == null) {
            LOGGER.warn("Plugin {} tried to register a snail spawn entry with null biome name or snail type.", this.pluginID);
            return;
        }
        SnailTypeRegister.INSTANCE.registerSpawnEntry(snailType, biomeName, weight);
    }

    private boolean validBrushable(Class<? extends LivingEntity> entityClass, IBrushable<?> iBrushable) {
        if (entityClass == null || iBrushable == null) {
            LOGGER.warn("Plugin {} tried to register null brushable! This can't be right?", this.pluginID);
            return false;
        }
        if (MythEntities.BRUSHABLES.containsKey(entityClass)) {
            LOGGER.warn("Plugin {} tried to register duplicate brushable for entity class: {}", this.pluginID, entityClass.getName());
            return false;
        }
        return true;
    }

    public void postPluginSetup() {
        SnailTypeRegister register = SnailTypeRegister.INSTANCE;
        register.invalidate();
    }
}
