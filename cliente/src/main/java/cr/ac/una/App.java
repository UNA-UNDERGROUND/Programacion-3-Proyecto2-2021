package cr.ac.una;

import cr.ac.una.view.Login;

public final class App {

    public static void main(String[] args) {
        new App().run();
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
