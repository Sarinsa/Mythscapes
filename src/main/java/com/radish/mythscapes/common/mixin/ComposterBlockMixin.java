package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.misc.MixinHooks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.inventory.ISidedInventoryProvider;
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
        MixinHooks.onComposterBlockTick(state, worldIn, pos, rand);
    }
}
