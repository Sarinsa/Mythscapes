package com.mythscapes.event;

import com.mythscapes.common.tags.MythFluidTags;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.Util;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent
    public static void onGlassBottleClickFluid(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        ItemStack itemStack = player.getHeldItem(event.getHand());

        if (!itemStack.isEmpty() && itemStack.getItem() == Items.GLASS_BOTTLE) {
            RayTraceResult result = Item.rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

            if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos pos = ((BlockRayTraceResult)result).getPos();

                if (world.getFluidState(pos).isTagged(MythFluidTags.SULFUR)) {
                    ItemStack liquidBottle = new ItemStack(MythItems.LIQUID_SULPHUR_BOTTLE.get());

                    world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
                    itemStack.shrink(1);
                    player.addStat(Stats.ITEM_USED.get(itemStack.getItem()));

                    if (itemStack.isEmpty()) {
                        player.setHeldItem(event.getHand(), liquidBottle);
                    }
                    else {
                        if (!player.inventory.addItemStackToInventory(liquidBottle)) {
                            player.dropItem(liquidBottle, false);
                        }
                    }
                    event.setCancellationResult(ActionResultType.SUCCESS);
                    event.setCanceled(true);
                }
            }
        }
    }
}
