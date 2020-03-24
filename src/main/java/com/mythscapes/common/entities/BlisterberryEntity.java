package com.mythscapes.common.entities;

import com.mythscapes.register.MythEntities;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
    public float getGravityVelocity() {
        return 0.04F;
    }

    @Override
    public void onImpact(RayTraceResult result) {
        World world = this.getEntityWorld();
        LivingEntity thrower = this.getThrower();

        if (!world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity target = ((EntityRayTraceResult)result).getEntity();
                target.attackEntityFrom(DamageSource.causeThrownDamage(this, thrower), 2.0F);
                this.applyEnchantments(thrower, target);
            }
            boolean mobGriefing = ForgeEventFactory.getMobGriefingEvent(world, thrower);
            world.createExplosion(this, DamageSource.GENERIC, this.getPosX(), this.getPosY(), this.getPosZ(), 2.0f, mobGriefing, Explosion.Mode.NONE);
            this.remove();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return MythItems.ACTIVATED_BLISTERBERRY.get();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
