package com.mythscapes.common.blocks.plant;

import com.mythscapes.common.tags.MythEntityTags;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythEffects;
import com.mythscapes.register.MythParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.LargeExplosionParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class ChargedDandelionBlock extends MythFlowerBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 1);
    public static final BooleanProperty SPREAD = BooleanProperty.create("spread");

    public ChargedDandelionBlock(Supplier<Effect> effect, int duration, Properties properties) {
        super(effect, duration, properties);
        this.setDefaultState(this.getDefaultState().with(AGE, 0).with(SPREAD, false));
    }

    public boolean isMature(BlockState blockState) {
        return blockState.get(AGE) == 1;
    }

    public boolean shouldSpread(BlockState state) {
        return state.get(SPREAD);
    }

    private boolean attemptSpread(World world, BlockPos pos) {
        List<BlockPos> validPositions = new ArrayList<>();
        BlockPos[] neighbors = this.getPosSpreadTo(pos);

        for (int i = 0; i < neighbors.length; i++) {
            BlockPos neighborPos = neighbors[i];
            BlockState neighborState = world.getBlockState(neighborPos);
            if (neighborState.isAir(world, neighborPos) && this.isValidGround(world.getBlockState(neighborPos.down()), world, neighborPos)) {
                validPositions.add(neighborPos);
            }
        }
        if (!validPositions.isEmpty()) {
            BlockPos spreadPos = validPositions.get(world.rand.nextInt(validPositions.size()));
            world.setBlockState(spreadPos, this.getDefaultState().with(AGE, 0));
        }
        return false;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (MythEntityTags.ELECTRIC.contains(entity.getType()))
            return;
        if (isMature(state)) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addPotionEffect(new EffectInstance(MythEffects.STATIC.get(), (20 * 5)));
                world.setBlockState(pos, this.getDefaultState().with(AGE, 0));
                world.addParticle(MythParticles.STATIC_COTTON_POOF.get(),
                        (pos.getX() + state.getOffset(world, pos).getX()) + 0.5d,
                        pos.getY() + 0.6d,
                        (pos.getZ() + state.getOffset(world, pos).getZ()) + 0.5d,
                        2.0f, 2.0f, 2.0f);
                world.setBlockState(pos, this.getDefaultState().with(SPREAD, true));
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (this.shouldSpread(state)) {
            world.setBlockState(pos, this.getDefaultState().with(SPREAD, this.attemptSpread(world, pos)));
            return;
        }
        if (!this.isMature(state)) {
            if (rand.nextInt(2) == 0) {
                world.setBlockState(pos, state.with(AGE, 1));
            }
        }
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, SPREAD);
    }
}
