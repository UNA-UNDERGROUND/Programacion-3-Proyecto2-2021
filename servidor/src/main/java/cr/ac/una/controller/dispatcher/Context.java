package cr.ac.una.controller.dispatcher;

import cr.ac.una.model.Usuario;
import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.SocketData;
import cr.ac.una.net.packet.RequestPacket;

public class Context {

    public Context(SocketData socketData, PacketHandler packetHandler) {
        this.socketData = socketData;
        this.packetHandler = packetHandler;
    }

    public void sendResponse(RequestPacket response) {
        packetHandler.transferPacket(response.toString(), socketData);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private SocketData socketData;
    private PacketHandler packetHandler;
    private Usuario usuario;
}
