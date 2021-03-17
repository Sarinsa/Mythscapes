package com.radish.mythscapes.common.entities.projectile;

import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlisterberryEntity extends ProjectileItemEntity {


    public BlisterberryEntity(EntityType<? extends BlisterberryEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlisterberryEntity(double x, double y, double z, World world) {
        super(MythEntities.BLISTERBERRY.get(), x, y, z, world);
    }

    public BlisterberryEntity(LivingEntity livingEntity, World world) {
        super(MythEntities.BLISTERBERRY.get(), livingEntity, world);
    }

    @Override
    public float getGravity() {
        return 0.04F;
    }


    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity target = result.getEntity();

        target.hurt(DamageSource.thrown(this, this.getOwner()), 2.0F);
    }

    @Override
    public void onHit(RayTraceResult result) {
        super.onHit(result);

        if (!this.level.isClientSide) {
            boolean mobGriefing = ForgeEventFactory.getMobGriefingEvent(this.level, this);
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 2.0f, mobGriefing, Explosion.Mode.NONE);

            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return MythItems.ACTIVATED_BLISTERBERRY.get();
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
