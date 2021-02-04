package com.radish.mythscapes.common.network.packets;

import com.radish.mythscapes.common.network.work.ClientPacketWork;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class S2CUpdatePlayerEditSignPacket {

    public final UUID uuid;
    public final BlockPos pos;

    public S2CUpdatePlayerEditSignPacket(BlockPos signPos, UUID uuid) {
        this.pos = signPos;
        this.uuid = uuid;
    }

    @SuppressWarnings("all")
    public static void handle(S2CUpdatePlayerEditSignPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientPacketWork.handleEditSign(message));
        }
        context.setPacketHandled(true);
    }

    public static S2CUpdatePlayerEditSignPacket decode(PacketBuffer buffer) {
        return new S2CUpdatePlayerEditSignPacket(buffer.readBlockPos(), buffer.readUniqueId());
    }

    public static void encode(S2CUpdatePlayerEditSignPacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeUniqueId(packet.uuid);
    }
}
