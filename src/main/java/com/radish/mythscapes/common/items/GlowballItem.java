package com.radish.mythscapes.common.items;

import com.radish.mythscapes.common.entities.projectile.GlowballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GlowballItem extends Item {

    public GlowballItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getItemInHand(hand);

        GlowballEntity entity = new GlowballEntity(playerEntity, world);
        entity.shootFromRotation(playerEntity, playerEntity.xRot, playerEntity.yRot, 0.0F, 1.5F, 1.0F);
        world.addFreshEntity(entity);
        world.playSound(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        playerEntity.awardStat(Stats.ITEM_USED.get(this));

        if (!playerEntity.abilities.instabuild) {
            itemStack.shrink(1);
        }
        return ActionResult.success(itemStack);
    }
}
