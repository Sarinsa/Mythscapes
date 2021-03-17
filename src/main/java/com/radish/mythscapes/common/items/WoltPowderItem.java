package com.radish.mythscapes.common.items;

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

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
        ItemStack itemStack = player.getItemInHand(handIn);
        Vector3d motion = player.getDeltaMovement();
        double motionY = 1.0d;
        int cooldown = 5;

        if (this.golden) {
            motionY = 1.4d;
            cooldown = 15;
            player.addEffect(new EffectInstance(Effects.JUMP, 20 * 15));
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 20 * 15));
        }
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 1.0f, 0.1f);
        player.setDeltaMovement(motion.x(), motionY, motion.z());
        player.awardStat(Stats.ITEM_USED.get(this));

        if (!player.abilities.instabuild) {
            itemStack.shrink(1);
            player.getCooldowns().addCooldown(this, cooldown * 20);
        }
        return ActionResult.success(itemStack);
    }
}
