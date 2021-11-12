package cr.ac.una.net.packet.handler;

import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.PacketProcessor;
import cr.ac.una.net.io.SocketData;
import cr.ac.una.net.packet.netstring.NetString;

public class NetStringPacketHandler extends ProcessorPacketHandler implements PacketHandler {

    public NetStringPacketHandler(PacketProcessor processor) {
        super(processor);
    }

    @Override
    public int processPacket(String buffer, SocketData client) {
        try {
            // verificamos si hay una nueva linea
            if (buffer.contains("\n")) {
                // obtenemos el indice de la linea
                int index = buffer.indexOf("\n");
                // verificamos si tiene un retorno de carro
                boolean hasCR = buffer.charAt(index - 1) == '\r';
                // obtenemos el desplazamiento sensible al retorno de carro
                // obtenemos la linea
                String linea;
                // si la nueva linea es el inicio entonces esta vacio
                if (index == 0 || (hasCR && index == 1)) {
                    linea = "";
                } else {
                    // obtenemos la linea sin el salto de linea
                    linea = buffer.substring(0, index - (hasCR ? 1 : 0));
                }
                // removemos la linea nueva del mensaje
                // y eliminamos el mensaje de la lista
                int bytes = linea.length() + (hasCR ? 2 : 1);
                processor.process(linea, client, this);
                // retornamos la cantidad de bytes recolectados
                return bytes;
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public void transferPacket(String packet, SocketData client) {
        client.sendData(new NetString(packet).toString());
    }

}
