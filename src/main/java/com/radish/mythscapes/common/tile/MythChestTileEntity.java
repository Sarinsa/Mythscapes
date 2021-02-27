package com.radish.mythscapes.common.tile;

import com.radish.mythscapes.common.register.MythTileEntities;
import net.minecraft.tileentity.ChestTileEntity;

public class MythChestTileEntity extends ChestTileEntity {

    public MythChestTileEntity() {
        super(MythTileEntities.CHEST.get());
    }
}
