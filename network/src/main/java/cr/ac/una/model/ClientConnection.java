package cr.ac.una.model;

import java.util.HashMap;

import cr.ac.una.net.NetStringSocket;
import cr.ac.una.net.packet.RequestPacket;

public class ClientConnection {
    public ClientConnection(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public boolean connect(String host, int port) {
        if (socket != null) {
            return false;
        } else {
            socket = new NetStringSocket(host, port);
            return socket.connect();
        }
    }

    public boolean isConnected() {
        return socket != null;
    }

    public boolean disconnect() {
        if (socket == null) {
            return false;
        } else {
            socket.close();
            socket = null;
            return true;
        }
    }

    public RequestPacket send(RequestPacket packet) {
        RequestPacket status;
        try {
            if (isAuthenticated) {
                send(packet);
                String response = socket.receive();
                status = RequestPacket.parseRequest(response);
            } else {
                HashMap<String, String> data = new HashMap<>();
                data.put("status", "requires-login");
                status = new RequestPacket("response", data);
            }
        } catch (Exception e) {
            HashMap<String, String> data = new HashMap<>();
            data.put("status", "unknown-error");
            data.put("exception", e.getMessage());
            status = new RequestPacket("response", data);
        }
        return status;
    }

    public boolean auth() {
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("usuario", usuario);
            params.put("password", password);
            RequestPacket request = new RequestPacket(LOGIN_CMD, params);
            if (!socket.send(request.toString())) {
                isAuthenticated = false;
                return false;
            }
            String response = socket.receive();
            RequestPacket status = RequestPacket.parseRequest(response);
            if (status.getAccion().equals("response") && status.getParametros().get("status").equals("ok")) {
                isAuthenticated = true;
                return true;
            }
            isAuthenticated = false;
            return false;
        } catch (Exception e) {
            isAuthenticated = false;
            System.err.println("Error al autenticar: " + e.getMessage());
            return false;
        }

    }

    private String usuario;
    private String password;
    private boolean isAuthenticated = false;
    private NetStringSocket socket;
    private static final String LOGIN_CMD = "login";
}
