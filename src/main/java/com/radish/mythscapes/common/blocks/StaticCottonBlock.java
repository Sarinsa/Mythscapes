package com.radish.mythscapes.common.blocks;

import com.radish.mythscapes.common.register.MythParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;


public class StaticCottonBlock extends Block {

    public StaticCottonBlock(Properties properties) {
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

    @Override
    public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.causeFallDamage(fallDistance, 0.2F);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        for(Direction direction : Direction.values()) {
            BlockPos blockPos = pos.relative(direction);

            // Spawn falling particles under the block
            if (direction == Direction.DOWN) {
                if (random.nextInt(14) == 0 && world.isEmptyBlock(blockPos) || world.getBlockState(blockPos).getMaterial().isLiquid()) {
                    double x = (double)pos.getX() + (double)random.nextFloat();
                    double y = (double)pos.getY() - 0.05D;
                    double z = (double)pos.getZ() + (double)random.nextFloat();
                    world.addParticle(MythParticles.STATIC_COTTON_FALLING.get(), x, y, z, 0.0D, 0.0D, 0.0D);
                }
            }
            // Spawn normal cotton particles around the sides of the block
            else {
                if (random.nextInt(10) == 0 && !world.getBlockState(blockPos).isSolidRender(world, blockPos)) {
                    Direction.Axis axis = direction.getAxis();
                    double x = (double)pos.getX() + (axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat());
                    double y = (double)pos.getY() + (axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat());
                    double z = (double)pos.getZ() + (axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat());
                    world.addParticle(MythParticles.STATIC_COTTON.get(), x, y, z, 0.1D, 0.1D, 0.1D);
                }
            }
        }
    }
}
