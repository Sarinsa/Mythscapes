package com.radish.mythscapes.common.blocks.plant;

import java.util.ArrayList;
import java.util.List;

public class TintedLeafCarpetBlock extends LeafCarpetBlock {

    public static final List<TintedLeafCarpetBlock> blockList = new ArrayList<>();
    private final int leavesColor;

    public TintedLeafCarpetBlock(Properties properties, int leavesColor) {
        super(properties);
        blockList.add(this);
        this.leavesColor = leavesColor;
    }

    public TintedLeafCarpetBlock(int leavesColor) {
        super();
        blockList.add(this);
        this.leavesColor = leavesColor;
    }

    public int getLeavesColor() {
        return this.leavesColor;
    }
}
