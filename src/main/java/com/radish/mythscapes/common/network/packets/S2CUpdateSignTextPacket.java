package com.radish.mythscapes.common.network.packets;

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

    private final String[] signText;
    private final BlockPos pos;
    private final int textColor;

    public S2CUpdateSignTextPacket(BlockPos signPos, String first, String second, String third, String fourth, int textColor) {
        this.pos = signPos;
        this.signText = new String[] {first, second, third, fourth};
        this.textColor = textColor;
    }

    @SuppressWarnings("all")
    public static void handle(S2CUpdateSignTextPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> {
                ClientWorld world = Minecraft.getInstance().world;
                BlockPos signPos = message.pos;

                if (world == null || !world.isAreaLoaded(signPos, 1))
                    return;

                TileEntity tileEntity = world.getTileEntity(signPos);

                if (tileEntity instanceof MythSignTileEntity) {
                    MythSignTileEntity signTileEntity = (MythSignTileEntity) tileEntity;

                    for (int i = 0; i < message.signText.length; i++) {
                        signTileEntity.setText(i, new StringTextComponent(TextFormatting.getTextWithoutFormattingCodes(message.signText[i])));
                    }
                    signTileEntity.setTextColor(DyeColor.byId(message.textColor));
                }
            });
        }
    }

    public static S2CUpdateSignTextPacket decode(PacketBuffer buffer) {
        return new S2CUpdateSignTextPacket(buffer.readBlockPos(), buffer.readString(), buffer.readString(), buffer.readString(), buffer.readString(), buffer.readInt());
    }

    public static void encode(S2CUpdateSignTextPacket packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);

        for (String s : packet.signText)
            buffer.writeString(s);

        buffer.writeInt(packet.textColor);
    }
}
