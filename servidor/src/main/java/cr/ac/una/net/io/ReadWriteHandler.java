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
        try {
            doSend(socketData);
            doRead(socketData);
        } catch (Exception e) {
            System.err.println("Error de conexion con el cliente [" + socketData.getClientAddr() + "]: "
                    + e.getLocalizedMessage());
            doClose(socketData);
        }

    }

    void doRead(SocketData socketData) {
        if (socketData.isRead()) {
            String buffer = socketData.getData();
            // verificamos si tiene una nueva linea
            if (buffer.contains("\n")) {
                // si tiene una nueva linea, la separamos (sin quitarse el \n)
                String[] msgs = buffer.split("\n");
                // obtenemos el mensaje con la nueva linea
                String mensaje = msgs[0] + "\n";
                // y eliminamos el mensaje de la lista
                int bytes = mensaje.length();
                socketData.popData(bytes);
                System.out.println("Mensaje recibido[" + socketData.getClientAddr() + "]: " + mensaje);
                socketData.sendData("Server->" + mensaje);
            } else {
                socketData.doRead();
            }
        }
    }

    void doSend(SocketData socketData) {
        if (socketData.needRead()) {
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