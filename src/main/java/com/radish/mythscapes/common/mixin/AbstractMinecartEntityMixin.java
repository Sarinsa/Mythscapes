package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.misc.MixinHooks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeEntityMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin extends Entity implements IForgeEntityMinecart {

    public AbstractMinecartEntityMixin(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Inject(method = "moveAlongTrack", at = @At("HEAD"), cancellable = true)
    public void onMoveMinecartOnRail(BlockPos pos, BlockState state, CallbackInfo callbackInfo) {
        MixinHooks.onMoveMinecartOnRail(pos, state, this.getMinecart(), callbackInfo);
    }
}
