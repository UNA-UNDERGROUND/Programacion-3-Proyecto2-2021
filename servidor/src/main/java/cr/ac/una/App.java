package cr.ac.una;

import cr.ac.una.net.Server;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        try {
            new Server().start();
        } catch (InterruptedException e) {
            System.err.println("Error al iniciar el servidor");
        }
    }
}
