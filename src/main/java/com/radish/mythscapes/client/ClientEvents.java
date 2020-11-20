package com.radish.mythscapes.client;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEffects;
import com.radish.mythscapes.common.tags.MythFluidTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Supplier;

public class ClientEvents {

    private final Supplier<Minecraft> mc = Minecraft::getInstance;
    // Vec3d(R, G, B)
    private static final Vector3d SULFUR_COLORS = new Vector3d(2.2d, 1.85d, 0.01d);

    private static final ResourceLocation PETRIFIED_HEARTS = Mythscapes.resourceLoc("textures/misc/petrified_hearts.png");


    // Change the underwater fog color depending on fluid
    @SubscribeEvent
    public void onPlayerEyesinLiquid(EntityViewRenderEvent.FogColors event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player.areEyesInFluid(MythFluidTags.SULFUR)) {
            setFogColor(event, SULFUR_COLORS);
        }
    }

    // Change fog density depending on fluid
    @SubscribeEvent
    public void setFogThickness(EntityViewRenderEvent.FogDensity event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player.areEyesInFluid(MythFluidTags.SULFUR)) {
            event.setDensity(0.5f);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void renderPlayerHearts(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if (mc.get().player.isPotionActive(MythEffects.PETRIFIED.get())) {
                mc.get().getTextureManager().bindTexture(PETRIFIED_HEARTS);
            }
        }
    }

    private void setFogColor(EntityViewRenderEvent.FogColors event, Vector3d colors) {
        event.setRed((float) colors.getX());
        event.setGreen((float) colors.getY());
        event.setBlue((float) colors.getZ());
    }
}
