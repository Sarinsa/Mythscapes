package com.mythscapes.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractHarvestableBlock extends BushBlock implements IGrowable {

    private final boolean hasCollision;

    public AbstractHarvestableBlock(Properties properties, boolean collision) {
        super(properties);
        this.hasCollision = collision;
    }

    public abstract IItemProvider getSeed();

    public boolean hasCollision() {
        return this.hasCollision;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.hasCollision ? super.getShape(state, worldIn, pos, context) : VoxelShapes.empty();
    }

    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(this.getSeed().asItem());
    }
}
