package com.radish.mythscapes.common.misc.mixin;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEffects;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class ClientMixinHooks {

    //TODO
    /**
     * This is a pretty bad and rough solution to the petrified
     * overlay texture. Ideally I think a @ModifyVariable mixin injection
     * would be better here to just change the texture used instead of also
     * returning the RenderType. Chances are there are other mods that inject into this
     * method and changes the RenderType, in which case this would either just not work,
     * or override what said mod is doing.
     */
    public static <T extends LivingEntity, M extends EntityModel<T>> void onGetRenderType(T entity, M entityModel, boolean normal, boolean translucent, boolean glowing, CallbackInfoReturnable<RenderType> callbackInfoReturnable) {
        if (entity.isPotionActive(MythEffects.PETRIFIED.get())) {
            RenderType renderType;
            ResourceLocation texture = Mythscapes.resourceLoc("textures/misc/petrified.png");

            if (translucent) {
                renderType = RenderType.getItemEntityTranslucentCull(texture);
            }
            else if (normal) {
                renderType = entityModel.getRenderType(texture);
            }
            else {
                renderType = glowing ? RenderType.getOutline(texture) : null;
            }
            callbackInfoReturnable.setReturnValue(renderType);
        }
    }
}
