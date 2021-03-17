package com.radish.mythscapes.common.blocks.fluid;

import com.radish.mythscapes.common.register.MythEffects;
import com.radish.mythscapes.common.tags.MythEntityTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public class LiquidSulphurFluidBlock extends FlowingFluidBlock {

    public LiquidSulphurFluidBlock(Supplier<FlowingFluid> fluidSupplier, Properties properties) {
        super(fluidSupplier, properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.HUNGER, (20 * 10)));
            ((LivingEntity) entity).addEffect(new EffectInstance(MythEffects.VOLATILE.get(), (20 * 10)));

            if (MythEntityTags.DIES_IN_SULFUR.contains(entity.getType())) {
                entity.hurt(DamageSource.GENERIC, 1.0f);
            }
        }
    }

    @Override
    public Vector3d getFogColor(BlockState state, IWorldReader world, BlockPos pos, Entity entity, Vector3d originalColor, float partialTicks) {
        return new Vector3d(2.2D, 1.85D, 0.01D);
    }
}
