package cr.ac.una.controller;

import cr.ac.una.controller.actions.LoginHandler;
import cr.ac.una.controller.actions.PasswordChangeHandler;
import cr.ac.una.controller.dispatcher.ControllerDispatcher;
import cr.ac.una.net.Server;
import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.PacketProcessor;
import cr.ac.una.net.io.ReadWriteHandler;
import cr.ac.una.net.packet.handler.NetStringPacketHandler;
import cr.ac.una.net.packet.processor.RequestProcessor;

public class ServerController {
    private ServerController() {
        PacketProcessor processor = new RequestProcessor();
        PacketHandler packetHandler = new NetStringPacketHandler(processor);
        rwHandler = new ReadWriteHandler(packetHandler);
        registerActions();
        server = new Server();
    }

    private void registerActions() {
        ControllerDispatcher dispatcher = ControllerDispatcher.getInstance();
        // login
        dispatcher.addHandler("login", new LoginHandler());
        // cambiar contraseña
        dispatcher.addHandler("pwd-change", new PasswordChangeHandler());
    }

    public void start() {
        try {
            server.start(rwHandler);
        } catch (InterruptedException e) {
            System.err.println("Error al iniciar el servidor");
        }
    }

    private static ServerController instance;

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    private Server server;
    private ReadWriteHandler rwHandler;
}
