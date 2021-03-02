package com.radish.mythscapes.common.misc.mixin;

import com.radish.mythscapes.common.core.config.MythConfig;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonMixinHooks {

    public static void onMoveMinecartOnRail(BlockPos pos, BlockState state, AbstractMinecartEntity entity, CallbackInfo callbackInfo) {
        if (state.isIn(MythBlocks.LAUNCHER_RAIL.get()) && state.get(PoweredRailBlock.POWERED)) {
            entity.fallDistance = 0.0F;
            World world = entity.getEntityWorld();
            Vector3d position = entity.getPositionVec();
            Vector3d motion = entity.getMotion();

            entity.setPosition(position.getX(), position.getY() + 0.30D, position.getZ());
            entity.setMotion(motion.getX() * 1.1D, 1.4D, motion.getZ() * 1.1D);

            entity.move(MoverType.SELF, entity.getMotion());

            if (!world.isRemote) {
                ((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, 14, 0, 0, 0, 0.2f);
            }
            world.playSound(null, pos, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.9f, 0.9f);

            callbackInfo.cancel();
        }
    }

    public static void onComposterBlockTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (!MythConfig.COMMON.getComposterSnails())
            return;

        int chanceModifier = state.get(ComposterBlock.LEVEL);

        if (chanceModifier > 0 && rand.nextInt(38) <= chanceModifier) {
            int scanRange = MythConfig.COMMON.getComposterSnailCheckRange();
            int maxSnailCount = MythConfig.COMMON.getComposterSnailMaxCount();
            boolean tooManySnails = world.getEntitiesWithinAABB(SnailEntity.class, new AxisAlignedBB(pos).grow(scanRange)).size() > maxSnailCount;

            if (!tooManySnails) {
                Iterable<BlockPos> scanArea = BlockPos.getAllInBoxMutable(pos.add(3, 2, 2), pos.add(-3, -2, -3));
                List<BlockPos> spawnPositions = new ArrayList<>();

                scanArea.forEach(blockPos -> {
                    if (!world.getBlockState(blockPos).isSolid() && world.getBlockState(blockPos.down()).isSolid() && blockPos.getY() > 50 && world.getLight(blockPos) > 3) {
                        spawnPositions.add(blockPos.toImmutable());
                    }
                });

                if (!spawnPositions.isEmpty()) {
                    BlockPos spawnPos = spawnPositions.get(rand.nextInt(spawnPositions.size()));
                    SnailEntity snail = MythEntities.PYGMY_SNAIL.get().spawn(world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);

                    if (snail != null) {
                        snail.setPosition(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D);

                        for (int i = 0; i < 9; ++i) {
                            world.spawnParticle(
                                    ParticleTypes.CLOUD,
                                    snail.getPosX() + rand.nextGaussian(),
                                    snail.getPosY() + (snail.getHeight() / 2) + (rand.nextGaussian() / 2),
                                    snail.getPosZ() + rand.nextGaussian(),
                                    1,
                                    0.0D,
                                    0.0D,
                                    0.0D,
                                    0);
                        }
                    }
                }
            }
        }
    }
}