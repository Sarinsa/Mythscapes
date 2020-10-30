package com.mythscapes.misc;

import com.mythscapes.common.entities.projectile.BlisterberryEntity;
import com.mythscapes.common.entities.projectile.GlowballEntity;
import com.mythscapes.common.entities.projectile.StaticCottonEntity;
import com.mythscapes.register.MythItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.minecraft.world.World;

public class DispenserBehavior {

    public static void register() {
        // Activated Blisterberry
        DispenserBlock.registerDispenseBehavior(MythItems.ACTIVATED_BLISTERBERRY.get(), new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World world, IPosition iPosition, ItemStack itemStack) {
                return Util.make(new BlisterberryEntity(iPosition.getX(), iPosition.getY(), iPosition.getZ(), world), (entity) -> {
                    entity.setMotion(1.0f, 1.0f, 0.1f);
                });
            }
        });

        // Glowball
        DispenserBlock.registerDispenseBehavior(MythItems.GLOWBALL.get(), new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World world, IPosition iPosition, ItemStack itemStack) {
                return Util.make(new GlowballEntity(iPosition.getX(), iPosition.getY(), iPosition.getZ(), world), (entity) -> {
                    entity.setMotion(1.0f, 1.0f, 0.1f);
                });
            }
        });

        // Static Cotton
        DispenserBlock.registerDispenseBehavior(MythItems.STATIC_COTTON.get(), new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World world, IPosition iPosition, ItemStack itemStack) {
                return Util.make(new StaticCottonEntity(iPosition.getX(), iPosition.getY(), iPosition.getZ(), world), (entity) -> {
                    entity.setMotion(1.0f, 1.0f, 0.1f);
                });
            }
        });
    }
}
