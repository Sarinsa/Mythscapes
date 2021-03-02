package com.radish.mythscapes.common.blocks.compat;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.tile.MythChestTileEntity;
import net.minecraft.block.ChestBlock;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

public class MythChestBlock extends ChestBlock {

    private final ResourceLocation[] TEXTURES;

    public MythChestBlock(Properties builder, Supplier<TileEntityType<? extends ChestTileEntity>> tileEntityTypeIn, String woodName) {
        super(builder, tileEntityTypeIn);
        TEXTURES = create(woodName);
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world) {
        return new MythChestTileEntity();
    }

    protected ResourceLocation[] create(String woodName) {
        return new ResourceLocation[] {
                Mythscapes.resourceLoc("textures/tile/chests/" + woodName + "/" + woodName + ".png"),
                Mythscapes.resourceLoc("textures/tile/chests/" + woodName + "/" + woodName + "_left.png"),
                Mythscapes.resourceLoc("textures/tile/chests/" + woodName + "/" + woodName + "_right.png")
        };
    }

    public ResourceLocation getChestTexture(ChestType chestType) {
        switch (chestType) {
            default:
            case SINGLE:
                return TEXTURES[0];
            case LEFT:
                return TEXTURES[1];
            case RIGHT:
                return TEXTURES[2];
        }
    }
}
