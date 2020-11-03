package com.mythscapes.common.items;

import com.mythscapes.api.IBrushable;
import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythEnchantments;
import com.mythscapes.register.MythEntities;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BrushItem extends Item {

    // 4 minute cooldown
    private static final int cooldownTime = 4 * 20 * 60;

    public BrushItem(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("all")
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (player.getCooldownTracker().hasCooldown(stack.getItem()))
            return ActionResultType.FAIL;

        if (entity instanceof TameableEntity) {
            if (player.isSneaking())
                return ActionResultType.PASS;
        }
        int soothingLevel = EnchantmentHelper.getEnchantmentLevel(MythEnchantments.SOOTHING.get(), stack);
        int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);

        if (MythEntities.BRUSHABLES.containsKey(entity.getType())) {
            IBrushable brushable = MythEntities.BRUSHABLES.get(entity.getType());
            World world = entity.getEntityWorld();

            if (brushable.canBrush(entity, world)) {
                ItemStack droppedStack = brushable.itemDropped(entity, fortuneLevel);

                if (droppedStack == null) {
                    droppedStack = ItemStack.EMPTY;
                    Mythscapes.LOGGER.warn("A brushed entity just tried to drop a null itemstack... Grrrr... (Should be ItemStack.EMPTY)");
                }

                if (!world.isRemote && !brushable.itemDropped(entity, fortuneLevel).isEmpty()) {
                    entity.entityDropItem(brushable.itemDropped(entity, fortuneLevel));
                }
                brushable.onBrushed(entity, entity.getEntityWorld());
                entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, ((soothingLevel + 1) * 20 * 2)));
                player.getEntityWorld().playSound(player, entity.getPosition(), SoundEvents.BLOCK_GRASS_HIT, SoundCategory.PLAYERS, 0.9f, 0.9f);

                if (!player.abilities.isCreativeMode) {
                    player.getCooldownTracker().setCooldown(this, cooldownTime);
                    stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
                }
                return ActionResultType.SUCCESS;
            }
            else {
                return ActionResultType.FAIL;
            }
        }
        else {
            return ActionResultType.FAIL;
        }
    }
}
