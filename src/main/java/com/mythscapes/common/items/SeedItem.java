package com.mythscapes.common.items;

import com.mythscapes.common.blocks.plant.AbstractHarvestableBlock;
import com.mythscapes.misc.MythItemGroup;
import com.mythscapes.register.MythItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class SeedItem extends Item {

    private final Supplier<AbstractHarvestableBlock> plantBlock;

    public SeedItem(Supplier<AbstractHarvestableBlock> plant, Properties properties) {
        super(properties);
        this.plantBlock = plant;
    }

    public AbstractHarvestableBlock getPlantBlock() {
        return this.plantBlock.get();
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        Direction face = context.getFace();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState groundState = world.getBlockState(pos);

        if (face == Direction.UP) {
            if (world.getBlockState(pos.up()).isAir(world, pos.up()) && getPlantBlock().isValidGround(groundState, world, pos)) {
                world.setBlockState(pos.up(), getPlantBlock().getDefaultState());
                world.playSound(player, pos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0f, 1.0f);

                if (player != null) {
                    player.addStat(Stats.ITEM_USED.get(this));

                    if (player.abilities.isCreativeMode) {
                        context.getItem().shrink(1);
                    }
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }
}
