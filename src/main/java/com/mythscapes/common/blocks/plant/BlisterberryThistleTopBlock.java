package com.mythscapes.common.blocks.plant;

import com.mythscapes.register.MythBlocks;
import com.mythscapes.register.MythItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlisterberryThistleTopBlock extends BushBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 1);
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 11.0D, 14.0D);

    public BlisterberryThistleTopBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(AGE, 0));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(MythItems.BLISTERBERRY.get());
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() != this && world.getBlockState(pos.down()).getBlock() == MythBlocks.BLISTERBERRY_THISTLE.get()) {
            world.setBlockState(pos.down(), Blocks.AIR.getDefaultState(), 2);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        Block block = state.getBlock();
        return block == MythBlocks.BLISTERBERRY_THISTLE.get();
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
