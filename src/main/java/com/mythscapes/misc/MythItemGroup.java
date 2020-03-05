package com.mythscapes.misc;

import com.mythscapes.core.Mythscapes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class MythItemGroup {

    public static final ItemGroup MOD_ITEM_GROUP = new ModGroup(Mythscapes.MODID, () ->  new ItemStack(Items.NETHER_STAR));

    public static class ModGroup extends ItemGroup {

        private final Supplier<ItemStack> icon;

        public ModGroup(final String name, final Supplier<ItemStack> icon) {
            super(name);
            this.icon = icon;
        }

        @Override
        public ItemStack createIcon() {
            return icon.get();
        }
    }
}

