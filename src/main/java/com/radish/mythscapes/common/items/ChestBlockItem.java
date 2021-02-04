package com.radish.mythscapes.common.items;

import com.radish.mythscapes.client.ISTER;
import com.radish.mythscapes.common.blocks.MythChestBlock;
import net.minecraft.item.BlockItem;

public class ChestBlockItem extends BlockItem {

    public ChestBlockItem(MythChestBlock chestBlock, Properties properties) {
        super(chestBlock, properties.setISTER(() -> ISTER.chestISTER(chestBlock)));
    }
}
