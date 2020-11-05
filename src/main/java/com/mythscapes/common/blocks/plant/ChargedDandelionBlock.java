package com.mythscapes.common.blocks.plant;

import com.mythscapes.common.tags.MythEntityTags;
import com.mythscapes.register.MythEffects;
import com.mythscapes.register.MythParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class ChargedDandelionBlock extends ModFlowerBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
    public static final BooleanProperty SPREAD = BooleanProperty.create("spread");

    public ChargedDandelionBlock(Supplier<Effect> effect, int duration, Properties properties) {
        super(effect, duration, properties);
        this.setDefaultState(this.getDefaultState().with(AGE, 0).with(SPREAD, false));
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 100;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return 60;
    }

    public boolean isMature(BlockState blockState) {
        return blockState.get(AGE) == 5;
    }

    private int getAge(BlockState state) {
        return state.get(AGE);
    }

    public boolean shouldSpread(BlockState state) {
        return state.get(SPREAD);
    }

    private boolean attemptSpread(World world, BlockPos pos) {
        final List<BlockPos> validPositions = new ArrayList<>();

        for (BlockPos neighborPos : this.getPosSpreadTo(pos)) {
            BlockState neighborState = world.getBlockState(neighborPos);
            if (neighborState.getBlock().isAir(neighborState, world, neighborPos) && this.isValidGround(world.getBlockState(neighborPos.down()), world, neighborPos)) {
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
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isMature(state)) {
            if (entity instanceof LivingEntity) {
                if (MythEntityTags.ELECTRIC.contains(entity.getType())) {
                    ((LivingEntity) entity).addPotionEffect(new EffectInstance(MythEffects.STATIC.get(), (20 * 5)));
                }
                world.setBlockState(pos, this.getDefaultState().with(AGE, 0).with(SPREAD, true));

                if (!world.isRemote) {
                    ((ServerWorld) world).spawnParticle(MythParticles.STATIC_COTTON_POOF.get(),
                            (pos.getX() + state.getOffset(world, pos).getX()) + 0.5d,
                            pos.getY() + 0.6d,
                            (pos.getZ() + state.getOffset(world, pos).getZ()) + 0.5d,
                            1, 0.0f, 0.0f, 0.0f, 0.0f);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (this.shouldSpread(state)) {
            world.setBlockState(pos, this.getDefaultState().with(SPREAD, this.attemptSpread(world, pos)));
            return;
        }
        if (!this.isMature(state)) {
            if (rand.nextInt(15) == 0) {
                world.setBlockState(pos, state.with(AGE, this.getAge(state) + 1));
            }
        }
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, SPREAD);
    }
}
