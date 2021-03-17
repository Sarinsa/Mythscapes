package com.radish.mythscapes.common.blocks.compat;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.core.config.ConfigHelpers;
import com.radish.mythscapes.common.core.config.helpers.QuarkConfigHelper;
import com.radish.mythscapes.common.network.NetworkHelper;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ModAbstractSignBlock extends AbstractSignBlock {

    public static final List<AbstractSignBlock> SIGN_BLOCKS = new ArrayList<>();
    public static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("textures/entity/signs/oak.png");

    private final String textureName;

    public ModAbstractSignBlock(Properties propertiesIn, String textureName) {
        super(propertiesIn, WoodType.OAK);
        this.textureName = textureName;
        SIGN_BLOCKS.add(this);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemStack = player.getItemInHand(handIn);
        boolean flag = itemStack.getItem() instanceof DyeItem && player.abilities.mayBuild;

        if (worldIn.isClientSide) {
            return flag ? ActionResultType.SUCCESS : ActionResultType.CONSUME;
        }
        else {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);

            if (tileEntity instanceof MythSignTileEntity) {
                MythSignTileEntity signTileEntity = (MythSignTileEntity) tileEntity;

                if (flag) {
                    boolean changeTextColor = signTileEntity.setTextColor(((DyeItem) itemStack.getItem()).getDyeColor());

                    if (changeTextColor) {
                        NetworkHelper.updateSignTextToClient(
                                pos,
                                signTileEntity.getText(0).getString(),
                                signTileEntity.getText(1).getString(),
                                signTileEntity.getText(2).getString(),
                                signTileEntity.getText(3).getString(),
                                ((DyeItem)itemStack.getItem()).getDyeColor().getId()
                        );

                        if (!player.isCreative()) {
                            itemStack.shrink(1);
                        }
                    }
                }
                else {
                    if (ConfigHelpers.QUARK_CONFIG_HELPER.isPresent()) {
                        QuarkConfigHelper configHelper = ConfigHelpers.QUARK_CONFIG_HELPER.orElse(null);

                        if (configHelper.signEditingEnabled() &&!player.isCrouching() && player.abilities.mayBuild){
                            if (configHelper.signEditRequireEmptyHand() && !itemStack.isEmpty())
                                return ActionResultType.PASS;

                            NetworkHelper.openSignEditorToClient((ServerPlayerEntity) player, signTileEntity);
                            return ActionResultType.SUCCESS;
                        }
                    }
                }
                return signTileEntity.executeCommand(player) ? ActionResultType.SUCCESS : ActionResultType.PASS;
            }
            else {
                return ActionResultType.PASS;
            }
        }
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    public ResourceLocation getSignTexture() {
        return Mythscapes.resourceLoc("textures/tile/signs/" + this.textureName + ".png");
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader worldIn) {
        return new MythSignTileEntity();
    }
}
