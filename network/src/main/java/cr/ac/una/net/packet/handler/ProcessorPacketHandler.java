package cr.ac.una.net.packet.handler;

import cr.ac.una.net.io.PacketProcessor;

public abstract class ProcessorPacketHandler {
    protected ProcessorPacketHandler(PacketProcessor processor) {
        this.processor = processor;
    }

    protected PacketProcessor processor;
}
