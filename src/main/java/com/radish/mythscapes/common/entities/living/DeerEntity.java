package com.radish.mythscapes.common.entities.living;

import com.radish.mythscapes.common.register.MythEntities;
import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class DeerEntity extends AnimalEntity {

    private static final DataParameter<Integer> REGROWTH_TIME = EntityDataManager.createKey(DeerEntity.class, DataSerializers.VARINT);

    public DeerEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new DeerEntity.BreedingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.8, Ingredient.fromItems(Items.APPLE), true));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 10.0F, 1.2D, 1.4D));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, LionEntity.class, 13.0F, 1.2D, 1.4D));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(12, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(REGROWTH_TIME, 0);
    }


    public static AttributeModifierMap.MutableAttribute registerEntityAttributes() {
        return CreatureEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.30D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
    }

    @Nullable
    @Override
    public AgeableEntity func_241840_a(ServerWorld serverWorld, AgeableEntity ageableEntity) {
        return MythEntities.DEER.get().create(this.world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return this.isChild() ? 0.80F : 1.65F;
    }

    public boolean hasAntlers() {
        return this.getRegrowthTime() <= 0 && !this.isChild();
    }

    /**
     *  Called when deer have children and
     *  should lose their antlers.
     */
    public void dropAntlers() {
        this.newRegrowthTime();
        if (!this.world.isRemote) {
            InventoryHelper.spawnItemStack(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(MythItems.ANTLER.get()));
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("RegrowthTime", this.getRegrowthTime());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setRegrowthTime(compound.getInt("RegrowthTime"));
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.APPLE;
    }

    private int getRegrowthTime() {
        return this.dataManager.get(REGROWTH_TIME);
    }

    private void setRegrowthTime(int value) {
        this.dataManager.set(REGROWTH_TIME, value);
    }

    private void newRegrowthTime() {
        this.setRegrowthTime(8000 + this.rand.nextInt(3000));
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!this.hasAntlers()) {
            this.setRegrowthTime(this.getRegrowthTime() - 1);
        }
    }

    private class BreedingGoal extends BreedGoal {

        public BreedingGoal(AnimalEntity animal, double speedIn) {
            super(animal, speedIn);
        }

        @Override
        public boolean shouldExecute() {
            return DeerEntity.this.hasAntlers() && super.shouldExecute();
        }
    }
}
