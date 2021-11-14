package cr.ac.una.net;

import cr.ac.una.net.packet.netstring.NetString;

public class NetStringSocket extends ClientSocket {
    public NetStringSocket(String host, int port) {
        super(host, port);
    }

    public boolean send(String message) {
        return super.send(new NetString(message).toString());
    }

    public String receive() {
        try {
            NetString netString = NetString.parseNetString(reader);
            return netString.getData();
        } catch (Exception e) {
            throw new RuntimeException("Error de conexion", e);
        }
    }

}
