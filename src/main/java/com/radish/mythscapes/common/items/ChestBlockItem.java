package com.radish.mythscapes.common.items;

import com.radish.mythscapes.client.ISTER;
import com.radish.mythscapes.common.blocks.compat.MythChestBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class ChestBlockItem extends BlockItem {

    public ChestBlockItem(MythChestBlock chestBlock, Properties properties) {
        super(chestBlock, properties.setISTER(() -> ISTER.chestISTER(chestBlock)));
    }

    @Override
    public int getBurnTime(ItemStack stack) {
        return 300;
    }
}
