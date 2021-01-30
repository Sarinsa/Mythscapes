package com.radish.mythscapes.common.items;

import com.radish.mythscapes.common.network.NetworkHelper;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ModSignItem extends WallOrFloorItem {

    public ModSignItem(Properties properties, Block floorBlock, Block wallBlock) {
        super(floorBlock, wallBlock, properties);
    }

    @Override
    protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        boolean flag = super.onBlockPlaced(pos, worldIn, player, stack, state);
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (!worldIn.isRemote && !flag && player != null && tileEntity != null) {
            NetworkHelper.openSignEditorToClient((ServerPlayerEntity) player, (MythSignTileEntity) tileEntity);
        }
        return flag;
    }
}
