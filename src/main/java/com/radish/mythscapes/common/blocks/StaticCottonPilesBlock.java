package com.radish.mythscapes.common.blocks;

import com.radish.mythscapes.common.register.MythParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class StaticCottonPilesBlock extends SnowBlock {

    public StaticCottonPilesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 30;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        for(Direction direction : Direction.values()) {
            BlockPos blockpos = pos.offset(direction);

            if (random.nextInt(10) == 0 && !world.getBlockState(blockpos).isOpaqueCube(world, blockpos)) {
                Direction.Axis axis = direction.getAxis();
                double x = (double)pos.getX() + (axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getXOffset() : (double)random.nextFloat());
                double y = direction == Direction.UP
                        ? (double)pos.getY() + (((state.get(LAYERS) * 2) + (double)random.nextFloat()) / 16)
                        : (double)pos.getY() + (0.05D + (double)random.nextInt(state.get(LAYERS) * 2) / 16);
                double z = (double)pos.getZ() + (axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getZOffset() : (double)random.nextFloat());
                world.addParticle(MythParticles.STATIC_COTTON.get(), x, y, z, 0.1D, 0.1D, 0.1D);
            }
        }
    }
}
