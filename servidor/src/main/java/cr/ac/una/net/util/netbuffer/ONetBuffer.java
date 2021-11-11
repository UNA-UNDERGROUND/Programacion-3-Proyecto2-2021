package cr.ac.una.net.util.netbuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
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

    /**
     * mueve la mayor cantidad de bytes posibles al buffer de bytes y los remueve de
     * la lista de bytes
     * 
     * @param buffer Buffer de bytes
     * @param data   Lista de bytes
     */
    private void fillBBuffer() {
        // copia los datos al buffer de bytes y obtiene la cantidad de bytes copiados
        int movidos = pushBBufferData();
        // remueve los bytes copiados de la lista de bytes
        data = new ArrayList<>(data.subList(movidos, data.size()));
    }

    /**
     * Agrega bytes al buffer de bytes la cantidad de bytes agregados es el maximo
     * que puede agregar al buffer o la cantidad de bytes que tiene el string
     * 
     * @param buffer Buffer de bytes
     * @param data   Cadena de texto
     * @return numero de bytes agregados al buffer
     */
    private int pushBBufferData() {
        // vefifica si el buffer esta lleno
        if (buffer.remaining() < 1) {
            return 0;
        }
        // calcula el tamaño de los datos a agregar
        int size = data.size();
        // calcula el tamaño de los bytes que se pueden agregar
        int limit = buffer.limit() - buffer.position();
        // calcula el tamaño de los bytes se van a enviar
        int length = Math.min(size, limit);

        // recuperamos los bytes del buffer que podemos agregar
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = data.get(i);
        }

        // agregamos los bytes al buffer
        buffer.put(bytes);

        return length;
    }

}
