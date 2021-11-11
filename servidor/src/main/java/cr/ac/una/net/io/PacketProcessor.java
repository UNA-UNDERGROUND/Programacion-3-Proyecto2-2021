package cr.ac.una.net.io;

/**
 * Clase que procesa los paquetes recibidos.
 */
public interface PacketProcessor {
    public void process(String message, SocketData client, PacketHandler processor);

    public void sendPacket(String message, SocketData client, PacketHandler processor);
}
