package com.mythscapes.common.blocks.plant;

import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MythFlowerBlock extends FlowerBlock {

    private final Supplier<Effect> effectSupplier;

    protected MythFlowerBlock(Supplier<Effect> effect, int duration, Properties properties) {
        // Effect parsed in super will not be used
        super(Effects.LUCK, duration, properties);
        this.effectSupplier = effect;
    }

    // Returns a BlockPos array of neighboring blocks
    // for flower spreading.
    public BlockPos[] getPosSpreadTo(BlockPos origin) {
        List<BlockPos> posList = new ArrayList<>();
        origin = origin.down();
        for (int i = 0; i < 3; i++) {
            posList.add(origin.west());
            posList.add(origin.south());
            posList.add(origin.north());
            posList.add(origin.east());
            posList.add(origin.north().east());
            posList.add(origin.north().west());
            posList.add(origin.south().east());
            posList.add(origin.south().west());
            origin = origin.up();
        }
        return posList.toArray(new BlockPos[0]);
    }

    @Override
    public Effect getStewEffect() {
        return this.effectSupplier.get();
    }
}