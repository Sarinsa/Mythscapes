package com.radish.mythscapes.common.core.config;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythBiomes;
import com.radish.mythscapes.common.register.registry.BiomeRegistryObject;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class MythConfig {

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }

    public static class Common {

        private final HashMap<String, ForgeConfigSpec.IntValue> biomeWeights = new HashMap<>();

        // Snail stuff
        public final ForgeConfigSpec.BooleanValue composterSpawnSnails;
        public final ForgeConfigSpec.IntValue composterSnailMaxCount;
        public final ForgeConfigSpec.IntValue composterSnailCheckRange;

        public Common(ForgeConfigSpec.Builder configBuilder) {

            //
            // SNAIL CATEGORY
            //
            configBuilder.push("snails");

            configBuilder.comment("If enabled, composter blocks will have a chance to spawn a Pygmy Snail on random tick if the composter is not empty");
            composterSpawnSnails = configBuilder.define("composterSnails", true);

            configBuilder.comment("If composter snail spawning is enabled, this value will determine how many snails can exist near the composter before spawning will halt");
            composterSnailMaxCount = configBuilder.defineInRange("composterSnailMaxCount", 5, 1, 50);

            configBuilder.comment("If composter snail spawning is enabled, this value will determine the range in blocks that the composter should check for existing snails." +
                    " For instance, a value of 10 will make the composter check in a 10x10 space around itself");
            composterSnailCheckRange = configBuilder.defineInRange("composterSnailCheckRange", 25, 1, 50);

            configBuilder.pop();

            //
            // BIOME CATEGORY
            //
            configBuilder.push("biomes");

            configBuilder.comment("The weights in chance for each of these biomes to generate in the world. A higher value gives a higher probability.");
            this.createBiomeWeights(configBuilder);

            configBuilder.pop();
        }

        public int getBiomeWeight(BiomeRegistryObject biomeRegistryObject) {
            Biome biome = biomeRegistryObject.get();

            if (biome.getRegistryName() == null) {
                Mythscapes.LOGGER.log(Level.ERROR, "Tried to fetch biome weight from biome with null registry name. Why and how did this happen?");
                return 0;
            }
            else {
                String biomeName = biome.getRegistryName().getPath();
                return this.biomeWeights.containsKey(biomeName) ? this.biomeWeights.get(biomeName).get() : 0;
            }
        }

        private void createBiomeWeights(ForgeConfigSpec.Builder configBuilder) {
            for (BiomeRegistryObject registryObject : MythBiomes.BIOMES.getRegistryObjects()) {
                String biomeName = registryObject.getRegistryObject().getId().getPath();
                ForgeConfigSpec.IntValue value = configBuilder.defineInRange(biomeName, registryObject.getDefaultBiomeWeight(), 0, 100);

                this.biomeWeights.put(biomeName, value);
            }
        }

        public boolean getComposterSnails() {
            return composterSpawnSnails.get();
        }

        public int getComposterSnailMaxCount() {
            return composterSnailMaxCount.get();
        }

        public int getComposterSnailCheckRange() {
            return composterSnailCheckRange.get();
        }
    }
}
