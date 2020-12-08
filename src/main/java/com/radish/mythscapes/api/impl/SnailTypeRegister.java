package com.radish.mythscapes.api.impl;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.common.core.Mythscapes;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Random;

public final class SnailTypeRegister {

    public static final LinkedHashMap<String, ISnailType> SNAIL_TYPES = new LinkedHashMap<>();
    private final Random random = new Random();

    public static void registerInternal() {
        RegistryUtil registryHelper = Mythscapes.getInstance().getRegistryHelper();

        //registryHelper.registerSnailType();
    }

    public ISnailType getRandom() {
        return SNAIL_TYPES.get(SNAIL_TYPES.keySet().toArray(new String[0])[random.nextInt(SNAIL_TYPES.size())]);
    }

    public ISnailType getFromName(String name) {
        return SNAIL_TYPES.get(name);
    }

    @Nullable
    public ISnailType getFromNameOrNull(String name) {
        return SNAIL_TYPES.getOrDefault(name, null);
    }
}
