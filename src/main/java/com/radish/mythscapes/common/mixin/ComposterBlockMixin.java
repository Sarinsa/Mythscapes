package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.entities.living.SnailEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        int chanceModifier = state.get(ComposterBlock.LEVEL);

        if (chanceModifier > 0 && rand.nextInt(55) <= chanceModifier) {
            boolean tooManySnails = worldIn.getEntitiesWithinAABB(SnailEntity.class, new AxisAlignedBB(pos).grow(25)).size() > 3;

            if (!tooManySnails) {
                List<BlockPos> spawnPositions = getValidSpawnPositions(pos, worldIn);

                if (!spawnPositions.isEmpty()) {
                    BlockPos spawnPos = spawnPositions.get(rand.nextInt(spawnPositions.size()));
                    SnailEntity snailEntity = new SnailEntity(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D, worldIn);
                    worldIn.addEntity(snailEntity);

                    for (int i = 0; i < 6; ++i) {
                        worldIn.spawnParticle(ParticleTypes.CLOUD, snailEntity.getPosX(), snailEntity.getPosY() + (snailEntity.getHeight() / 2), snailEntity.getPosZ(), 1, 0.0D, 0.0D, 0.0D, 0);
                    }
                }
            }
        }
    }

    private static List<BlockPos> getValidSpawnPositions(BlockPos origin, ServerWorld world) {
        Iterator<BlockPos> scanArea = BlockPos.getAllInBox(new AxisAlignedBB(origin).grow(3, 2, 3)).iterator();
        List<BlockPos> spawnPositions = new ArrayList<>();

        scanArea.forEachRemaining(blockPos -> {
            if (!world.getBlockState(blockPos).isSolid() && world.getBlockState(blockPos.down()).isSolid() && blockPos.getY() > 50 && world.canBlockSeeSky(blockPos)) {
                spawnPositions.add(blockPos.toImmutable());
            }
        });
        return spawnPositions;
    }
}
