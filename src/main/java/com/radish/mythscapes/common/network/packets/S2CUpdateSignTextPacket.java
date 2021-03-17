package com.radish.mythscapes.common.network.packets;

import com.radish.mythscapes.common.network.work.ClientPacketWork;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.DyeColor;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CUpdateSignTextPacket {

    public final String[] signText;
    public final BlockPos pos;
    public final int textColor;

    public S2CUpdateSignTextPacket(BlockPos signPos, String first, String second, String third, String fourth, int textColor) {
        this.pos = signPos;
        this.signText = new String[] {first, second, third, fourth};
        this.textColor = textColor;
    }

    @SuppressWarnings("all")
    public static void handle(S2CUpdateSignTextPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientPacketWork.handleUpdateSignText(message));
        }
        context.setPacketHandled(true);
    }

    public static S2CUpdateSignTextPacket decode(PacketBuffer buffer) {
        return new S2CUpdateSignTextPacket(buffer.readBlockPos(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readInt());
    }

    public static void encode(S2CUpdateSignTextPacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);

        for (String s : packet.signText)
            buffer.writeUtf(s);

        buffer.writeInt(packet.textColor);
    }
}
