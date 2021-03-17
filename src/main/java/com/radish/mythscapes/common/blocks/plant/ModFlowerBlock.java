package com.radish.mythscapes.common.blocks.plant;

import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class ModFlowerBlock extends FlowerBlock {

    private final Supplier<Effect> effectSupplier;

    protected ModFlowerBlock(Supplier<Effect> effect, int duration, Properties properties) {
        // Effect parsed in super will not be used
        super(Effects.LUCK, duration, properties);
        this.effectSupplier = effect;
    }

    /**
     * @param origin - The center position from where a
     *                 bounding box is created.
     *
     * @return - A BlockPos[] containing all the neighbour positions
     *           of this BlockPos.
     */
    public List<BlockPos> getPosSpreadTo(BlockPos origin) {
        List<BlockPos> posList = new ArrayList<>();
        origin = origin.below();
        for (int i = 0; i < 3; i++) {
            posList.add(origin.west());
            posList.add(origin.south());
            posList.add(origin.north());
            posList.add(origin.east());
            posList.add(origin.north().east());
            posList.add(origin.north().west());
            posList.add(origin.south().east());
            posList.add(origin.south().west());
            origin = origin.above();
        }
        return posList;
    }

    /**
     * @param origin - The center position from where a
     *                 bounding box is created.
     *
     * @param radius - The size of the bounding box
     *
     * @return - A BlockPos[] containing all the positions
     *           in
     */
    public List<BlockPos> getPosSpreadTo(BlockPos origin, int radius) {
        Iterator<BlockPos> iterator = BlockPos.betweenClosedStream(new AxisAlignedBB(origin).inflate(radius)).iterator();
        ArrayList<BlockPos> posList = new ArrayList<>();

        iterator.forEachRemaining(blockPos -> {
            posList.add(blockPos.immutable());
        });

        return posList;

        /*
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

         */
    }

    @Override
    public Effect getSuspiciousStewEffect() {
        return this.effectSupplier.get();
    }
}
