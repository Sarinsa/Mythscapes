package com.radish.mythscapes.common.items;

import com.radish.mythscapes.common.network.NetworkHelper;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ModSignItem extends WallOrFloorItem {

    private final int burnTime;

    public ModSignItem(Properties properties, Block floorBlock, Block wallBlock) {
        this(properties, floorBlock, wallBlock, 0);
    }

    public ModSignItem(Properties properties, Block floorBlock, Block wallBlock, int burnTime) {
        super(floorBlock, wallBlock, properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack stack) {
        return this.burnTime;
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        boolean flag = super.updateCustomBlockEntityTag(pos, world, player, stack, state);
        TileEntity tileEntity = world.getBlockEntity(pos);

        if (!world.isClientSide && !flag && player != null && tileEntity != null) {
            NetworkHelper.openSignEditorToClient((ServerPlayerEntity) player, (MythSignTileEntity) tileEntity);
        }
        return flag;
    }
}
