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
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class DeerEntity extends AnimalEntity {

    private static final DataParameter<Boolean> HAS_ANTLERS = EntityDataManager.createKey(DeerEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> REGROWTH_TIME = EntityDataManager.createKey(DeerEntity.class, DataSerializers.VARINT);

    private int regrowthTime;

    public DeerEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.8, Ingredient.fromItems(Items.APPLE), false));
        this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, PlayerEntity.class, 10.0F, 1.1D, 1.4D));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 0.7D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(12, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(HAS_ANTLERS, true);
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
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.80F : 1.65F;
    }

    public boolean hasAntlers() {
        return this.dataManager.get(HAS_ANTLERS);
    }

    public void setAntlers(boolean hasAntlers) {
        this.dataManager.set(HAS_ANTLERS, hasAntlers);
    }

    /**
     *  Called when deer have children and
     *  should lose their antlers.
     */
    public void dropAntlers() {
        this.setAntlers(false);
        if (!this.world.isRemote) {
            InventoryHelper.spawnItemStack(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(MythItems.ANTLER.get()));
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack.getItem() == Items.APPLE;
    }

    private int getRegrowthTime() {
        return this.dataManager.get(REGROWTH_TIME);
    }

    private void setRegrowthTime(int value) {

    }

    private int newRegrowthTime() {
        return 8000 + this.rand.nextInt(5000);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.hasAntlers()) {
            if (!world.isRemote && --this.regrowthTime <= 0) {
                this.setAntlers(true);
                this.regrowthTime = this.newRegrowthTime();
            }
        }
    }
}