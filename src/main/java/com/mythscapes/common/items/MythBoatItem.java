package com.mythscapes.common.items;

import com.mythscapes.common.entities.misc.MythBoatEntity;
import com.mythscapes.misc.MythItemGroup;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class MythBoatItem extends Item {

    private static Predicate<Entity> entityPredicate = EntityPredicates.NOT_SPECTATING.and(Entity::canBeCollidedWith);
    private MythBoatEntity.Type type;

    public MythBoatItem(MythBoatEntity.Type type) {
        super(new Item.Properties().group(MythItemGroup.MOD_ITEM_GROUP).maxStackSize(1));
        this.type = type;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getHeldItem(hand);
        RayTraceResult result = rayTrace(world, playerEntity, RayTraceContext.FluidMode.ANY);

        if (result.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.resultPass(itemStack);
        }
        else {
            Vector3d look = playerEntity.getLook(1.0F);
            List<Entity> entities = world.getEntitiesInAABBexcluding(playerEntity, playerEntity.getBoundingBox().expand(look.scale(5.0D)).grow(1.0D), entityPredicate);

            if (!entities.isEmpty()) {
                Vector3d eyePosition = playerEntity.getEyePosition(1.0F);
                for (Entity nextEntity : entities) {
                    AxisAlignedBB axisAlignedBB = nextEntity.getBoundingBox().grow(nextEntity.getCollisionBorderSize());
                    if (axisAlignedBB.contains(eyePosition)) {
                        return ActionResult.resultPass(itemStack);
                    }
                }
            }
            if (result.getType() == RayTraceResult.Type.BLOCK) {
                MythBoatEntity boatEntity = new MythBoatEntity(world, result.getHitVec().x, result.getHitVec().y, result.getHitVec().z);
                boatEntity.setBoatType(this.type);
                boatEntity.rotationYaw = playerEntity.rotationYaw;

                if (!world.hasNoCollisions(boatEntity, boatEntity.getBoundingBox().grow(-0.1D))) {
                    return ActionResult.resultFail(itemStack);
                }
                else {
                    if (!world.isRemote) {
                        world.addEntity(boatEntity);
                        if (!playerEntity.abilities.isCreativeMode) {
                            itemStack.shrink(1);
                        }
                    }
                    playerEntity.addStat(Stats.ITEM_USED.get(this));
                    return ActionResult.resultSuccess(itemStack);
                }
            }
            else {
                return ActionResult.resultPass(itemStack);
            }
        }
    }
}
