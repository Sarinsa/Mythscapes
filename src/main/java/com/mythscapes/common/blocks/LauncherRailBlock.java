package com.mythscapes.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class LauncherRailBlock extends PoweredRailBlock {

    public LauncherRailBlock(Properties builder) {
        super(builder, true);
    }

    @Override
    public void onMinecartPass(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
        if (state.get(POWERED)) {
            Vector3d oldMotion = cart.getMotion();
            cart.setCanUseRail(false);
            cart.setMotion(oldMotion.getX(), 1.0D, oldMotion.getZ());
        }
    }
}
