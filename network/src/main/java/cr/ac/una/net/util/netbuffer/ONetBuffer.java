package cr.ac.una.net.util.netbuffer;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Clase que representa un buffer de datos de salida.
 */
public class ONetBuffer extends NetBuffer {
    public ONetBuffer() {
        super();
    }

    /**
     * push data to the buffer
     * 
     * @param data
     */
    public void pushData(List<Byte> data) {
        this.data.addAll(data);
        // fillBBuffer();
    }

    /**
     * pop data from the buffer and fill it again
     * 
     * @return data in the buffer
     */
    // public ByteBuffer popBuffer() {
    // ByteBuffer bufferData = buffer;
    // buffer = ByteBuffer.allocate(BUFFER_SIZE);
    // fillBBuffer();
    // return bufferData;
    // }

    public ByteBuffer popBuffer() {
        // primero recuperamos la lista de bytes
        byte[] bytes = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            bytes[i] = data.get(i);
        }
        ByteBuffer bufferData = ByteBuffer.wrap(bytes);
        // luego limpiamos la lista de bytes
        data.clear();
        return bufferData;
    }

    /**
     * check if the buffer is empty or does not have pending data
     * 
     * @return true if the buffer is empty
     */
    public boolean isEmpty() {
        return data.isEmpty();
        // llenamos el buffer primero
        // fillBBuffer();
        // verificamos si el buffer de salida esta vacio
        // return buffer.position() == 0;
    }

}
