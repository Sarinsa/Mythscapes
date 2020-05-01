package com.mythscapes.common.items;

import com.mythscapes.misc.MythFoods;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WoltFruitItem extends BaseItem {

    public WoltFruitItem(boolean golden) {
        super(golden
                ? new Item.Properties().group(MythItems.itemGroup).food(MythFoods.WOLT_FRUIT)
                : new Item.Properties().group(MythItems.itemGroup).food(MythFoods.GOLDEN_WOLT_FRUIT));
        this.golden = golden;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        Vec3d motion = entityLiving.getMotion();
        double motionY = 1.0d;

        if (this.isGolden())
            motionY = 1.4d;

        worldIn.playSound(entityLiving.getPosX(), entityLiving.getPosY(), entityLiving.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 1.0f, 0.1f, false);
        entityLiving.setMotion(motion.getX(), this.isGolden() ? 1.4d : 1.0d, motion.getZ());
        return entityLiving.onFoodEaten(worldIn, stack);
    }
}
