package cr.ac.una;

import cr.ac.una.net.Server;
import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.PacketProcessor;
import cr.ac.una.net.io.ReadWriteHandler;
import cr.ac.una.net.packet.handler.SimplePacketHandler;
import cr.ac.una.net.packet.processor.EchoProcessor;

public final class App {
    public static void main(String[] args) {
        try {
            PacketProcessor processor = new EchoProcessor();
            PacketHandler packetHandler = new SimplePacketHandler(processor);
            ReadWriteHandler rwHandler = new ReadWriteHandler(packetHandler);
            new Server().start(rwHandler);
        } catch (InterruptedException e) {
            System.err.println("Error al iniciar el servidor");
        }
    }
}
