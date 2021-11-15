package cr.ac.una.net.io;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import cr.ac.una.net.util.netbuffer.IONetBuffer;

public class SocketData {
    public SocketData(AsynchronousServerSocketChannel server) {
        this.server = server;
    }

    public void accept(SocketData data, ConnectionHandler handler) {
        this.server.accept(data, handler);
    }

    public SocketData createSocketData(AsynchronousSocketChannel client, ReadWriteHandler handler) throws IOException {
        SocketData socketData = new SocketData(this.server);
        socketData.client = client;
        socketData.buffer = new IONetBuffer();
        // socketData.inputBBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        socketData.clientAddr = client.getRemoteAddress();
        socketData.handler = handler;
        return socketData;
    }

    public synchronized String getData() {
        Byte[] byteArray = buffer.getInputBuffer().getData().toArray(new Byte[0]);
        byte[] bytes = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            bytes[i] = byteArray[i];
        }
        String data = new String(bytes, CHARSET);
        return data;
    }

    public synchronized void popData(int bytes) {
        buffer.getInputBuffer().popData(bytes);
    }

    public synchronized void sendData(String data) {
        byte[] bytes = data.getBytes(CHARSET);
        List<Byte> list = new ArrayList<Byte>();
        for (byte b : bytes) {
            list.add(b);
        }
        buffer.getOutputBuffer().pushData(list);
        doSend();
    }

    public synchronized void doRead() {
        client.read(buffer.getInputBuffer().getBuffer(), this, this.handler);
    }

    public synchronized void doSend() {
        client.write(buffer.getOutputBuffer().popBuffer(), this, handler);
    }

    public synchronized SocketAddress getClientAddr() {
        return clientAddr;
    }

    public synchronized boolean needWrite() {
        // verificamos si el buffer de salida esta vacio
        return !buffer.getOutputBuffer().isEmpty();
    }

    public synchronized void close() throws IOException {
        client.close();
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Object getExtra() {
        return extra;
    }

    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private IONetBuffer buffer;
    // private ByteBuffer inputBBuffer;
    // private ByteBuffer outputBBuffer;
    private SocketAddress clientAddr;
    private ReadWriteHandler handler;
    private Object extra = null;

    // private ArrayList<Byte> outputBuffer;

    private static final Charset CHARSET = Charset.forName("UTF-8");
}