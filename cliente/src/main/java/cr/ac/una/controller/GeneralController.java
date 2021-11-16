package cr.ac.una.controller;

import cr.ac.una.model.ClientConnection;

public class GeneralController {

    public boolean login(String user, String password) {
        if (connection == null) {
            connection = new ClientConnection(user, password);
        }
        if (connection.connect(host, port)) {
            return connection.auth();
        }
        return false;
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
