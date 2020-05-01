package com.mythscapes.common.items;

import com.mythscapes.misc.MythItemGroup;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    protected boolean golden = false;

    public BaseItem(Properties properties) {
        super(properties);
    }

    public BaseItem() {
        super(new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP));
    }

    protected boolean isGolden() {
        return this.golden;
    }
}
