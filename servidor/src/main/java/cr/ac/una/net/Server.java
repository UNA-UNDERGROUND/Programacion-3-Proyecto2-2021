package cr.ac.una.net;

import java.nio.channels.AsynchronousServerSocketChannel;

import cr.ac.una.net.io.ConnectionHandler;
import cr.ac.una.net.io.SocketData;

public class Server {

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
        bindServerSocket();
    }

    public Server(int port) {
        this(DEFAULT_HOST, port);
    }

    public Server() {
        this(DEFAULT_PORT);
    }

    private void bindServerSocket() {
        try {
            server = AsynchronousServerSocketChannel.open();
            if (host != null && !host.isEmpty()) {
                server.bind(new java.net.InetSocketAddress(host, port));
            } else {
                server.bind(new java.net.InetSocketAddress(port));
            }
        } catch (Exception ex) {
            System.err.println("Error al iniciar el servidor");
            System.err.println(ex.getMessage());
            throw new RuntimeException("No se pudo iniciar el servidor", ex);
        }
    }

    public void start() throws InterruptedException {
        Thread.currentThread().setName("Servidor");
        boolean defaultHost = host == null || host.isEmpty();
        System.out.println("Servidor abierto en[" + (defaultHost ? "*" : host) + ":" + port + "]");
        SocketData socketData = new SocketData(server);
        server.accept(socketData, new ConnectionHandler());
        Thread.currentThread().join();
    }

    private String host = DEFAULT_HOST;
    private Integer port = DEFAULT_PORT;
    private AsynchronousServerSocketChannel server;

    public static final String DEFAULT_HOST = "";
    public static final int DEFAULT_PORT = 5727;
}
