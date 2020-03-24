package com.mythscapes.misc;

import com.mythscapes.common.entities.BlisterberryEntity;
import com.mythscapes.register.MythItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.world.World;

public class DispenserBehavior {

    public static void register() {
        // Activated Blisterberry
        DispenserBlock.registerDispenseBehavior(MythItems.ACTIVATED_BLISTERBERRY.get(), new ProjectileDispenseBehavior() {
            @Override
            protected IProjectile getProjectileEntity(World world, IPosition iPosition, ItemStack itemStack) {
                return Util.make(new BlisterberryEntity(iPosition.getX(), iPosition.getY(), iPosition.getZ(), world), (entity) -> {
                    entity.setMotion(1.0f, 1.0f, 0.1f);
                });
            }
        });
    }
}
