package com.radish.mythscapes.common.network;

import com.radish.mythscapes.common.network.packets.C2SUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdatePlayerEditSignPacket;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

public class NetworkHelper {

    /**
     * Sends a packet from the server to client to update sign text and color.
     */
    public static void updateSignTextToClient(BlockPos signPos, String first, String second, String third, String fourth, int textColor) {
        PacketHandler.CHANNEL.send(PacketDistributor.ALL.noArg(), new S2CUpdateSignTextPacket(signPos, first, second, third, fourth, textColor));
    }

    /**
     * Sends a packet to the client to open
     * the sign edit screen.
     */
    public static void openSignEditorToClient(@NotNull ServerPlayerEntity playerEntity, @NotNull MythSignTileEntity signTileEntity) {
        signTileEntity.setPlayer(playerEntity);
        PacketHandler.CHANNEL.send(PacketDistributor.ALL.noArg(), new S2CUpdatePlayerEditSignPacket(signTileEntity.getBlockPos(), playerEntity.getUUID()));
    }

    /**
     * Sends a packet from the client to server to update sign text.
     */
    public static void updateSignTextToServer(BlockPos signPos, String first, String second, String third, String fourth) {
        PacketHandler.CHANNEL.sendToServer(new C2SUpdateSignTextPacket(signPos, first, second, third, fourth));
    }
}
