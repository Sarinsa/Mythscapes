package com.radish.mythscapes.common.network.work;

import com.radish.mythscapes.client.screen.ModSignScreen;
import com.radish.mythscapes.common.network.packets.S2CUpdatePlayerEditSignPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdateSignTextPacket;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.DyeColor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

@SuppressWarnings("all")
public class ClientPacketWork {

    public static void handleEditSign(S2CUpdatePlayerEditSignPacket message) {
        if (!Minecraft.getInstance().player.getUUID().equals(message.uuid))
            return;

        BlockPos pos = message.pos;
        ClientWorld world = Minecraft.getInstance().level;
        TileEntity tileEntity = world.getBlockEntity(pos);

        if (tileEntity == null || !(tileEntity instanceof MythSignTileEntity)) {
            tileEntity = new MythSignTileEntity();
            tileEntity.setLevelAndPosition(world, pos);
        }
        Minecraft.getInstance().setScreen(new ModSignScreen((MythSignTileEntity) tileEntity));
    }

    public static void handleUpdateSignText(S2CUpdateSignTextPacket message) {
        ClientWorld world = Minecraft.getInstance().level;
        BlockPos signPos = message.pos;

        if (world == null || !world.isAreaLoaded(signPos, 1))
            return;

        TileEntity tileEntity = world.getBlockEntity(signPos);

        if (tileEntity instanceof MythSignTileEntity) {
            MythSignTileEntity signTileEntity = (MythSignTileEntity) tileEntity;

            for (int i = 0; i < message.signText.length; i++) {
                signTileEntity.setText(i, new StringTextComponent(TextFormatting.stripFormatting(message.signText[i])));
            }
            signTileEntity.setTextColor(DyeColor.byId(message.textColor));
        }
    }
}
