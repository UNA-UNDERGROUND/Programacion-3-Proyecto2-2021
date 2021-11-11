package cr.ac.una.net.io;

import java.io.IOException;
import java.nio.channels.CompletionHandler;

public class ReadWriteHandler implements CompletionHandler<Integer, SocketData> {
    public ReadWriteHandler(PacketHandler packetHandler) {
        this.packetHandler = packetHandler;
    }

    @Override
    public void completed(Integer result, SocketData socketData) {
        if (result == -1) {
            doClose(socketData);
        } else {
            doWork(socketData);
        }
    }

    void doWork(SocketData socketData) {
        synchronized (socketData) {
            try {
                if (socketData.needWrite()) {
                    doSend(socketData);
                } else {
                    doRead(socketData);
                }
            } catch (Exception e) {
                System.err.println("Error de comunicación con el cliente [" + socketData.getClientAddr() + "]: "
                        + e.getLocalizedMessage());
                doClose(socketData);
            }
        }

    }

    void doRead(SocketData socketData) {

        String buffer = socketData.getData();
        int bytes = packetHandler.processPacket(buffer, socketData);
        if (bytes > 0) {
            socketData.popData(bytes);
        } else if (bytes == -1) {
            doClose(socketData);
        } else {
            socketData.doRead();
        }

    }

    void doSend(SocketData socketData) {
        if (socketData.needWrite()) {
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
        System.err.println(
                "Error de conexion con el cliente [" + attach.getClientAddr() + "]: " + e.getLocalizedMessage());
    }

    private PacketHandler packetHandler;
}