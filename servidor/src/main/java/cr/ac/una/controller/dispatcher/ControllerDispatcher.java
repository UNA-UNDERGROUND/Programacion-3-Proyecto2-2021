package cr.ac.una.controller.dispatcher;

import java.util.HashMap;

import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.SocketData;
import cr.ac.una.net.packet.RequestPacket;

/**
 * se encarga de despachar los paquetes recibidos por el cliente para que sean
 * procesados por funciones especificas
 */
public class ControllerDispatcher {

    private ControllerDispatcher() {
    }

    public void dispatch(RequestPacket requestPacket, SocketData client, PacketHandler processor) {
        Context context = null;
        getRequestHandler(requestPacket.getAccion()).handle(requestPacket, context);
    }

    public void addHandler(String action, RequestHandler handler) {
        handlers.put(action, handler);
    }

    private RequestHandler getRequestHandler(String action) {
        if (handlers.containsKey(action)) {
            return handlers.get(action);
        }
        return new RequestHandler() {
            @Override
            public void handle(RequestPacket request, Context context) {
                HashMap<String, String> params = new HashMap<>();
                params.put("status", "invalid-request");
                params.put("error", "se ha recibido una orden no valida");
                context.sendResponse(new RequestPacket("response", params));
            }
        };
    }

    HashMap<String, RequestHandler> handlers = new HashMap<>();
    private static ControllerDispatcher instance;

    public static ControllerDispatcher getInstance() {
        if (instance == null) {
            instance = new ControllerDispatcher();
        }
        return instance;
    }

}
