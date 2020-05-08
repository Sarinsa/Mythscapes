package com.mythscapes.common.items;

import com.mythscapes.register.MythItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class MythThrowableItem<T extends ProjectileItemEntity> extends BaseItem {

    private final Supplier<EntityType<T>> entityTypeSupplier;
    private int cooldown = 0;

    public MythThrowableItem(Supplier<EntityType<T>> entityTypeSupplier, int maxStackSize) {
        this(entityTypeSupplier, new Properties().group(MythItems.itemGroup).maxStackSize(maxStackSize));
    }

    public MythThrowableItem(Supplier<EntityType<T>> entityTypeSupplier) {
        this(entityTypeSupplier, new Properties().group(MythItems.itemGroup).maxStackSize(16));
    }

    public MythThrowableItem(Supplier<EntityType<T>> entityTypeSupplier, Properties properties) {
        super(properties);
        this.entityTypeSupplier = entityTypeSupplier;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getHeldItem(hand);
        ProjectileItemEntity entity = this.getThrowableType().create(world);

        if (entity == null) {
            return ActionResult.resultPass(itemStack);
        }
        else {
            entity.setPosition(playerEntity.getPosX(), playerEntity.getPosYEye() - (double)0.1F, playerEntity.getPosZ());
            entity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(entity);
            world.playSound(playerEntity, playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            playerEntity.addStat(Stats.ITEM_USED.get(this));

            if (!playerEntity.abilities.isCreativeMode) {
                if (this.cooldown > 0) {
                    playerEntity.getCooldownTracker().setCooldown(this, this.getCooldown());
                }
                itemStack.shrink(1);
            }
            return ActionResult.resultSuccess(itemStack);
        }
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public MythThrowableItem<T> setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    public EntityType<T> getThrowableType() {
        return this.entityTypeSupplier.get();
    }
}
