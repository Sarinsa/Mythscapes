package com.radish.mythscapes.common.items;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEnchantments;
import com.radish.mythscapes.common.register.MythEntities;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
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
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (player.getCooldowns().isOnCooldown(stack.getItem()))
            return ActionResultType.FAIL;

        if (entity instanceof TameableEntity && player.isCrouching()) {
            return ActionResultType.PASS;
        }

        if (MythEntities.BRUSHABLES.containsKey(entity.getClass())) {
            IBrushable brushable = MythEntities.BRUSHABLES.get(entity.getClass());
            World world = entity.getCommandSenderWorld();
            int soothingLevel = EnchantmentHelper.getItemEnchantmentLevel(MythEnchantments.SOOTHING.get(), stack);
            int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack);

            if (brushable.canBrush(entity, world)) {
                ItemStack droppedStack = brushable.itemDropped(entity, world.random, fortuneLevel);

                if (droppedStack == null) {
                    droppedStack = ItemStack.EMPTY;
                    Mythscapes.LOGGER.warn("A brushed entity just tried to drop a null ItemStack... Grrrr... (Should be ItemStack.EMPTY)");
                }
                for (int i = 0; i < 7; i++) {
                    double x = random.nextGaussian() * 0.02D;
                    double y = random.nextGaussian() * 0.02D;
                    double z = random.nextGaussian() * 0.02D;
                    world.addParticle(ParticleTypes.HEART, entity.getRandomX(1.0D), entity.getRandomY() + 0.5D, entity.getRandomZ(1.0D), x, y, z);
                }

                if (!world.isClientSide && !droppedStack.isEmpty()) {
                    entity.spawnAtLocation(droppedStack);
                }

                if (brushable.shouldReceiveRegen(entity))
                    entity.addEffect(new EffectInstance(Effects.REGENERATION, ((soothingLevel + 1) * 20 * 3)));

                brushable.onBrushed(entity, entity.getCommandSenderWorld());
                player.getCommandSenderWorld().playSound(player, entity.blockPosition(), SoundEvents.GRASS_HIT, SoundCategory.PLAYERS, 0.9F, 0.9F);

                if (!player.abilities.instabuild) {
                    player.getCooldowns().addCooldown(this, cooldownTime);
                    player.awardStat(Stats.ITEM_USED.get(this));
                    stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(hand));
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
}
