package cr.ac.una.net.packet.processor;

import java.util.HashMap;

import cr.ac.una.controller.dispatcher.ControllerDispatcher;
import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.PacketProcessor;
import cr.ac.una.net.io.SocketData;
import cr.ac.una.net.packet.RequestPacket;

/**
 * clase que se encarga de recuperar las solicitudes del servidor para que estas
 * sean despachadas a los clientes mediante el controlador
 */
public class RequestProcessor implements PacketProcessor {

    @Override
    public void process(String message, SocketData client, PacketHandler processor) {
        try {
            RequestPacket request = RequestPacket.parseRequest(message);
            ControllerDispatcher.getInstance().dispatch(request, client, processor);
        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud");
            HashMap<String, String> params = new HashMap<>();
            params.put("status", "malformed-request");
            params.put("error", "Error al procesar la solicitud");
            RequestPacket response = new RequestPacket("response", params);
            sendPacket(response.toString(), client, processor);
        }
    }

    @Override
    public void sendPacket(String message, SocketData client, PacketHandler processor) {
        processor.transferPacket(message, client);
    }

}
