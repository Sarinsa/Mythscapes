package com.radish.mythscapes.api;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

@MythscapesPlugin
public class TestPlugin implements IMythscapesPlugin {

    @Override
    public void register(IRegistryHelper iRegistryHelper) {
        iRegistryHelper.registerSnailType(new BrutusSnail());
    }

    @Override
    public String getPluginName() {
        return "mythscapes_test_plugin";
    }

    private static class BrutusSnail implements ISnailType {

        @Override
        public ResourceLocation getSnailTexture() {
            return Mythscapes.resourceLoc("textures/entity/snails/brutus.png");
        }

        @Override
        public String getName() {
            return "brutus_snail";
        }

        @Override
        public ItemStack getShedDrop(Random random) {
            return new ItemStack(Items.COBBLESTONE);
        }

        @Override
        public Rarity getRarity() {
            return Rarity.RARE;
        }
    }
}
