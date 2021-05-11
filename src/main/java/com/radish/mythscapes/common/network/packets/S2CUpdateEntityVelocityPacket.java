package com.radish.mythscapes.common.network.packets;

import com.radish.mythscapes.common.network.work.ClientPacketWork;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class S2CUpdateEntityVelocityPacket {

    public final double xMotion;
    public final double yMotion;
    public final double zMotion;

    public final int entityId;

    public S2CUpdateEntityVelocityPacket(Entity entity, Vector3d velocity) {
        this(velocity.x, velocity.y, velocity.z, entity.getId());
    }

    public S2CUpdateEntityVelocityPacket(double xMotion, double yMotion, double zMotion, int entityId) {
        this.xMotion = xMotion;
        this.yMotion = yMotion;
        this.zMotion = zMotion;
        this.entityId = entityId;
    }

    public static void handle(S2CUpdateEntityVelocityPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientPacketWork.handleUpdateEntityVelocity(message));
        }
        context.setPacketHandled(true);
    }

    public static S2CUpdateEntityVelocityPacket decode(PacketBuffer buffer) {
        return new S2CUpdateEntityVelocityPacket(buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readInt());
    }

    public static void encode(S2CUpdateEntityVelocityPacket message, PacketBuffer buffer) {
        buffer.writeDouble(message.xMotion);
        buffer.writeDouble(message.yMotion);
        buffer.writeDouble(message.zMotion);
        buffer.writeInt(message.entityId);
    }
}
