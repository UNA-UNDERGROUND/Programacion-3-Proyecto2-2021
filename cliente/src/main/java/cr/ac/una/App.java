package cr.ac.una;

import cr.ac.una.model.ClientConnection;
import cr.ac.una.view.Login;

public final class App {

    public static void main(String[] args) {
        new App().testLogin();
        // new App().run();
    }

    void testLogin() {
        ClientConnection connection = new ClientConnection("dummy", "dummy123");
        if (!connection.connect("localhost", 5727)) {
            System.err.println("No se pudo conectar con el servidor");
            return;
        }
        if (!connection.auth()) {
            System.err.println("No se pudo loguear");
            return;
        }
        System.out.println("Login exitoso");
    }

    void run() {
        try {
            // aspecto nativo del sistema operativo
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Login().setVisible(true);
                }
            });
        } catch (Exception ex) {
            System.err.println("Error al iniciar el cliente");
        }
    }
}
