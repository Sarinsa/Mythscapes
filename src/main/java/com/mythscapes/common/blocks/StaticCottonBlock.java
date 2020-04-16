package com.mythscapes.common.blocks;

import com.mythscapes.register.MythParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class StaticCottonBlock extends Block {

    public StaticCottonBlock(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        for(Direction direction : Direction.values()) {
            BlockPos blockpos = pos.offset(direction);

            // Spawn falling particles under the block
            if (direction == Direction.DOWN) {
                if (random.nextInt(14) == 0 && world.isAirBlock(blockpos) || world.getBlockState(blockpos).getMaterial().isLiquid()) {
                    double x = (double)pos.getX() + (double)random.nextFloat();
                    double y = (double)pos.getY() - 0.05D;
                    double z = (double)pos.getZ() + (double)random.nextFloat();
                    world.addParticle(MythParticles.STATIC_COTTON_FALLING.get(), x, y, z, 0.0D, 0.0D, 0.0D);
                }
            }
            // Spawn normal cotton particles around the sides of the block
            else {
                if (random.nextInt(10) == 0 && !world.getBlockState(blockpos).isOpaqueCube(world, blockpos)) {
                    Direction.Axis axis = direction.getAxis();
                    double x = (double)pos.getX() + (axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getXOffset() : (double)random.nextFloat());
                    double y = (double)pos.getY() + (axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getYOffset() : (double)random.nextFloat());
                    double z = (double)pos.getZ() + (axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getZOffset() : (double)random.nextFloat());
                    world.addParticle(MythParticles.STATIC_COTTON.get(), x, y, z, 0.1D, 0.1D, 0.1D);
                }
            }
        }
    }
}
