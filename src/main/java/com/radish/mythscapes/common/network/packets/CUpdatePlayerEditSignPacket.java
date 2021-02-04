package com.radish.mythscapes.common.network.packets;

import com.radish.mythscapes.client.screen.ModSignScreen;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CUpdatePlayerEditSignPacket {

    private final UUID uuid;
    private final BlockPos pos;

    public CUpdatePlayerEditSignPacket(BlockPos signPos, UUID uuid) {
        this.pos = signPos;
        this.uuid = uuid;
    }

    @SuppressWarnings("all")
    public static void handle(CUpdatePlayerEditSignPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> {
                if (!Minecraft.getInstance().player.getUniqueID().equals(message.uuid))
                    return;

                BlockPos pos = message.pos;
                ClientWorld world = Minecraft.getInstance().world;
                TileEntity tileEntity = world.getTileEntity(pos);

                if (tileEntity == null || !(tileEntity instanceof MythSignTileEntity)) {
                    tileEntity = new MythSignTileEntity();
                    tileEntity.setWorldAndPos(world, pos);
                }
                Minecraft.getInstance().displayGuiScreen(new ModSignScreen((MythSignTileEntity) tileEntity));
            });
        }
        context.setPacketHandled(true);
    }

    public static CUpdatePlayerEditSignPacket decode(PacketBuffer buffer) {
        return new CUpdatePlayerEditSignPacket(buffer.readBlockPos(), buffer.readUniqueId());
    }

    public static void encode(CUpdatePlayerEditSignPacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeUniqueId(packet.uuid);
    }
}
