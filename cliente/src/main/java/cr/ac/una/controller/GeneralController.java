package cr.ac.una.controller;

import cr.ac.una.model.ClientConnection;
import cr.ac.una.net.packet.RequestPacket;

public class GeneralController {

    public boolean login(String user, String password) {
        if (connection == null) {
            connection = new ClientConnection(user, password);
        }
        if (connection.connect(host, port)) {
            return connection.auth();
        }
        logout();
        return false;
    }

    public boolean retirarMonto(Float monto) {
        if (connection != null) {
            RequestPacket packet = new RequestPacket("retiro-dinero");
            packet.setParametro("monto", monto.toString());
            RequestPacket response = connection.send(packet);
            return response.getParametro("status").equals("ok");
        }
        return false;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (connection == null) {
            return false;
        }
        RequestPacket packet = new RequestPacket("pwd-change");
        packet.setParametro("originalPassword", oldPassword);
        packet.setParametro("newPassword", newPassword);
        RequestPacket response = connection.send(packet);
        return response.getParametro("status").equals("ok");
    }

    public Float recuperarDinero() {
        try {
            if (connection != null) {
                RequestPacket packet = new RequestPacket("recuperar-saldo");
                // toda solicitud espera almenos 1 parametro
                // de lo contrario se considera un error
                // y el cliente se desconecta
                packet.setParametro("dummy-param", "0");
                RequestPacket response = connection.send(packet);
                if (response.getParametro("status").equals("ok")) {
                    return Float.parseFloat(response.getParametro("monto"));
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void logout() {
        connection.disconnect();
    }

    private ClientConnection connection;

    private static final String host = "localhost";
    private static final int port = 5727;
    // singleton
    private static GeneralController instance = null;

    public static GeneralController getInstance() {
        if (instance == null) {
            instance = new GeneralController();
        }
        return instance;
    }
}
