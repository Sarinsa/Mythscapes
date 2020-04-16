package com.mythscapes.client;

import com.mythscapes.common.tags.MythFluidTags;
import com.mythscapes.core.Mythscapes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InsideFluidRenderEvents {

    // Vec3d(R, G, B)
    public static final Vec3d SULFUR_COLORS = new Vec3d(2.2d, 1.85d, 0.01d);


    // Change the underwater fog color depending on fluid
    @SubscribeEvent
    public static void onPlayerEyesinLiquid(EntityViewRenderEvent.FogColors event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player.areEyesInFluid(MythFluidTags.SULFUR)) {
            setFogColor(event, SULFUR_COLORS);
        }
    }

    // Change fog density depending on fluid
    @SubscribeEvent
    public static void setFogThickness(EntityViewRenderEvent.FogDensity event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player.areEyesInFluid(MythFluidTags.SULFUR)) {
            event.setDensity(0.3f);
            event.setCanceled(true);
        }
    }

    private static void setFogColor(EntityViewRenderEvent.FogColors event, Vec3d colors) {
        event.setRed((float) colors.getX());
        event.setGreen((float) colors.getY());
        event.setBlue((float) colors.getZ());
    }
}
