package com.radish.mythscapes.common.network.packets;

import com.radish.mythscapes.common.network.NetworkHelper;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SUpdateSignTextPacket {

    private final String[] signText;
    private final BlockPos pos;

    public C2SUpdateSignTextPacket(BlockPos signPos, String first, String second, String third, String fourth) {
        this.pos = signPos;
        this.signText = new String[] {first, second, third, fourth};;
    }

    @SuppressWarnings("all")
    public static void handle(C2SUpdateSignTextPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isServer()) {
            context.enqueueWork(() -> {
                ServerPlayerEntity playerEntity = context.getSender();
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
            });
        }
        context.setPacketHandled(true);
    }

    public static C2SUpdateSignTextPacket decode(PacketBuffer buffer) {
        return new C2SUpdateSignTextPacket(buffer.readBlockPos(), buffer.readString(), buffer.readString(), buffer.readString(), buffer.readString());
    }

    public static void encode(C2SUpdateSignTextPacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);

        for (String s : packet.signText)
            buffer.writeString(s);
    }
}
