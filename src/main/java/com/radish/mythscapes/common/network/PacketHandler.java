package com.radish.mythscapes.common.network;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.network.packets.C2SUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.S2CUpdateSignTextPacket;
import com.radish.mythscapes.common.network.packets.CUpdatePlayerEditSignPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
        registerServerToClient(CUpdatePlayerEditSignPacket.class, CUpdatePlayerEditSignPacket::encode, CUpdatePlayerEditSignPacket::decode, CUpdatePlayerEditSignPacket::handle);
        registerServerToClient(S2CUpdateSignTextPacket.class, S2CUpdateSignTextPacket::encode, S2CUpdateSignTextPacket::decode, S2CUpdateSignTextPacket::handle);

        registerClientToServer(C2SUpdateSignTextPacket.class, C2SUpdateSignTextPacket::encode, C2SUpdateSignTextPacket::decode, C2SUpdateSignTextPacket::handle);
    }

    private <M> void registerClientToServer(Class<M> type, BiConsumer<M, PacketBuffer> encoder, Function<PacketBuffer, M> decoder, BiConsumer<M, Supplier<NetworkEvent.Context>> consumer) {
        CHANNEL.registerMessage(messageIndex++, type, encoder, decoder, consumer, Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    private <M> void registerServerToClient(Class<M> type, BiConsumer<M, PacketBuffer> encoder, Function<PacketBuffer, M> decoder, BiConsumer<M, Supplier<NetworkEvent.Context>> consumer) {
        CHANNEL.registerMessage(messageIndex++, type, encoder, decoder, consumer, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}
