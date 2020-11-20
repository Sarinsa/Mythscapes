package com.radish.mythscapes.common.items;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.register.MythEntities;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class SnailBucketItem extends Item {

    public SnailBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        BlockRayTraceResult rayTraceResult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos blockPos = rayTraceResult.getPos();
            Direction direction = rayTraceResult.getFace();
            BlockPos facingPos = blockPos.offset(direction);

            if (!world.isRemote) {
                SnailEntity snailEntity = this.spawnSnail((ServerWorld) world, itemStack, player, facingPos);

                if (snailEntity == null) {
                    return ActionResult.resultPass(itemStack);
                }
                else {
                    if (!player.abilities.isCreativeMode) {
                        player.setHeldItem(hand, new ItemStack(Items.BUCKET));
                    }
                    player.addStat(Stats.ITEM_USED.get(this));
                    return ActionResult.resultSuccess(itemStack);
                }
            }
        }
        return ActionResult.resultPass(itemStack);
    }

    private SnailEntity spawnSnail(ServerWorld world, ItemStack itemStack, PlayerEntity player, BlockPos facingPos) {
        CompoundNBT tag = itemStack.getOrCreateTag().copy();
        if (!tag.contains("SnailType", 8))
            tag.putString("SnailType", SnailEntity.SnailType.getRandom().getName());

        return MythEntities.PYGMY_SNAIL.get().spawn(world, tag, itemStack.hasDisplayName() ? itemStack.getDisplayName() : null, player, facingPos, SpawnReason.BUCKET, false, false);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return this.getRarity(stack) == Rarity.EPIC;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        SnailEntity.SnailType snailType = this.getSnailType(stack);
        return snailType == null ? Rarity.COMMON : snailType.getRarity();
    }

    @Nullable
    public SnailEntity.SnailType getSnailType(ItemStack itemStack) {
        CompoundNBT compoundNBT = itemStack.getTag();
        String snailType = null;

        if (compoundNBT != null && compoundNBT.contains("SnailType", 8))
            snailType = compoundNBT.getString("SnailType");
        return SnailEntity.SnailType.getFromNameOrNull(snailType);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        SnailEntity.SnailType snailType = this.getSnailType(itemStack);

        if (snailType != null) {
            Color color = this.getRarity(itemStack) == Rarity.COMMON
                    ? Color.fromTextFormatting(TextFormatting.GRAY)
                    : Color.fromTextFormatting(this.getRarity(itemStack).color);

            tooltip.add(new TranslationTextComponent(Util.makeTranslationKey("snail_type", Mythscapes.resourceLoc(snailType.getName()))).setStyle(Style.EMPTY.setColor(color)));
        }
    }
}
