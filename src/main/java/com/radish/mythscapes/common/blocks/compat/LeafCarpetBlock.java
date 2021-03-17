package com.radish.mythscapes.common.blocks.compat;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

@SuppressWarnings("deprecation")
public class LeafCarpetBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public LeafCarpetBlock(Properties properties) {
        super(properties);
    }

    public LeafCarpetBlock() {
        super(AbstractBlock.Properties.of(Material.CLOTH_DECORATION)
                .noOcclusion()
                .instabreak()
                .sound(SoundType.GRASS)
                .harvestTool(ToolType.HOE)
        );
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos blockPos, ISelectionContext selectionContext) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
