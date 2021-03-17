package com.radish.mythscapes.common.entities.projectile;

import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class GlowballEntity extends ProjectileItemEntity {

    // Used for dispenser
    // behavior and summoning
    private BlockPos posSpawned = null;

    public GlowballEntity(EntityType<? extends ProjectileItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public GlowballEntity(double x, double y, double z, World world) {
        super(MythEntities.GLOWBALL.get(), x, y, z, world);
        this.posSpawned = new BlockPos(x, y, z);
    }

    public GlowballEntity(LivingEntity livingEntity, World world) {
        super(MythEntities.GLOWBALL.get(), livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return MythItems.GLOWBALL.get();
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        if (entity instanceof LivingEntity) {
            LivingEntity target = (LivingEntity)entity;
            target.addEffect(new EffectInstance(Effects.GLOWING, (20 * 30)));

            if (target instanceof PlayerEntity) {
                EquipmentSlotType slotType = EquipmentSlotType.MAINHAND;
                if (!target.hasItemInSlot(slotType)) {
                    slotType = EquipmentSlotType.OFFHAND;
                }
                if (target.hasItemInSlot(slotType)) {
                    ItemStack targetStack = target.getMainHandItem().copy();
                    Entity thrower = this.getOwner();

                    if (thrower != null) {
                        this.level.addFreshEntity(new ItemEntity(this.level, thrower.getX(), thrower.getY(), thrower.getZ(), targetStack));
                    }
                    else if (this.posSpawned != null) {
                        this.level.addFreshEntity(new ItemEntity(this.level, this.posSpawned.getX(), this.posSpawned.getY(), this.posSpawned.getZ(), targetStack));
                    }
                }
            }
            if (target.canChangeDimensions()) {
                target.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(MythItems.GLOWBALL.get()));
            }
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
