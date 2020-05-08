package com.mythscapes.common.items;

import com.mythscapes.common.entities.IBrushable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class BrushItem extends BaseItem {

    // 7 minute cooldown
    private static final int cooldownTime = 7 * 20 * 60;

    public BrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (player.getCooldownTracker().hasCooldown(this))
            return false;

        if (entity.getEntityWorld().isRemote) return false;

        // Entities from our mod
        if (entity instanceof IBrushable) {
            IBrushable brushable = (IBrushable) entity;

            if (!brushable.canBrush()) {
                return false;
            }
            else {
                if (!brushable.itemDropped().isEmpty()) {
                    entity.entityDropItem(brushable.itemDropped());
                }
                brushable.onBrushed(entity, entity.getEntityWorld());
                entity.heal(3.0f);
                player.getCooldownTracker().setCooldown(this, cooldownTime);
                stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
                return true;
            }
        }
        // Everything else
        else if (entity instanceof SheepEntity) {
            if (((SheepEntity)entity).getSheared()) {
                return false;
            }

            entity.entityDropItem(new ItemStack(Items.STRING, (random.nextInt(3) + 1)));
            entity.heal(3.0f);

            if (!player.abilities.isCreativeMode) {
                player.getCooldownTracker().setCooldown(this, cooldownTime);
                stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
            }
            return true;
        }
        else {
            return false;
        }
    }
}
