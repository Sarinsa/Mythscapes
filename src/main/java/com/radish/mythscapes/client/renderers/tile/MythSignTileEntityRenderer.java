package com.radish.mythscapes.client.renderers.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radish.mythscapes.common.blocks.compat.ModAbstractSignBlock;
import com.radish.mythscapes.common.blocks.compat.ModStandingSignBlock;
import com.radish.mythscapes.common.blocks.compat.ModWallSignBlock;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Copy-pasta from SignTileEntityRenderer
 */
public class MythSignTileEntityRenderer<T extends MythSignTileEntity> extends TileEntityRenderer<T> {

    private final SignTileEntityRenderer.SignModel model = new SignTileEntityRenderer.SignModel();

    public MythSignTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public void render(MythSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockState blockstate = tileEntityIn.getBlockState();
        matrixStackIn.push();

        if (blockstate.getBlock() instanceof ModStandingSignBlock) {
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            float f1 = -((float)(blockstate.get(ModStandingSignBlock.ROTATION) * 360) / 16.0F);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
            this.model.signStick.showModel = true;
        } else {
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            float f4 = -blockstate.get(ModWallSignBlock.FACING).getHorizontalAngle();
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f4));
            matrixStackIn.translate(0.0D, -0.3125D, -0.4375D);
            this.model.signStick.showModel = false;
        }
        matrixStackIn.push();
        matrixStackIn.scale(0.6666667F, -0.6666667F, -0.6666667F);

        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(getSignTexture(blockstate.getBlock())));

        this.model.signBoard.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        this.model.signStick.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
        matrixStackIn.pop();
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
        matrixStackIn.translate(0.0D, 0.33333334F, 0.046666667F);
        matrixStackIn.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int i = tileEntityIn.getTextColor().getTextColor();
        int j = (int)((double) NativeImage.getRed(i) * 0.4D);
        int k = (int)((double)NativeImage.getGreen(i) * 0.4D);
        int l = (int)((double)NativeImage.getBlue(i) * 0.4D);
        int i1 = NativeImage.getCombined(0, l, k, j);

        for(int k1 = 0; k1 < 4; ++k1) {
            IReorderingProcessor ireorderingprocessor = tileEntityIn.func_242686_a(k1, (p_243502_1_) -> {
                List<IReorderingProcessor> list = fontrenderer.trimStringToWidth(p_243502_1_, 90);
                return list.isEmpty() ? IReorderingProcessor.field_242232_a : list.get(0);
            });
            if (ireorderingprocessor != null) {
                float f3 = (float)(-fontrenderer.func_243245_a(ireorderingprocessor) / 2);
                fontrenderer.func_238416_a_(ireorderingprocessor, f3, (float)(k1 * 10 - 20), i1, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);
            }
        }
        matrixStackIn.pop();
    }

    private ResourceLocation getSignTexture(Block block) {
        if (block instanceof ModAbstractSignBlock) {
            return ((ModAbstractSignBlock)block).getSignTexture();
        }
        else {
            return Mythscapes.resourceLoc("texture/tile/signs/wolt.png");
        }
    }
}
