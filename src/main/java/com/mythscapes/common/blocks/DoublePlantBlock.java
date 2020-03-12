package com.mythscapes.common.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

/**
 * Only used for the blister berry bush thus far.
 */
public class DoublePlantBlock extends BushBlock implements IForgeBlock {

    private final Block mainPlantBlock;

    public DoublePlantBlock(Properties properties, Block mainPlantBlock) {
        super(properties);
        this.mainPlantBlock = mainPlantBlock;
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return this.getMainPlantBlock().getPickBlock(state, target, world, pos, player);
    }

    @Override
    public String getTranslationKey() {
        return this.getMainPlantBlock().getTranslationKey();
    }

    public Block getMainPlantBlock() {
        return this.mainPlantBlock;
    }
}
