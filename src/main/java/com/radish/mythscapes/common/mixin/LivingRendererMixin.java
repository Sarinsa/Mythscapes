package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.misc.mixin.ClientMixinHooks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingRenderer.class)
public abstract class LivingRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements IEntityRenderer<T, M> {

    @Shadow
    protected M entityModel;

    protected LivingRendererMixin(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Inject(method = "func_230496_a_", at = @At(value = "HEAD"), cancellable = true)
    public void onGetRenderType(T entity, boolean normal, boolean translucent, boolean glowing, CallbackInfoReturnable<RenderType> callbackInfoReturnable) {
        ClientMixinHooks.onGetRenderType(entity, this.entityModel, normal, translucent, glowing, callbackInfoReturnable);
    }
}
