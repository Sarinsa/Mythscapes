package com.mythscapes.common.items;

import com.mythscapes.common.entities.projectile.StaticCottonEntity;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class StaticCottonItem extends BaseItem {

    public StaticCottonItem() {
        new Item.Properties().group(MythItems.itemGroup).maxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getHeldItem(hand);

        world.playSound(playerEntity, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            StaticCottonEntity entity = new StaticCottonEntity(playerEntity, world);
            entity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(entity);
        }
        playerEntity.addStat(Stats.ITEM_USED.get(this));

        if (!playerEntity.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }
        return ActionResult.resultSuccess(itemStack);
    }
}
