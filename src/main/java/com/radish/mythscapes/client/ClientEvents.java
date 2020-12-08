package com.radish.mythscapes.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEffects;
import com.radish.mythscapes.common.tags.MythFluidTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Supplier;

public class ClientEvents {

    private final Supplier<Minecraft> mc = Minecraft::getInstance;

    private static final Vector3d SULFUR_COLORS = new Vector3d(2.2d, 1.85d, 0.01d);

    private static final ResourceLocation PETRIFIED_HEARTS = Mythscapes.resourceLoc("textures/misc/petrified_hearts.png");
    private static final ResourceLocation PETRIFIED_ENTITY = Mythscapes.resourceLoc("textures/misc/petrified.png");

    // Change fog density depending on fluid
    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        ClientPlayerEntity playerEntity = mc.get().player;

        Mythscapes.LOGGER.info(playerEntity.areEyesInFluid(MythFluidTags.SULFUR));

        if (playerEntity.areEyesInFluid(MythFluidTags.SULFUR)) {
            event.setDensity(0.5f);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onFogColor(EntityViewRenderEvent.FogColors event) {
        ClientPlayerEntity playerEntity = mc.get().player;

        if (playerEntity.areEyesInFluid(MythFluidTags.SULFUR)) {
            setFogColors(event, SULFUR_COLORS);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onHeartsRender(RenderGameOverlayEvent.Pre event) {
        ClientPlayerEntity playerEntity = mc.get().player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if (playerEntity.isPotionActive(MythEffects.PETRIFIED.get())) {
                mc.get().getTextureManager().bindTexture(PETRIFIED_HEARTS);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onRenderLiving(RenderLivingEvent.Pre<?, ?> event) {
        if (event.getEntity().isPotionActive(MythEffects.PETRIFIED.get())) {
            LivingEntity entity = event.getEntity();
            MatrixStack matrixStack = event.getMatrixStack();
            double x = entity.getPosX();
            double y = entity.getPosY();
            double z = entity.getPosZ();
            float rotationYaw = entity.getRotationYawHead();

            Minecraft.getInstance().getTextureManager().bindTexture(PETRIFIED_ENTITY);
        }
    }

    private static void setFogColors(EntityViewRenderEvent.FogColors event, Vector3d colors) {
        event.setRed((float) colors.getX());
        event.setGreen((float) colors.getY());
        event.setBlue((float) colors.getZ());
    }
}
