package com.radish.mythscapes.common.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DrinkableBottleItem extends Item {

    private final Supplier<EffectInstance> effectInstanceSupplier;

    public DrinkableBottleItem(Properties properties) {
        this(properties, null);
    }

    public DrinkableBottleItem(Properties properties, @Nullable Supplier<EffectInstance> drinkEffect) {
        super(properties);
        this.effectInstanceSupplier = drinkEffect;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
        PlayerEntity playerentity = livingEntity instanceof PlayerEntity ? (PlayerEntity)livingEntity : null;
        if (playerentity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerentity, stack);
        }

        if (!world.isClientSide && this.effectInstanceSupplier != null) {
            EffectInstance effectinstance = this.effectInstanceSupplier.get();

            if (effectinstance.getEffect().isInstantenous()) {
                effectinstance.getEffect().applyInstantenousEffect(playerentity, playerentity, livingEntity, effectinstance.getAmplifier(), 1.0D);
            }
            else {
                livingEntity.addEffect(new EffectInstance(effectinstance));
            }
        }

        if (playerentity != null) {
            playerentity.awardStat(Stats.ITEM_USED.get(this));
            if (!playerentity.abilities.instabuild) {
                stack.shrink(1);
            }
        }

        if (playerentity == null || !playerentity.abilities.instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerentity != null) {
                playerentity.inventory.add(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return DrinkHelper.useDrink(worldIn, playerIn, handIn);
    }
}
