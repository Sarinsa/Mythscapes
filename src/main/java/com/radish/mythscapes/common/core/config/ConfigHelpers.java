package com.radish.mythscapes.common.core.config;

import com.radish.mythscapes.common.core.config.helpers.AbstractConfigHelper;
import com.radish.mythscapes.common.core.config.helpers.QuarkConfigHelper;
import com.radish.mythscapes.common.util.SarinsaFreakinSucksException;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ConfigTracker;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.EnumMap;

public class ConfigHelpers {

    // Config helpers are only safe to use after
    // FMLCommonSetupEvent after they have been initialized
    public static LazyOptional<QuarkConfigHelper> QUARK_CONFIG_HELPER;

    public static void initHelpers() {
        QUARK_CONFIG_HELPER = createConfigHelper("quark", ModConfig.Type.COMMON, QuarkConfigHelper::new);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    private static <T extends AbstractConfigHelper> LazyOptional<T> createConfigHelper(String modid, ModConfig.Type configType, IConfigHelperFactory<T> configHelperFactory) {
        LazyOptional<T> configHelperOptional = LazyOptional.empty();

        if (modid == null || configType == null || configHelperFactory == null)
            return configHelperOptional;

        if (ModList.get().isLoaded(modid)) {
            String configName = ConfigTracker.INSTANCE.getConfigFileName(modid, configType);

            if (configName != null && !configName.isEmpty()) {
                ModContainer modContainer = ModList.get().getModContainerById(modid).orElseThrow(() -> new SarinsaFreakinSucksException("Failed to fetch ModContainer instance for modid " + modid));
                Field field = ObfuscationReflectionHelper.findField(ModContainer.class, "configs");

                try {
                    EnumMap<ModConfig.Type, ModConfig> configMap;
                    configMap = (EnumMap<ModConfig.Type, ModConfig>) field.get(modContainer);
                    ModConfig config = configMap.getOrDefault(configType, null);

                    if (config != null) {
                        T configHelper = configHelperFactory.create(config);
                        configHelper.validateEntries(configHelper.getConfigEntries(), config.getConfigData());
                        configHelperOptional = LazyOptional.of(() -> configHelper);
                    }
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return configHelperOptional;
    }

    private interface IConfigHelperFactory<T> {
        T create(ModConfig modConfig);
    }
}
