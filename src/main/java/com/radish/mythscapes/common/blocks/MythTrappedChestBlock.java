package com.radish.mythscapes.common.blocks;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.block.BlockState;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class MythTrappedChestBlock extends MythChestBlock {

    public MythTrappedChestBlock(Properties builder, Supplier<TileEntityType<? extends ChestTileEntity>> tileEntityTypeIn, String woodName) {
        super(builder, tileEntityTypeIn, woodName);
    }

    @Override
    protected ResourceLocation[] create(String woodName) {
        return new ResourceLocation[] {
                Mythscapes.resourceLoc("textures/tile/chests/" + woodName + "/" + woodName + "_trapped.png"),
                Mythscapes.resourceLoc("textures/tile/chests/" + woodName + "/" + woodName + "_trapped_left.png"),
                Mythscapes.resourceLoc("textures/tile/chests/" + woodName + "/" + woodName + "_trapped_right.png")
        };
    }

    @Override
    protected Stat<ResourceLocation> getOpenStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return MathHelper.clamp(ChestTileEntity.getPlayersUsing(blockAccess, pos), 0, 15);
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return side == Direction.UP ? blockState.getWeakPower(blockAccess, pos, side) : 0;
    }
}
