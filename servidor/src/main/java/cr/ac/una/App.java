package cr.ac.una;

import cr.ac.una.net.Server;


public final class App {
    public static void main(String[] args) {
        try {
            new Server().start();
        } catch (InterruptedException e) {
            System.err.println("Error al iniciar el servidor");
        }
    }
}
