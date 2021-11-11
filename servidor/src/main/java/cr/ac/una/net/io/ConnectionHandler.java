package cr.ac.una.net.io;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Se encarga de manejar las conexiones de sockets entrantes. asigna una
 * conexion a un hilo para que la atienda. de manera que no se bloquee el
 * servidor.
 * 
 */
public class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, SocketData> {
    @Override
    public void completed(AsynchronousSocketChannel client, SocketData serverAttach) {
        try {
            SocketAddress clientAddr = client.getRemoteAddress();
            System.out.println("Conectado cliente desde: " + clientAddr);
            serverAttach.accept(serverAttach, this);
            ReadWriteHandler rwHandler = new ReadWriteHandler();
            SocketData clientAttatch = serverAttach.createSocketData(client, rwHandler);
            clientAttatch.doRead();
            // client.read(newAttach.getInputBBuffer(), newAttach, rwHandler);
        } catch (IOException e) {
            System.err.println("Error al procesar el cliente");
        }
    }

    @Override
    public void failed(Throwable e, SocketData attach) {
        System.err.println("Error al conectar con cliente");
        System.err.println(e.getMessage());
    }
}