package cr.ac.una;

import cr.ac.una.controller.ServerController;

public final class App {
    public static void main(String[] args) {
        ServerController.getInstance().start();
    }
}
