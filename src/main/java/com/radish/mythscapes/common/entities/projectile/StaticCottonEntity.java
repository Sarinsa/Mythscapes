package com.radish.mythscapes.common.entities.projectile;

import com.radish.mythscapes.common.register.MythEffects;
import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class StaticCottonEntity extends ProjectileItemEntity {


    public StaticCottonEntity(EntityType<? extends ProjectileItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public StaticCottonEntity(double x, double y, double z, World world) {
        super(MythEntities.BLISTERBERRY.get(), x, y, z, world);
    }

    public StaticCottonEntity(LivingEntity livingEntity, World world) {
        super(MythEntities.STATIC_COTTON.get(), livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return MythItems.STATIC_COTTON.get();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        if (entity instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) entity;
            target.addEffect(new EffectInstance(MythEffects.STATIC.get(), (20 * 5)));
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        this.remove();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
