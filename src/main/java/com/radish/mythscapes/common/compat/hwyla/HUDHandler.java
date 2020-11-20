package com.radish.mythscapes.common.compat.hwyla;

import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.register.MythItems;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IEntityComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class HUDHandler implements IComponentProvider, IEntityComponentProvider {

    protected static final HUDHandler INSTANCE = new HUDHandler();

    @Override
    public ItemStack getStack(IDataAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() == MythBlocks.BLISTERBERRY_THISTLE.get()) {
            return new ItemStack(MythItems.BLISTERBERRY.get());
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() == MythBlocks.BLISTERBERRY_THISTLE.get()) {
            int age = accessor.getBlockState().get(BlockStateProperties.AGE_0_5);

            addCropMaturityTooltip(tooltip, age / (float) 5);
        }
    }

    private static void addCropMaturityTooltip(List<ITextComponent> tooltip, float growthValue) {
        growthValue *= 100.0F;

        if (growthValue < 100.0F) {
            tooltip.add(new TranslationTextComponent("tooltip.waila.crop_growth", String.format("%.0f%%", growthValue)));
        }
        else {
            tooltip.add(new TranslationTextComponent("tooltip.waila.crop_growth", new TranslationTextComponent("tooltip.waila.crop_mature")));
        }
    }
}
