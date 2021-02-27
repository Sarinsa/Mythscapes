package com.radish.mythscapes.common.mixin;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEffects;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
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

    //TODO
    /**
     * This is a pretty bad and rough solution to the petrified
     * overlay texture. Ideally I think a @ModifyVariable mixin injection
     * would be better here to just change the texture used instead of also
     * returning the RenderType. Chances are there are other mods that inject into this
     * method and changes the RenderType, in which case this would either just not work,
     * or override what said mod is doing.
     */
    @Inject(method = "func_230496_a_", at = @At(value = "HEAD"), cancellable = true)
    public void onGetRenderType(T entity, boolean normal, boolean translucent, boolean glowing, CallbackInfoReturnable<RenderType> callbackInfoReturnable) {
        if (entity.isPotionActive(MythEffects.PETRIFIED.get())) {
            RenderType renderType;
            ResourceLocation texture = Mythscapes.resourceLoc("textures/misc/petrified.png");

            if (translucent) {
                renderType = RenderType.getItemEntityTranslucentCull(texture);
            }
            else if (normal) {
                renderType = this.entityModel.getRenderType(texture);
            }
            else {
                renderType = glowing ? RenderType.getOutline(texture) : null;
            }
            callbackInfoReturnable.setReturnValue(renderType);
        }
    }
}
