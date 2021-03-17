package com.radish.mythscapes.common.event;

import com.radish.mythscapes.common.entities.living.DeerEntity;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.fluids.BaseFluid;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityEvents {

    @SubscribeEvent
    public void onGlassBottleClickFluid(PlayerInteractEvent.RightClickItem event) {
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        ItemStack itemStack = event.getItemStack();

        if (!itemStack.isEmpty() && itemStack.getItem() == Items.GLASS_BOTTLE) {
            BlockRayTraceResult result = Item.getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

            if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos pos = result.getBlockPos();

                if (world.getFluidState(pos).getType() instanceof BaseFluid) {
                    BaseFluid fluid = (BaseFluid) world.getFluidState(pos).getType();

                    if (fluid.getBottleItem() != null) {
                        ItemStack liquidBottle = new ItemStack(fluid.getBottleItem());

                        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);

                        if (!player.abilities.instabuild) {
                            itemStack.shrink(1);
                            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                        }
                        if (itemStack.isEmpty()) {
                            player.setItemInHand(event.getHand(), liquidBottle);
                        }
                        else {
                            if (!player.inventory.add(liquidBottle)) {
                                player.drop(liquidBottle, false);
                            }
                        }
                        event.setCancellationResult(ActionResultType.SUCCESS);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onBucketClickSnail(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getTarget() instanceof SnailEntity) {
            SnailEntity snailEntity = (SnailEntity) event.getTarget();

            if (event.getItemStack().getItem() == Items.BUCKET) {
                ItemStack itemStack = event.getItemStack();
                PlayerEntity player = event.getPlayer();

                if (!player.abilities.instabuild) {
                    itemStack.shrink(1);
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
                }
                ItemStack snailBucket = new ItemStack(MythItems.SNAIL_BUCKET.get());
                // Set snail type to itemstack
                CompoundNBT tag = snailBucket.getOrCreateTag();
                tag.putString("SnailType", snailEntity.getSnailType().getName().toString());
                snailBucket.setTag(tag);

                if (itemStack.isEmpty()) {
                    player.setItemInHand(event.getHand(), snailBucket);
                } else {
                    if (!player.inventory.add(snailBucket)) {
                        player.drop(snailBucket, false);
                    }
                }
                snailEntity.remove();
                event.setCancellationResult(ActionResultType.SUCCESS);
            }
        }
    }

    @SubscribeEvent
    public void onAnimalBreed(BabyEntitySpawnEvent event) {
        if (event.getChild() != null && event.getChild().getType() == MythEntities.DEER.get()) {
            ((DeerEntity) event.getParentA()).dropAntlers();
            ((DeerEntity) event.getParentB()).dropAntlers();
        }
    }
}
