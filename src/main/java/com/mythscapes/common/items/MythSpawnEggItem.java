package com.mythscapes.common.items;

import com.google.common.collect.Iterables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Copy-pasta from SpawnEggItem, but with a constructor
 * that takes a Supplier<EntityType></>
 */
public class MythSpawnEggItem extends BaseItem {

    private static final Map<Supplier<? extends EntityType<?>>, MythSpawnEggItem> EGGS = new HashMap<>();
    private final int primaryColor, secondaryColor;
    Supplier<? extends EntityType<?>> entityTypeSupplier;

    public MythSpawnEggItem(Supplier<? extends EntityType<?>> entityType, int primaryColor, int secondaryColor) {
        super();
        this.entityTypeSupplier = entityType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        EGGS.put(entityType, this);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();

        if (world.isRemote) {
            return ActionResultType.SUCCESS;
        }
        else {
            ItemStack itemStack = context.getItem();
            BlockPos pos = context.getPos();
            Direction direction = context.getFace();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (block == Blocks.SPAWNER) {
                TileEntity tileEntity = world.getTileEntity(pos);

                if (tileEntity instanceof MobSpawnerTileEntity) {
                    AbstractSpawner spawner = ((MobSpawnerTileEntity)tileEntity).getSpawnerBaseLogic();
                    EntityType<?> entityType = this.getType(itemStack.getTag());
                    spawner.setEntityType(entityType);
                    tileEntity.markDirty();
                    world.notifyBlockUpdate(pos, state, state, 3);
                    itemStack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }
            BlockPos pos1 = state.getCollisionShape(world, pos).isEmpty() ? pos : pos.offset(direction);
            EntityType<?> entityType = this.getType(itemStack.getTag());
            
            if (entityType.spawn(world, itemStack, context.getPlayer(), pos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(pos, pos1) && direction == Direction.UP) != null) {
                itemStack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        RayTraceResult rayTraceResult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (rayTraceResult.getType() != RayTraceResult.Type.BLOCK) {
            return ActionResult.resultPass(itemStack);
        }
        else if (world.isRemote) {
            return ActionResult.resultSuccess(itemStack);
        }
        else {
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult)rayTraceResult;
            BlockPos pos = blockRayTraceResult.getPos();

            if (!(world.getBlockState(pos).getBlock() instanceof FlowingFluidBlock)) {
                return ActionResult.resultPass(itemStack);
            }
            else if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos, blockRayTraceResult.getFace(), itemStack)) {
                EntityType<?> entityType = this.getType(itemStack.getTag());

                if (entityType.spawn(world, itemStack, player, pos, SpawnReason.SPAWN_EGG, false, false) == null) {
                    return ActionResult.resultPass(itemStack);
                }
                else {
                    if (!player.abilities.isCreativeMode) {
                        itemStack.shrink(1);
                    }

                    player.addStat(Stats.ITEM_USED.get(this));
                    return ActionResult.resultSuccess(itemStack);
                }
            } else {
                return ActionResult.resultFail(itemStack);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getColor(int color) {
        return color == 0 ? this.primaryColor : this.secondaryColor;
    }

    public static Iterable<MythSpawnEggItem> getEggs() {
        return Iterables.unmodifiableIterable(EGGS.values());
    }

    public EntityType<?> getEntityType() {
        return this.entityTypeSupplier.get();
    }

    public EntityType<?> getType(@Nullable CompoundNBT compoundNBT) {
        if (compoundNBT != null && compoundNBT.contains("EntityTag", 10)) {
            CompoundNBT tag = compoundNBT.getCompound("EntityTag");
            if (tag.contains("id", 8)) {
                return EntityType.byKey(tag.getString("id")).orElse(this.getEntityType());
            }
        }
        return this.getEntityType();
    }
}
