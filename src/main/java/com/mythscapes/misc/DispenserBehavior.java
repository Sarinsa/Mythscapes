package com.mythscapes.misc;

import com.mythscapes.common.entities.BlisterBerryEntity;
import com.mythscapes.register.MythItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class DispenserBehavior {

    public static void register() {
        /*
        DispenserBlock.registerDispenseBehavior(MythItems.ACTIVATED_BLISTER_BERRY.get(), (blockSource, itemStack) -> {
            World world = blockSource.getWorld();
            Random random = world.rand;
            IPosition position = DispenserBlock.getDispensePosition(blockSource);

            double x = position.getX() + (double)(direction.getXOffset() * 0.3F);
            double y = position.getY() + (double)(direction.getYOffset() * 0.3F);
            double z = position.getZ() + (double)(direction.getZOffset() * 0.3F);
            double xOffset = random.nextGaussian() * 0.05D + (double)direction.getXOffset();
            double yOffset = random.nextGaussian() * 0.05D + (double)direction.getYOffset();
            double zOffset = random.nextGaussian() * 0.05D + (double)direction.getZOffset();

            if (!world.isRemote) {
                BlisterBerryEntity blisterBerryEntity = new BlisterBerryEntity(null, world);
                blisterBerryEntity.setItem(itemStack);
                blisterBerryEntity.shoot(playerEntity, playerEntity.rotationPitch, playerEntity.rotationYaw, 0.0F, 1.5F, 1.0F);
                world.addEntity(blisterBerryEntity);
            }

            return itemStack;
        });

         */
    }
}
