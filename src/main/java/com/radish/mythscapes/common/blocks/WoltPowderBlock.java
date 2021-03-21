package com.radish.mythscapes.common.blocks;

import com.radish.mythscapes.common.tags.MythEntityTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class WoltPowderBlock extends Block {

    private final double launchVelocity;

    public WoltPowderBlock(double launchVelocity, Properties properties) {
        super(properties);
        this.launchVelocity = launchVelocity;
    }

    private double getLaunchVelocity() {
        return this.launchVelocity;
    }

    @Override
    public void updateEntityAfterFallOn(IBlockReader world, Entity entity) {
        World entityWorld = entity.getCommandSenderWorld();
        BlockPos pos = entity.blockPosition();

        if (!isSwooshableEntity(entity))
            return;

        if (!entityWorld.isClientSide) {
            ((ServerWorld) entityWorld).sendParticles(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, 14, 0, 0, 0, 0.2f);
        }
        entityWorld.playSound(null, pos, SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.9f, 0.1f);

        Vector3d motion = entity.getDeltaMovement();
        entity.setDeltaMovement(new Vector3d(motion.x(), this.getLaunchVelocity(), motion.z()));
    }

    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return PathNodeType.DAMAGE_OTHER;
    }

    /**
     * Performs a check to see if an entity can/should be
     * launched upwards from standing on a wolt powder block.
     * If the entity's EntityType is in the swoosh_whitelist tag
     * or is an instance of LivingEntity, the check is passed.
     *
     * Not all entities accept motion being applied to them
     * by just any means, and will end up just standing still
     * while particles and sounds gets spammed. This check
     * exists to avoid that happening. Other mods that have non
     * living entities can also add them to this tag.
     *
     * @param entity - The entity to perform a check on.
     */
    private boolean isSwooshableEntity(Entity entity) {
        return MythEntityTags.SWOOSH_WHITELIST.contains(entity.getType()) || entity instanceof LivingEntity;
    }
}
