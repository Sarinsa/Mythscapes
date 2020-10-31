package com.mythscapes.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LauncherRailBlock extends PoweredRailBlock {

    public LauncherRailBlock(Properties builder) {
        super(builder, true);
    }

    @Override
    public void onMinecartPass(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
        /*
        if (state.get(POWERED)) {
            if (!world.isRemote) {
                ((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, 14, 0, 0, 0, 0.2f);
            }
            world.playSound(null, pos, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }

         */
    }
}
