package com.mythscapes.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WoltFruitItem extends Item {

    private final boolean golden;

    public WoltFruitItem(Properties properties, boolean golden) {
        super(properties);
        this.golden = golden;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Vector3d motion = entityLiving.getMotion();
        worldIn.playSound(entityLiving.getPosX(), entityLiving.getPosY(), entityLiving.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 1.0f, 0.1f, false);
        entityLiving.setMotion(motion.getX(), this.golden ? 1.4d : 1.0d, motion.getZ());
        return entityLiving.onFoodEaten(worldIn, stack);
    }
}
