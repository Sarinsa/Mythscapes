package com.mythscapes.mixin;

import com.mythscapes.register.MythBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.extensions.IForgeEntityMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity implements IForgeEntityMinecart {

    public AbstractMinecartEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    //@Inject(method = "moveMinecartOnRail", at = @At("HEAD"), cancellable = true)
    public void onMoveMinecartOnRail(BlockPos pos, CallbackInfo callbackInfo) {
        BlockState state = this.world.getBlockState(pos);

        if (state.isIn(MythBlocks.LAUNCHER_RAIL.get()) && state.get(PoweredRailBlock.POWERED)) {
            AbstractMinecartEntity minecart = this.getMinecart();

            double speedPenalty = minecart.isBeingRidden() ? 0.75D : 1.0D;
            double maxSpeed = minecart.getMaxSpeedWithRail();
            Vector3d motion = minecart.getMotion();

            minecart.move(MoverType.SELF, new Vector3d(MathHelper.clamp(speedPenalty * motion.x, -maxSpeed, maxSpeed), 1.8D, MathHelper.clamp(speedPenalty * motion.z, -maxSpeed, maxSpeed)));

            if (!this.world.isRemote) {
                ((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, 14, 0, 0, 0, 0.2f);
            }
            world.playSound(null, pos, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.9f, 0.9f);
            callbackInfo.cancel();
        }
    }
}
