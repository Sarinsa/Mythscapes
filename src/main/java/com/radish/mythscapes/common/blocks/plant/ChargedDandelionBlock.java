package com.radish.mythscapes.common.blocks.plant;

import com.radish.mythscapes.common.register.MythEffects;
import com.radish.mythscapes.common.register.MythParticles;
import com.radish.mythscapes.common.tags.MythEntityTags;
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

    public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
    public static final BooleanProperty SPREAD = BooleanProperty.create("spread");

    public ChargedDandelionBlock(Supplier<Effect> effect, int duration, Properties properties) {
        super(effect, duration, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(SPREAD, false));
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
        return blockState.getValue(AGE) == 5;
    }

    private int getAge(BlockState state) {
        return state.getValue(AGE);
    }

    public boolean shouldSpread(BlockState state) {
        return state.getValue(SPREAD);
    }

    private boolean attemptSpread(World world, BlockPos pos) {
        final List<BlockPos> validPositions = new ArrayList<>();

        for (BlockPos neighborPos : this.getPosSpreadTo(pos)) {
            BlockState neighborState = world.getBlockState(neighborPos);
            if (neighborState.getBlock().isAir(neighborState, world, neighborPos) && this.mayPlaceOn(world.getBlockState(neighborPos.below()), world, neighborPos)) {
                validPositions.add(neighborPos);
            }
        }
        if (!validPositions.isEmpty()) {
            BlockPos spreadPos = validPositions.get(world.random.nextInt(validPositions.size()));
            world.setBlockAndUpdate(spreadPos, this.defaultBlockState().setValue(AGE, 0));
        }
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isMature(state)) {
            if (entity instanceof LivingEntity) {
                if (MythEntityTags.ELECTRIC.contains(entity.getType())) {
                    ((LivingEntity) entity).addEffect(new EffectInstance(MythEffects.STATIC.get(), (20 * 5)));
                }
                world.setBlockAndUpdate(pos, this.defaultBlockState().setValue(AGE, 0).setValue(SPREAD, true));

                if (!world.isClientSide) {
                    ((ServerWorld) world).sendParticles(MythParticles.STATIC_COTTON_POOF.get(),
                            (pos.getX() + state.getOffset(world, pos).x()) + 0.5D,
                            pos.getY() + 0.6D,
                            (pos.getZ() + state.getOffset(world, pos).z()) + 0.5D,
                            1, 0.0F, 0.0F, 0.0F, 0.0F);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (this.shouldSpread(state)) {
            world.setBlockAndUpdate(pos, this.defaultBlockState().setValue(SPREAD, this.attemptSpread(world, pos)));
            return;
        }
        if (!this.isMature(state)) {
            if (rand.nextInt(15) == 0) {
                world.setBlockAndUpdate(pos, state.setValue(AGE, this.getAge(state) + 1));
            }
        }
    }

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, SPREAD);
    }
}
