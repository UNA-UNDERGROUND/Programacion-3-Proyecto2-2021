package cr.ac.una.net.util.netbuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class NetBuffer {

    protected NetBuffer() {
    }

    protected ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    protected ArrayList<Byte> data = new ArrayList<>();
    public static final int BUFFER_SIZE = 2048;
}
