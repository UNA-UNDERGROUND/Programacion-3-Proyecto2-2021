package cr.ac.una.net.packet.processor;

import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.PacketProcessor;
import cr.ac.una.net.io.SocketData;

public class EchoProcessor implements PacketProcessor {

    @Override
    public void process(String message, SocketData client, PacketHandler handler) {
        String ip = client.getClientAddr().toString();
        String mensaje = "EchoProcessor: " + message + " from " + ip;
        System.out.println(mensaje);
        sendPacket(message, client, handler);
    }

    @Override
    public void sendPacket(String message, SocketData client, PacketHandler handler) {
        String mensaje = "Server[EchoProcessor]->" + message;
        System.out.println("(" + (client.getClientAddr()) + ")" + mensaje);
        handler.transferPacket(mensaje, client);
    }

}
