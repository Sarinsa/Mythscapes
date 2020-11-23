package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.register.MythEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.SpawnReason;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin extends Block implements ISidedInventoryProvider {

    public ComposterBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void onConstructed(AbstractBlock.Properties properties, CallbackInfo callbackInfo) {
        this.ticksRandomly = true;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand, CallbackInfo callbackInfo) {
        Mythscapes.LOGGER.info("Composter ticked!");
        int chanceModifier = state.get(ComposterBlock.LEVEL);

        if (chanceModifier > 0 && rand.nextInt(115) <= chanceModifier) {
            Mythscapes.LOGGER.info("Composter tryinna spawn snail.");
            boolean trySpawn = worldIn.getEntitiesWithinAABB(SnailEntity.class, new AxisAlignedBB(pos).expand(15, 15, 15)).size() < 4;
            BlockPos spawnPos = pos.offset(Direction.byHorizontalIndex(rand.nextInt(4)));

            if (trySpawn && SnailEntity.canSnailSpawn(MythEntities.PYGMY_SNAIL.get(), worldIn, SpawnReason.NATURAL, spawnPos, rand)) {
                Mythscapes.LOGGER.info("Snail can spawn here!");
                SnailEntity snailEntity = new SnailEntity(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), worldIn);
                worldIn.addEntity(snailEntity);

                for(int i = 0; i < 8; ++i) {
                    worldIn.spawnParticle(ParticleTypes.CLOUD, spawnPos.getX() + rand.nextDouble(), spawnPos.getY() + 0.4D, spawnPos.getZ() + rand.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 0);
                }
            }
        }
    }
}
