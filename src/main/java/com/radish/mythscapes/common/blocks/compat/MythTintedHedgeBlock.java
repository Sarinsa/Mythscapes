package com.radish.mythscapes.common.blocks.compat;

import java.util.ArrayList;
import java.util.List;

public class MythTintedHedgeBlock extends MythHedgeBlock {

    public static final List<MythTintedHedgeBlock> blockList = new ArrayList<>();
    private final int leavesColor;

    public MythTintedHedgeBlock(Properties properties, int leavesColor) {
        super(properties);
        blockList.add(this);
        this.leavesColor = leavesColor;
    }

    public int getLeavesColor() {
        return this.leavesColor;
    }
}
