package com.mythscapes.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

public abstract class AbstractHarvestableBlock extends BushBlock implements IGrowable, IForgeBlock {

    public AbstractHarvestableBlock(Properties properties) {
        super(properties);
    }

    public abstract IItemProvider getSeed();

    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(this.getSeed().asItem());
    }
}
