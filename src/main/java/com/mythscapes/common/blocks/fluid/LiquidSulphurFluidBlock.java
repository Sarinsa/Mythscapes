package com.mythscapes.common.blocks.fluid;

import com.mythscapes.common.tags.MythEntityTags;
import com.mythscapes.register.MythEffects;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
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
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HUNGER, (20 * 10)));
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(MythEffects.VOLATILE.get(), (20 * 10)));

            if (MythEntityTags.DIES_IN_SULFUR.contains(entity.getType())) {
                entity.attackEntityFrom(DamageSource.GENERIC, 1.0f);
            }
        }
    }
}
