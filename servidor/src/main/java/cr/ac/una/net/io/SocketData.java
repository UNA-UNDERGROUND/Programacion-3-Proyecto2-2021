package cr.ac.una.net.io;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class SocketData {
    public SocketData(AsynchronousServerSocketChannel server) {
        this.server = server;
    }

    public void accept(SocketData data, ConnectionHandler handler) {
        this.server.accept(data, handler);
    }

    public SocketData createSocketData(AsynchronousSocketChannel client) {
        SocketData socketData = new SocketData(this.server);
        socketData.client = client;
        socketData.buffer = ByteBuffer.allocate(BUFFER_SIZE);
        socketData.isRead = true;
        socketData.clientAddr = clientAddr;
        return socketData;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public String getData() {
        buffer.flip();
        byte bytes[] = new byte[buffer.limit()];
        getBuffer().get(bytes, 0, buffer.limit());
        String data = new String(bytes, CHARSET);
        isRead = false;
        buffer.rewind();
        return data;
    }

    public void sendData(ReadWriteHandler handler) {
        client.write(buffer, this, handler);
        isRead = true;
        buffer.clear();
        client.read(buffer, this, handler);
    }

    public SocketAddress getClientAddr() {
        return clientAddr;
    }

    public boolean isRead() {
        return isRead;
    }

    public void close() throws IOException {
        client.close();
    }

    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private ByteBuffer buffer;
    private SocketAddress clientAddr;
    private boolean isRead;

    private static final int BUFFER_SIZE = 2048;
    private static final Charset CHARSET = Charset.forName("UTF-8");
}