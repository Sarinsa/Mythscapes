package com.radish.mythscapes.common.misc;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class  MythItemGroup {

    public static final ItemGroup MOD_ITEM_GROUP = new ModGroup(Mythscapes.MODID, () -> new ItemStack(MythItems.BLISTERBERRY.get()));

    public static class ModGroup extends ItemGroup {

        private final Supplier<ItemStack> icon;

        public ModGroup(final String name, final Supplier<ItemStack> icon) {
            super(name);
            this.icon = icon;
        }

        @Override
        public ItemStack makeIcon() {
            return this.icon.get();
        }
    }
}

