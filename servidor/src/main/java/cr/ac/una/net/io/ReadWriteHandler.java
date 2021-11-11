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
            String buffer = socketData.getData();
            // verificamos si tiene una nueva linea
            if (buffer.contains("\n")) {
                // si tiene una nueva linea, la separamos
                String[] msgs = buffer.split("\n");
                // obtenemos el mensaje
                String mensaje = msgs[0];
                // y eliminamos el mensaje de la lista
                int bytes = mensaje.length() + 1;
                socketData.popData(bytes);
                System.out.println("Mensaje recibido[" + socketData.getClientAddr() + "]: " + mensaje);
                socketData.sendData("Server->" + mensaje);
            } else {
                socketData.doRead();
            }
        } else {
            socketData.doSend();
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