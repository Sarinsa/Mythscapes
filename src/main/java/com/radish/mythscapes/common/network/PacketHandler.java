package com.radish.mythscapes.common.network;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.network.packets.C2SUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdatePlayerEditSignPacket;
import net.minecraftforge.fml.WorldPersistenceHooks;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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
    }
}
