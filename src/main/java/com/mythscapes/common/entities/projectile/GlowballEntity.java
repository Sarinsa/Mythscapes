package com.mythscapes.common.entities.projectile;

import com.mythscapes.register.MythEntities;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
    protected void onImpact(RayTraceResult result) {
        World world = this.getEntityWorld();

        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();

            if (entity instanceof LivingEntity) {
                LivingEntity target = (LivingEntity)entity;
                target.addPotionEffect(new EffectInstance(Effects.GLOWING, (20 * 30)));

                if (target instanceof PlayerEntity) {
                    EquipmentSlotType slotType = EquipmentSlotType.MAINHAND;
                    if (!target.hasItemInSlot(slotType)) {
                        slotType = EquipmentSlotType.OFFHAND;
                    }
                    if (target.hasItemInSlot(slotType)) {
                        ItemStack targetStack = target.getHeldItemMainhand().copy();
                        Entity thrower = this.func_234616_v_();

                        if (thrower != null) {
                            world.addEntity(new ItemEntity(world, thrower.getPosX(), thrower.getPosY(), thrower.getPosZ(), targetStack));
                        } else if (this.posSpawned != null) {
                            world.addEntity(new ItemEntity(world, this.posSpawned.getX(), this.posSpawned.getY(), this.posSpawned.getZ(), targetStack));
                        }
                    }
                }
                target.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(MythItems.GLOWBALL.get()));
            }
        }
        this.remove();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
