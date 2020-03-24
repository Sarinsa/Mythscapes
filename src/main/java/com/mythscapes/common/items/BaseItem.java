package com.mythscapes.common.items;

import com.mythscapes.misc.MythItemGroup;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.DarkOakTreeFeature;

public class BaseItem extends Item {

    public BaseItem(Properties properties) {
        super(properties);
    }

    public BaseItem() {
        super(new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP));
    }
}
