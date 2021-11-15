package cr.ac.una.net.util.netbuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un buffer de datos de entrada.
 */
public class INetBuffer extends NetBuffer {

    public INetBuffer() {
        super();
    }

    /**
     * recupera la lista de bytes recolectados
     * 
     * @return lista de bytes recolectados
     */
    public List<Byte> getData() {
        pullData();
        return this.data;
    }

    /**
     * remueve una cantidad de bytes de la lista de bytes recolectados
     * 
     * @param bytes cantidad de bytes a remover
     */
    public void popData(int bytes) {
        data = new ArrayList<>(data.subList(bytes, data.size()));
    }

    /**
     * agrega un buffer a la lista de bytes recolectados
     */
    public void pushBuffer(ByteBuffer buffer) {
        pullData();
        this.buffer = buffer;
        pullData();
    }

    /**
     * expone el buffer de datos de entrada para su lectura
     * 
     * @return buffer de datos de entrada
     */
    public ByteBuffer getBuffer() {
        pullData();
        return buffer;
    }

    /**
     * verifica si la lista de bytes esta vacía
     * 
     * @return true si la lista de bytes esta no vacía, false en caso contrario
     */
    public boolean hasData() {
        pullData();
        return !data.isEmpty();
    }

    /**
     * recolecta la lista de bytes disponibles en el buffer y las almacena en la
     * lista de bytes recolectados y limpia el buffer de entrada
     */
    private void pullData() {
        // recupera los bytes disponibles en el buffer
        if (buffer != null) {
            buffer.flip();
            int bytes = buffer.remaining();
            for (int i = 0; i < bytes; i++) {
                data.add(buffer.get());
            }
        }
        this.buffer.clear();
    }
}
