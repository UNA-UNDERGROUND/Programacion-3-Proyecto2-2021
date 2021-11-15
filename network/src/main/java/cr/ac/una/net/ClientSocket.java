package cr.ac.una.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

    public ClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean send(String msg) {
        try {
            writer.print(msg);
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
        }
    }

    private String host;
    private int port;
    private Socket socket;
    private OutputStream output;
    private PrintWriter writer;
    private InputStream input;
    // InputStreamReader reader;
    protected BufferedReader reader;
}
