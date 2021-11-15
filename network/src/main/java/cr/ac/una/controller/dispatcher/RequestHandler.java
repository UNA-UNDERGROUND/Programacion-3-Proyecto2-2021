package cr.ac.una.controller.dispatcher;

import cr.ac.una.net.packet.RequestPacket;

public interface RequestHandler {
    public void handle(RequestPacket request, Context context);
}
