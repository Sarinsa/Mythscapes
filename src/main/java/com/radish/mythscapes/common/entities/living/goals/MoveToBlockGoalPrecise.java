package com.radish.mythscapes.common.entities.living.goals;

import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;

/**
 * Uh, don't really know if this will be used
 * or finished.
 */
public abstract class MoveToBlockGoalPrecise<T extends CreatureEntity> extends Goal {

    protected final T creature;
    public final double movementSpeed;
    /** Controls task execution delay */
    protected int runDelay;
    protected int timeoutCounter;
    private int maxStayTicks;
    /** Block to move to */
    protected BlockPos destinationBlock = BlockPos.ZERO;
    private boolean isAtDestination;
    private final int searchLength;
    private final int field_203113_j;
    protected int field_203112_e;

    private final double xOffset;
    private final double yOffset;
    private final double zOffset;

    public MoveToBlockGoalPrecise(T creature, double speedIn, int length, double xOffset, double yOffset, double zOffset) {
        this(creature, speedIn, length, 1, xOffset, yOffset, zOffset);
    }

    public MoveToBlockGoalPrecise(T creatureIn, double speed, int length, int p_i48796_5_, double xOffset, double yOffset, double zOffset) {
        this.creature = creatureIn;
        this.movementSpeed = speed;
        this.searchLength = length;
        this.field_203112_e = 0;
        this.field_203113_j = p_i48796_5_;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));

        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean shouldExecute() {
        if (this.runDelay > 0) {
            --this.runDelay;
            return false;
        } else {
            this.runDelay = this.getRunDelay(this.creature);
            return this.searchForDestination();
        }
    }

    protected int getRunDelay(CreatureEntity creatureIn) {
        return 200 + creatureIn.getRNG().nextInt(200);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return this.timeoutCounter >= -this.maxStayTicks && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.creature.world, this.destinationBlock);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.moveTowardsBlock();
        this.timeoutCounter = 0;
        this.maxStayTicks = this.creature.getRNG().nextInt(this.creature.getRNG().nextInt(1200) + 1200) + 1200;
    }

    protected void moveTowardsBlock() {
        if (!this.creature.getNavigator().tryMoveToXYZ(
                (double)((float)this.destinationBlock.getX()) + this.xOffset,
                this.destinationBlock.getY() + this.yOffset,
                (double)((float)this.destinationBlock.getZ()) + this.zOffset,
                this.movementSpeed)) {

            Mythscapes.LOGGER.info("Cannot pathfind to center");
        }
    }


    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        BlockPos destination = this.destinationBlock.add(this.xOffset, this.yOffset, this.zOffset);
        Vector3d entityPos = this.creature.getPositionVec();

        double distanceFromDestination = destination.distanceSq(entityPos.x, entityPos.y, entityPos.z, false);

        Mythscapes.LOGGER.info("Distance: " + distanceFromDestination);

        if (distanceFromDestination > 0.1D) {
            this.isAtDestination = false;
            ++this.timeoutCounter;
            if (this.shouldMove()) {
                this.moveTowardsBlock();
            }
        } else {
            this.isAtDestination = true;
            --this.timeoutCounter;
        }
    }

    public boolean shouldMove() {
        return this.timeoutCounter % 40 == 0;
    }

    /**
     * @return If the entity is standing inside
     *         the target position block.
     */
    protected boolean isAtDestination() {
        return this.isAtDestination;
    }

    protected boolean searchForDestination() {
        int i = this.searchLength;
        int j = this.field_203113_j;
        BlockPos blockpos = this.creature.getPosition();
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int k = this.field_203112_e; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutable.setAndOffset(blockpos, i1, k - 1, j1);
                        if (this.creature.isWithinHomeDistanceFromPosition(blockpos$mutable) && this.shouldMoveTo(this.creature.world, blockpos$mutable)) {
                            this.destinationBlock = blockpos$mutable;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Return true to set given position as destination
     */
    protected abstract boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos);
}
