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
        if (state.is(MythBlocks.LAUNCHER_RAIL.get()) && state.getValue(PoweredRailBlock.POWERED)) {
            entity.fallDistance = 0.0F;
            World world = entity.getCommandSenderWorld();
            Vector3d position = entity.position();
            Vector3d motion = entity.getDeltaMovement();

            entity.setPos(position.x(), position.y() + 0.30D, position.z());
            entity.setDeltaMovement(motion.x() * 1.1D, 1.2D, motion.z() * 1.1D);

            entity.move(MoverType.SELF, entity.getDeltaMovement());

            if (!world.isClientSide) {
                ((ServerWorld) world).sendParticles(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, 14, 0, 0, 0, 0.2f);
            }
            world.playSound(null, pos, SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.9f, 0.9f);

            callbackInfo.cancel();
        }
    }

    public static void onComposterBlockTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (!MythConfig.COMMON.getComposterSnails())
            return;

        int chanceModifier = state.getValue(ComposterBlock.LEVEL);

        if (chanceModifier > 0 && rand.nextInt(38) <= chanceModifier) {
            int scanRange = MythConfig.COMMON.getComposterSnailCheckRange();
            int maxSnailCount = MythConfig.COMMON.getComposterSnailMaxCount();
            boolean tooManySnails = world.getEntitiesOfClass(SnailEntity.class, new AxisAlignedBB(pos).inflate(scanRange)).size() > maxSnailCount;

            if (!tooManySnails) {
                Iterable<BlockPos> scanArea = BlockPos.betweenClosed(pos.offset(3, 2, 2), pos.offset(-3, -2, -3));
                List<BlockPos> spawnPositions = new ArrayList<>();

                scanArea.forEach(blockPos -> {
                    if (!world.getBlockState(blockPos).canOcclude() && world.getBlockState(blockPos.below()).canOcclude() && blockPos.getY() > 50 && world.getMaxLocalRawBrightness(blockPos) > 3) {
                        spawnPositions.add(blockPos.immutable());
                    }
                });

                if (!spawnPositions.isEmpty()) {
                    BlockPos spawnPos = spawnPositions.get(rand.nextInt(spawnPositions.size()));
                    SnailEntity snail = MythEntities.PYGMY_SNAIL.get().spawn(world, null, null, null, spawnPos, SpawnReason.EVENT, false, false);

                    if (snail != null) {
                        snail.setPos(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D);

                        for (int i = 0; i < 9; ++i) {
                            world.sendParticles(
                                    ParticleTypes.CLOUD,
                                    snail.getX() + rand.nextGaussian(),
                                    snail.getY() + (snail.getBbHeight() / 2) + (rand.nextGaussian() / 2),
                                    snail.getZ() + rand.nextGaussian(),
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
