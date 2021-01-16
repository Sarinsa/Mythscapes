package com.radish.mythscapes.common.items;

import com.radish.mythscapes.api.IBrushable;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythEnchantments;
import com.radish.mythscapes.common.register.MythEntities;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
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

        if (MythEntities.BRUSHABLES.containsKey(entity.getClass())) {
            IBrushable brushable = MythEntities.BRUSHABLES.get(entity.getClass());
            World world = entity.getEntityWorld();

            if (brushable.canBrush(entity, world)) {
                ItemStack droppedStack = brushable.itemDropped(entity, world.rand, fortuneLevel);

                if (droppedStack == null) {
                    droppedStack = ItemStack.EMPTY;
                    Mythscapes.LOGGER.warn("A brushed entity just tried to drop a null ItemStack... Grrrr... (Should be ItemStack.EMPTY)");
                }
                for (int i = 0; i < 7; i++) {
                    double x = this.random.nextGaussian() * 0.02D;
                    double y = this.random.nextGaussian() * 0.02D;
                    double z = this.random.nextGaussian() * 0.02D;
                    world.addParticle(ParticleTypes.HEART, entity.getPosXRandom(1.0D), entity.getPosYRandom() + 0.5D, entity.getPosZRandom(1.0D), x, y, z);
                }

                if (!world.isRemote && !droppedStack.isEmpty()) {
                    entity.entityDropItem(droppedStack);
                }

                if (brushable.shouldReceiveRegen(entity))
                    entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, ((soothingLevel + 1) * 20 * 3)));

                brushable.onBrushed(entity, entity.getEntityWorld());
                player.getEntityWorld().playSound(player, entity.getPosition(), SoundEvents.BLOCK_GRASS_HIT, SoundCategory.PLAYERS, 0.9f, 0.9f);

                if (!player.abilities.isCreativeMode) {
                    player.getCooldownTracker().setCooldown(this, cooldownTime);
                    stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
}
