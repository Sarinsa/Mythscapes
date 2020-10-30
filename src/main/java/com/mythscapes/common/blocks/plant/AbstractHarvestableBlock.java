package com.mythscapes.common.blocks.plant;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class AbstractHarvestableBlock extends BushBlock implements IGrowable {
    
    private final int maxAge;

    public AbstractHarvestableBlock(int maxAge, Properties properties) {
        super(properties);
        this.maxAge = maxAge;
    }

    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return super.isValidGround(state, worldIn, pos);
    }

    public abstract IItemProvider getSeed();

    public int getMaxAge() {
        return this.maxAge;
    }

    abstract int getAge(BlockState state);

    abstract boolean isMature(BlockState state);

    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(this.getSeed().asItem());
    }
}
