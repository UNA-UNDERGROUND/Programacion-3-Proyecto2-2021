package cr.ac.una.net.io;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

public class ReadWriteHandler implements CompletionHandler<Integer, SocketData> {
    @Override
    public void completed(Integer result, SocketData socketData) {
        if (result == -1) {
            doClose(socketData);
        } else {
            doWork(socketData);
        }
    }

    void doWork(SocketData socketData) {
        if (socketData.isRead()) {
            String msg = socketData.getData();
            System.out.println("Mensaje recibido[" + socketData.getClientAddr() + "]: " + msg);
        } else {
            // Write to the client
            socketData.sendData(this);
        }
    }

    void doClose(SocketData socketData) {
        try {
            socketData.close();
            System.out.println("Conexion cerrada con el cliente %s" + socketData.getClientAddr());
        } catch (IOException ex) {
            System.err.println("Error closing the client socket");
        }
    }

    @Override
    public void failed(Throwable e, SocketData attach) {
        e.printStackTrace();
    }
}