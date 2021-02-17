package com.radish.mythscapes.common.network.work;

import com.radish.mythscapes.common.network.NetworkHelper;
import com.radish.mythscapes.common.network.packets.C2SUpdateSignTextPacket;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class ServerPacketWork {

    public static void handleUpdateSignText(C2SUpdateSignTextPacket message, @Nullable ServerPlayerEntity playerEntity) {
        if (playerEntity == null)
            return;

        ServerWorld serverWorld = playerEntity.getServerWorld();
        BlockPos signPos = message.pos;
        TileEntity tileEntity = serverWorld.getTileEntity(signPos);

        if (tileEntity instanceof MythSignTileEntity) {
            MythSignTileEntity signTileEntity = (MythSignTileEntity) tileEntity;
            BlockState blockState = serverWorld.getBlockState(signPos);
            String[] text = message.signText;

            if (!signTileEntity.getIsEditable() || signTileEntity.getPlayer() != playerEntity)
                return;

            for (int i = 0; i < text.length; i++) {
                signTileEntity.setText(i, new StringTextComponent(TextFormatting.getTextWithoutFormattingCodes(text[i])));
            }
            signTileEntity.markDirty();
            serverWorld.notifyBlockUpdate(signPos, blockState, blockState, 3);

            NetworkHelper.updateSignTextToClient(signPos, text[0], text[1], text[2], text[3], signTileEntity.getTextColor().getId());
        }
    }
}
