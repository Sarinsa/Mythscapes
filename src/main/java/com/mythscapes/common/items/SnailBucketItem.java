package com.mythscapes.common.items;

import com.mythscapes.common.entities.living.snail.SnailEntity;
import com.mythscapes.misc.ModResourceLocation;
import com.mythscapes.register.MythEntities;
import com.mythscapes.register.MythItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;

import static com.mythscapes.common.entities.living.snail.SnailEntity.SnailType;

public class SnailBucketItem extends BaseItem {

    public SnailBucketItem() {
        super(new Properties().group(MythItems.itemGroup).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        RayTraceResult rayTraceResult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockResult = (BlockRayTraceResult) rayTraceResult;
            BlockPos blockPos = blockResult.getPos();
            Direction direction = blockResult.getFace();
            BlockPos facingPos = blockPos.offset(direction);

            SnailEntity snailEntity = this.spawnSnail(world, itemStack, player, facingPos);

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
        return ActionResult.resultPass(itemStack);
    }

    private SnailEntity spawnSnail(World world, ItemStack itemStack, PlayerEntity player, BlockPos facingPos) {
        CompoundNBT tag = itemStack.getOrCreateTag().copy();
        if (!tag.contains("SnailType", 8))
            tag.putString("SnailType", SnailType.getRandom().getName());

        return MythEntities.PYGMY_SNAIL.get().spawn(world, tag, itemStack.hasDisplayName() ? itemStack.getDisplayName() : null, player, facingPos, SpawnReason.BUCKET, false, false);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return this.getRarity(stack) == Rarity.EPIC;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        SnailType snailType = this.getSnailType(stack);
        return snailType == null ? Rarity.COMMON : snailType.getRarity();
    }

    @Nullable
    public SnailType getSnailType(ItemStack itemStack) {
        CompoundNBT compoundNBT = itemStack.getTag();
        String snailType = null;

        if (compoundNBT != null && compoundNBT.contains("SnailType", 8))
            snailType = compoundNBT.getString("SnailType");

        return SnailType.getFromNameOrNull(snailType);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        SnailType snailType = this.getSnailType(itemStack);

        if (snailType != null) {
            TextFormatting color = this.getRarity(itemStack) == Rarity.COMMON
                    ? TextFormatting.GRAY
                    : this.getRarity(itemStack).color;
            tooltip.add(new TranslationTextComponent(Util.makeTranslationKey("snail_type", new ModResourceLocation(snailType.getName()))).setStyle(new Style().setColor(color)));
        }
    }
}
