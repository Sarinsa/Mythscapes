package com.mythscapes.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class WoltPowderBlock extends Block {

    private final boolean golden;

    public WoltPowderBlock(boolean golden, Properties properties) {
        super(properties);
        this.golden = golden;
    }

    private boolean isGolden() {
        return this.golden;
    }

    @Override
    public void onLanded(IBlockReader world, Entity entity) {
        World entityWorld = entity.getEntityWorld();
        BlockPos pos = entity.getPosition();

        if (!entityWorld.isRemote) {
            ((ServerWorld) entityWorld).spawnParticle(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, 14, 0, 0, 0, 0.2f);
        }
        entityWorld.playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.9f, 0.1f, false);

        Vector3d motion = entity.getMotion();
        double motionY = 1.2D;

        if (this.isGolden()) {
            motionY = 1.7D;
        }
        entity.setMotion(new Vector3d(motion.getX(), motionY, motion.getZ()));
    }
}
