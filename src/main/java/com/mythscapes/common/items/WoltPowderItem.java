package com.mythscapes.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WoltPowderItem extends Item {

    private final boolean golden;

    public WoltPowderItem(Properties properties, boolean golden) {
        super(properties);
        this.golden = golden;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
        ItemStack itemStack = player.getHeldItem(handIn);
        Vector3d motion = player.getMotion();
        double motionY = 1.0d;
        int cooldown = 5;

        if (this.golden) {
            motionY = 1.4d;
            cooldown = 15;
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 20 * 15));
            player.addPotionEffect(new EffectInstance(Effects.SPEED, 20 * 15));
        }
        world.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 1.0f, 0.1f, false);
        player.setMotion(motion.getX(), motionY, motion.getZ());
        player.addStat(Stats.ITEM_USED.get(this));

        if (!player.abilities.isCreativeMode) {
            itemStack.shrink(1);
            player.getCooldownTracker().setCooldown(this, cooldown * 20);
        }
        return ActionResult.resultSuccess(itemStack);
    }
}
