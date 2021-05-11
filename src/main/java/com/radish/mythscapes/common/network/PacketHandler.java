package com.radish.mythscapes.common.network;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.network.packets.C2SUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdateEntityVelocityPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdatePlayerEditSignPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.WorldPersistenceHooks;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.Function;

public class PacketHandler {

    private static final String PROTOCOL_NAME = "MYTH";
    public static final SimpleChannel CHANNEL = createChannel();

    private int messageIndex;

    private static SimpleChannel createChannel() {
        return NetworkRegistry.ChannelBuilder
                .named(Mythscapes.resourceLoc("channel"))
                .serverAcceptedVersions(PROTOCOL_NAME::equals)
                .clientAcceptedVersions(PROTOCOL_NAME::equals)
                .networkProtocolVersion(() -> PROTOCOL_NAME)
                .simpleChannel();
    }

    public void registerMessages() {
        CHANNEL.registerMessage(messageIndex++, S2CUpdatePlayerEditSignPacket.class, S2CUpdatePlayerEditSignPacket::encode, S2CUpdatePlayerEditSignPacket::decode, S2CUpdatePlayerEditSignPacket::handle);
        CHANNEL.registerMessage(messageIndex++, S2CUpdateSignTextPacket.class, S2CUpdateSignTextPacket::encode, S2CUpdateSignTextPacket::decode, S2CUpdateSignTextPacket::handle);
        CHANNEL.registerMessage(messageIndex++, C2SUpdateSignTextPacket.class, C2SUpdateSignTextPacket::encode, C2SUpdateSignTextPacket::decode, C2SUpdateSignTextPacket::handle);
        CHANNEL.registerMessage(messageIndex++, S2CUpdateEntityVelocityPacket.class, S2CUpdateEntityVelocityPacket::encode, S2CUpdateEntityVelocityPacket::decode, S2CUpdateEntityVelocityPacket::handle);
    }

    /**
     * Sends the specified message to the client.
     *
     * @param message The message to send to the client.
     * @param player The player client that should receive this message.
     * @param <MSG> Packet type.
     */
    public static <MSG> void sendToClient(MSG message, ServerPlayerEntity player) {
        CHANNEL.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
}
