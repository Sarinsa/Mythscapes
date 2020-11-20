package com.radish.mythscapes.common.event;

import com.radish.mythscapes.common.entities.living.DeerEntity;
import com.radish.mythscapes.common.entities.living.LionEntity;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import com.radish.mythscapes.common.tags.MythFluidTags;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.EvokerEntity;
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
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityEvents {

    @SubscribeEvent
    public void onGlassBottleClickFluid(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = event.getPlayer();
        World world = event.getWorld();
        ItemStack itemStack = player.getHeldItem(event.getHand());

        if (!itemStack.isEmpty() && itemStack.getItem() == Items.GLASS_BOTTLE) {
            BlockRayTraceResult result = Item.rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

            if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos pos = result.getPos();

                if (world.getFluidState(pos).isTagged(MythFluidTags.SULFUR)) {
                    ItemStack liquidBottle = new ItemStack(MythItems.LIQUID_SULPHUR_BOTTLE.get());

                    world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);

                    if (!player.abilities.isCreativeMode) {
                        itemStack.shrink(1);
                        player.addStat(Stats.ITEM_USED.get(itemStack.getItem()));
                    }
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

    @SubscribeEvent
    public void onBucketClickSnail(PlayerInteractEvent.EntityInteractSpecific event) {
        if (event.getTarget() instanceof SnailEntity) {
            SnailEntity snailEntity = (SnailEntity) event.getTarget();

            if (event.getItemStack().getItem() == Items.BUCKET) {
                ItemStack itemStack = event.getItemStack();
                PlayerEntity player = event.getPlayer();

                if (!player.abilities.isCreativeMode) {
                    itemStack.shrink(1);
                    player.addStat(Stats.ITEM_USED.get(itemStack.getItem()));
                }
                ItemStack snailBucket = new ItemStack(MythItems.SNAIL_BUCKET.get());
                // Set snail type to itemstack
                CompoundNBT tag = snailBucket.getOrCreateTag();
                tag.putString("SnailType", snailEntity.getSnailType().getName());
                snailBucket.setTag(tag);

                if (itemStack.isEmpty()) {
                    player.setHeldItem(event.getHand(), snailBucket);
                } else {
                    if (!player.inventory.addItemStackToInventory(snailBucket)) {
                        player.dropItem(snailBucket, false);
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

    // Entity AI goal modifying
    @SubscribeEvent
    public void onLivingSpawn(LivingSpawnEvent event) {
        LivingEntity entity = event.getEntityLiving();

        /*
        if (entity instanceof MonsterEntity) {
            //creatureEntity.goalSelector.addGoal(3, new AvoidEntityWearingBarbarianHoodGoal<>(creatureEntity, PlayerEntity.class, 10.0F, 1.1F, 1.15F));
            ((CreatureEntity)entity).goalSelector.addGoal(3, new AvoidEntityGoal<>((CreatureEntity) entity, FishbonesEntity.class, 6.0F, 1.0D, 1.2D));
        }
         */
        if (entity.getType() == EntityType.VINDICATOR || entity.getType() == EntityType.PILLAGER || entity.getType() == EntityType.ILLUSIONER) {
            AbstractIllagerEntity illagerEntity = (AbstractIllagerEntity) entity;
            illagerEntity.goalSelector.addGoal(3, new AvoidEntityGoal<>(illagerEntity, LionEntity.class, 10.0F, 1.2D, 1.3D));
        }
        else if (entity.getType() == EntityType.EVOKER) {
            EvokerEntity evokerEntity = (EvokerEntity) entity;
            evokerEntity.goalSelector.addGoal(3, new AvoidEntityGoal<>(evokerEntity, LionEntity.class, 7.0f, 0.6D, 1.0D));
            evokerEntity.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(evokerEntity, LionEntity.class, false));
        }
        else if (entity.getType() == EntityType.CREEPER) {
            CreatureEntity creatureEntity = (CreatureEntity) entity;
            creatureEntity.goalSelector.addGoal(3, new AvoidEntityGoal<>(creatureEntity, LionEntity.class, 6.0F, 1.0D, 1.2D));
        }
    }
}
