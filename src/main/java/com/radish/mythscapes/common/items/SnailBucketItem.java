package com.radish.mythscapes.common.items;

import com.radish.mythscapes.api.ISnailType;
import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.common.entities.living.SnailEntity;
import com.radish.mythscapes.common.register.MythEntities;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SnailBucketItem extends Item {

    public SnailBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        BlockRayTraceResult rayTraceResult = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);

        if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos blockPos = rayTraceResult.getBlockPos();
            Direction direction = rayTraceResult.getDirection();
            BlockPos facingPos = blockPos.relative(direction);

            if (!world.isClientSide) {
                this.spawnSnail((ServerWorld) world, itemStack, player, facingPos);

                if (!player.abilities.instabuild) {
                    player.setItemInHand(hand, new ItemStack(Items.BUCKET));
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return ActionResult.success(itemStack);
            }
        }
        return ActionResult.pass(itemStack);
    }

    private void spawnSnail(ServerWorld world, ItemStack itemStack, PlayerEntity player, BlockPos facingPos) {
        CompoundNBT tag = itemStack.getOrCreateTag().copy();
        if (!tag.contains("SnailType", 8))
            tag.putString("SnailType", SnailTypeRegister.getRandom().getName().toString());

        SnailEntity snail = MythEntities.PYGMY_SNAIL.get().spawn(world, tag, itemStack.hasCustomHoverName() ? itemStack.getDisplayName() : null, player, facingPos, SpawnReason.BUCKET, false, false);

        if (snail != null)
            snail.setFromBucket(true);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.getRarity(stack) == Rarity.EPIC;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        ISnailType snailType = this.getSnailType(stack);
        return snailType == null ? Rarity.COMMON : snailType.getRarity();
    }

    @Nullable
    public ISnailType getSnailType(ItemStack itemStack) {
        CompoundNBT compoundNBT = itemStack.getTag();
        String snailType = null;

        if (compoundNBT != null && compoundNBT.contains("SnailType", 8))
            snailType = compoundNBT.getString("SnailType");

        return SnailTypeRegister.getFromNameOrNull(snailType);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        ISnailType snailType = this.getSnailType(itemStack);

        if (snailType != null) {
            TextFormatting color = snailType.getRarity().color;
            tooltip.add(new TranslationTextComponent(SnailTypeRegister.getTranslationKey(snailType)).withStyle(color));
        }
    }
}
