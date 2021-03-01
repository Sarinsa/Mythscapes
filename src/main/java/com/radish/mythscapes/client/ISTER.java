package com.radish.mythscapes.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.radish.mythscapes.client.renderers.tile.MythChestTileEntityRenderer;
import com.radish.mythscapes.common.blocks.MythChestBlock;
import com.radish.mythscapes.common.tile.MythChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Keep all our ISTERs in a separate class here
 * to avoid class loading going bonkers and
 * loading this stuff before tile entity types
 * are registered.
 */
public class ISTER {

    public static Callable<ItemStackTileEntityRenderer> chestISTER(Block chestBlock) {
        if (!(chestBlock instanceof MythChestBlock))
            throw new IllegalArgumentException("Tried to set chest ISTER with the wrong block type. Should be an instance of MythChestBlock");

        return () -> new ItemStackTileEntityRenderer() {
            final Supplier<MythChestTileEntity> supplier = MythChestTileEntity::new;

            @Override
            public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
                MythChestTileEntityRenderer.itemRenderBlock = chestBlock;
                TileEntityRendererDispatcher.instance.renderItem(supplier.get(), matrixStack, buffer, combinedLight, combinedOverlay);
            }
        };
    }
}
