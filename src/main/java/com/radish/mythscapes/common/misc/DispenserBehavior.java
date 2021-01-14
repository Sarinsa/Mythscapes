package com.radish.mythscapes.common.misc;

import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class DispenserBehavior {

    public static void register() {
        // Activated Blisterberry
        DispenserBlock.registerDispenseBehavior(MythItems.ACTIVATED_BLISTERBERRY.get(), createSimple(MythEntities.BLISTERBERRY));
        DispenserBlock.registerDispenseBehavior(MythItems.GLOWBALL.get(), createSimple(MythEntities.GLOWBALL));
        DispenserBlock.registerDispenseBehavior(MythItems.STATIC_COTTON.get(), createSimple(MythEntities.STATIC_COTTON));
        // Glowball

        /*
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

         */
    }

    private static <T extends ProjectileEntity> ProjectileDispenseBehavior createSimple(Supplier<EntityType<T>> entityTypeSupplier) {
        return new ProjectileDispenseBehavior() {
            @Override
            protected ProjectileEntity getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {
                ProjectileEntity entity = entityTypeSupplier.get().create(worldIn);

                if (entity == null) {
                    entity = new SnowballEntity(worldIn, position.getX(), position.getY(), position.getZ());
                }
                entity.setPosition(position.getX(), position.getY(), position.getZ());
                entity.setMotion(1.0f, 1.0f, 1.0f);
                return entity;
            }
        };
    }
}
