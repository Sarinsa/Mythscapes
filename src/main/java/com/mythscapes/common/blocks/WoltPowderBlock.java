package com.mythscapes.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IBlockReader;

public class WoltPowderBlock extends Block {

    private final boolean golden;

    public WoltPowderBlock(boolean golden, Properties properties) {
        super(properties);
        this.golden = golden;
    }

    private boolean isGolden() {
        return this.golden;
    }

    @Override
    public void onLanded(IBlockReader world, Entity entity) {
        entity.getEntityWorld().playSound(entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.9f, 0.1f, false);

        if (entity instanceof ItemEntity) {
            ItemEntity itemEntity = (ItemEntity) entity;
            entity.setPositionAndUpdate(entity.getPosX(), entity.getPosY() + 0.2, entity.getPosZ());
        }
        entity.setMotion(0.0D, this.isGolden() ? 1.4D : 1.0d, 0.0D);
    }
}
