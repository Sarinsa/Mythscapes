package com.mythscapes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public abstract boolean areEyesInFluid(ITag<Fluid> tagIn);

    //@Inject(method = "canSwim", at = @At("HEAD"), cancellable = true)
    public void onCanSwim(CallbackInfoReturnable<Boolean> callbackInfo) {

    }
}
