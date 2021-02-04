package com.radish.mythscapes.common.tile;

import com.radish.mythscapes.common.register.MythTileEntities;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MythChestTileEntity extends ChestTileEntity {

    /**
     * Using this constructor for when we create our chest tile entity for
     * the chest block item's TESR, as our own chest entity type does not exist by
     * the time items are registered.
     */
    public MythChestTileEntity(TileEntityType<? extends ChestTileEntity> type) {
        super(type);
    }

    public MythChestTileEntity() {
        super(MythTileEntities.CHEST.get());
    }
}
