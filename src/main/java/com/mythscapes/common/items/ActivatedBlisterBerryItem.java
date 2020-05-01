package com.mythscapes.common.items;

import com.mythscapes.common.entities.projectile.BlisterberryEntity;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ActivatedBlisterBerryItem extends BaseItem {


    public ActivatedBlisterBerryItem() {
        super(new Item.Properties().group(MythItems.itemGroup).maxStackSize(16));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getHeldItem(hand);

        world.playSound(playerEntity, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            BlisterberryEntity blisterBerryEntity = new BlisterberryEntity(playerEntity, world);
            blisterBerryEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(blisterBerryEntity);
        }
        playerEntity.addStat(Stats.ITEM_USED.get(this));

        if (!playerEntity.abilities.isCreativeMode) {
            itemStack.shrink(1);
            playerEntity.getCooldownTracker().setCooldown(this, 200);
        }
        return ActionResult.resultSuccess(itemStack);
    }
}
