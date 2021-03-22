package com.radish.mythscapes.client.renderers.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.radish.mythscapes.common.blocks.compat.MythChestBlock;
import com.radish.mythscapes.common.tile.MythChestTileEntity;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.*;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.Calendar;

// Vanilla copy-pasta
@SuppressWarnings("all")
public class MythChestTileEntityRenderer<T extends MythChestTileEntity & IChestLid> extends TileEntityRenderer<T> {


    // This is just for funsies
    private static final ResourceLocation DEFAULT = new ResourceLocation("textures/item/blisterberry.png");

    // Using this for the chest block ISTER
    public static Block itemRenderBlock = null;

    private final ModelRenderer lid;
    private final ModelRenderer bottom;
    private final ModelRenderer lock;
    private final ModelRenderer doubleLeftLid;
    private final ModelRenderer doubleLeftBottom;
    private final ModelRenderer doubleLeftLock;
    private final ModelRenderer doubleRightLid;
    private final ModelRenderer doubleRightBottom;
    private final ModelRenderer doubleRightLock;
    private boolean xmasTextures;

    public MythChestTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);

        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.xmasTextures = true;
        }

        this.bottom = new ModelRenderer(64, 64, 0, 19);
        this.bottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
        this.lid = new ModelRenderer(64, 64, 0, 0);
        this.lid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
        this.lid.y = 9.0F;
        this.lid.z = 1.0F;
        this.lock = new ModelRenderer(64, 64, 0, 0);
        this.lock.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
        this.lock.y = 8.0F;
        this.doubleLeftBottom = new ModelRenderer(64, 64, 0, 19);
        this.doubleLeftBottom.addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
        this.doubleLeftLid = new ModelRenderer(64, 64, 0, 0);
        this.doubleLeftLid.addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
        this.doubleLeftLid.y = 9.0F;
        this.doubleLeftLid.z = 1.0F;
        this.doubleLeftLock = new ModelRenderer(64, 64, 0, 0);
        this.doubleLeftLock.addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
        this.doubleLeftLock.y = 8.0F;
        this.doubleRightBottom = new ModelRenderer(64, 64, 0, 19);
        this.doubleRightBottom.addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
        this.doubleRightLid = new ModelRenderer(64, 64, 0, 0);
        this.doubleRightLid.addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
        this.doubleRightLid.y = 9.0F;
        this.doubleRightLid.z = 1.0F;
        this.doubleRightLock = new ModelRenderer(64, 64, 0, 0);
        this.doubleRightLock.addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
        this.doubleRightLock.y = 8.0F;
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        World world = tileEntityIn.getLevel();
        boolean flag = world != null;
        BlockState blockstate = flag ? tileEntityIn.getBlockState() : itemRenderBlock.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockstate.getBlock();

        if (block instanceof MythChestBlock) {
            MythChestBlock chestBlock = (MythChestBlock) block;
            boolean flag1 = chesttype != ChestType.SINGLE;
            matrixStackIn.pushPose();
            float f = blockstate.getValue(ChestBlock.FACING).toYRot();
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-f));
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            TileEntityMerger.ICallbackWrapper<? extends ChestTileEntity> icallbackwrapper;

            if (flag) {
                icallbackwrapper = chestBlock.combine(blockstate, world, tileEntityIn.getBlockPos(), true);
            } else {
                icallbackwrapper = TileEntityMerger.ICallback::acceptNone;
            }

            float f1 = icallbackwrapper.<Float2FloatFunction>apply(ChestBlock.opennessCombiner(tileEntityIn)).get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = icallbackwrapper.<Int2IntFunction>apply(new DualBrightnessCallback<>()).applyAsInt(combinedLightIn);

            IVertexBuilder vertexBuilder;

            if (this.xmasTextures) {
                vertexBuilder = Atlases.chooseMaterial(tileEntityIn, chesttype, this.xmasTextures).buffer(bufferIn, RenderType::entityCutout);
            }
            else {
                vertexBuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(this.getChestTexture(block, chesttype)));
            }

            if (flag1) {
                if (chesttype == ChestType.LEFT) {
                    this.renderModels(matrixStackIn, vertexBuilder, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, combinedOverlayIn);
                }
                else {
                    this.renderModels(matrixStackIn, vertexBuilder, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, combinedOverlayIn);
                }
            }
            else {
                this.renderModels(matrixStackIn, vertexBuilder, this.lid, this.lock, this.bottom, f1, i, combinedOverlayIn);
            }
            matrixStackIn.popPose();
        }
    }

    private void renderModels(MatrixStack matrixStackIn, IVertexBuilder bufferIn, ModelRenderer lid, ModelRenderer lock, ModelRenderer bottom, float lidAngle, int combinedLightIn, int combinedOverlayIn) {
        lid.xRot = -(lidAngle * ((float)Math.PI / 2F));
        lock.xRot = lid.xRot;
        lid.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        lock.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        bottom.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    }

    protected ResourceLocation getChestTexture(Block block, ChestType chestType) {
        if (block instanceof MythChestBlock) {
            return ((MythChestBlock) block).getChestTexture(chestType);
        }
        return DEFAULT;
    }
}
