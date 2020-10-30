package com.mythscapes.client;

import com.mythscapes.common.tags.MythFluidTags;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythEffects;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {

    private static final Supplier<Minecraft> mc = Minecraft::getInstance;
    // Vec3d(R, G, B)
    public static final Vector3d SULFUR_COLORS = new Vector3d(2.2d, 1.85d, 0.01d);

    private static final ResourceLocation PETRIFIED_HEARTS = Mythscapes.resourceLoc("textures/misc/petrified_hearts.png");


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

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void renderPlayerHearts(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if (mc.get().player.isPotionActive(MythEffects.PETRIFIED.get())) {
                mc.get().getTextureManager().bindTexture(PETRIFIED_HEARTS);
            }
        }
    }

    private static void setFogColor(EntityViewRenderEvent.FogColors event, Vector3d colors) {
        event.setRed((float) colors.getX());
        event.setGreen((float) colors.getY());
        event.setBlue((float) colors.getZ());
    }
}
