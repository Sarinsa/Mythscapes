package com.radish.mythscapes.common.core.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class MythConfig {

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonPair.getLeft();
        COMMON_SPEC = commonPair.getRight();
    }

    public static class Common {

        public final ForgeConfigSpec.BooleanValue composterSpawnSnails;
        public final ForgeConfigSpec.IntValue composterSnailMaxCount;
        public final ForgeConfigSpec.IntValue composterSnailCheckRange;

        public Common(ForgeConfigSpec.Builder configBuilder) {
            configBuilder.push("snails");

            configBuilder.comment("If enabled, composter blocks will have a chance to spawn a Pygmy Snail on random tick if the composter is not empty");
            composterSpawnSnails = configBuilder.define("composterSnails", true);

            configBuilder.comment("If composter snail spawning is enabled, this value will determine how many snails can exist near the composter before spawning will halt");
            composterSnailMaxCount = configBuilder.defineInRange("composterSnailMaxCount", 5, 1, 50);

            configBuilder.comment("If composter snail spawning is enabled, this value will determine the range in blocks that the composter should check for existing snails." +
                    " For instance, a value of 10 will make the composter check in a 10x10 space around itself");
            composterSnailCheckRange = configBuilder.defineInRange("composterSnailCheckRange", 25, 1, 50);

            configBuilder.pop();
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
