package com.radish.mythscapes.common.items;

import com.radish.mythscapes.common.entities.misc.MythBoatEntity;
import com.radish.mythscapes.common.misc.MythItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoatItem;
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

    private static final Predicate<Entity> ENTITY_PREDICATE = EntityPredicates.NO_SPECTATORS.and(Entity::canBeCollidedWith);
    private final MythBoatEntity.Type type;

    public MythBoatItem(MythBoatEntity.Type type) {
        super(new Item.Properties().tab(MythItemGroup.MOD_ITEM_GROUP).stacksTo(1));
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getItemInHand(hand);
        RayTraceResult raytraceresult = getPlayerPOVHitResult(world, playerEntity, RayTraceContext.FluidMode.ANY);

        if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemStack);
        }
        else {
            Vector3d vector3d = playerEntity.getViewVector(1.0F);
            List<Entity> list = world.getEntities(playerEntity, playerEntity.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);

            if (!list.isEmpty()) {
                Vector3d vec = playerEntity.getEyePosition(1.0F);

                for(Entity entity : list) {
                    AxisAlignedBB axisalignedbb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (axisalignedbb.contains(vec)) {
                        return ActionResult.pass(itemStack);
                    }
                }
            }

            if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
                MythBoatEntity boatEntity = new MythBoatEntity(world, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
                boatEntity.setBoatType(this.type);
                boatEntity.yRot = playerEntity.yRot;

                if (!world.noCollision(boatEntity, boatEntity.getBoundingBox().inflate(-0.1D))) {
                    return ActionResult.fail(itemStack);
                }
                else {
                    if (!world.isClientSide) {
                        world.addFreshEntity(boatEntity);
                        if (!playerEntity.abilities.instabuild) {
                            itemStack.shrink(1);
                        }
                    }
                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                    return ActionResult.sidedSuccess(itemStack, world.isClientSide());
                }
            } else {
                return ActionResult.pass(itemStack);
            }
        }
    }
}
