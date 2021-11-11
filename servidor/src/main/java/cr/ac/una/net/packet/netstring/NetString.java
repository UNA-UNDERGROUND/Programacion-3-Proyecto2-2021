package cr.ac.una.net.packet.netstring;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Clase que representa un paquete de datos. los datos estan codificados como un
 * netstring ej: "5:Hello"
 */
public class NetString {

    /**
     * Crea un nuevo paquete de datos
     */
    public NetString() {
    }

    /**
     * Crea un nuevo paquete de datos a partir de una cadena de texto
     * 
     * @param data cadena de texto
     */
    public NetString(String data) {
        this.data = data;
    }

    /**
     * Crea un nuevo paquete de datos a partir de una cadena de texto
     * 
     * @param data
     */
    public NetString(byte[] data) {
        this.data = new String(data);
    }

    /**
     * Cambia el contenido del paquete de datos
     * 
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Obtiene el String de datos
     * 
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * Desserializa un paquete de datos codificado como un netstring, retorna nulo
     * si no hay bytes por leer
     * 
     * @param stream Buffer de entrada
     * @return Paquete de datos
     */
    public static NetString parseNetString(Reader reader) {
        try {
            Integer longitud = getNetStringLength(reader);
            if (longitud != null) {
                String data = readBytes(reader, longitud);
                return new NetString(data);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Cabecera invalida: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Desserializa un paquete de datos codificado como un netstring
     * 
     * @param netString texto codificado como netstring
     * @return Paquete de datos
     */
    public static NetString parseNetString(String netString) {
        InputStream stream = new ByteArrayInputStream(netString.getBytes());
        return parseNetString(new InputStreamReader(stream));
    }

    /**
     * Serializa un paquete de datos como un netstring
     */
    @Override
    public String toString() {
        return data.getBytes().length + SEPARATOR + data + SEPARATOR_DATA;
    }

    /**
     * Compara dos paquetes de datos o con otro NetString
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return this.getData().equals(obj);
        }
        if (obj instanceof NetString) {
            NetString netString = (NetString) obj;
            return netString.getData().equals(this.getData());
        }
        return true;
    }

    /**
     * Lee la longitud del paquete de datos del netString, retorna nulo si no hay
     * bytes por leer
     * 
     * @param buffer Buffer de entrada
     * @return Longitud del paquete de datos
     */
    private static Integer getNetStringLength(Reader buffer) {
        try {
            StringBuilder sb = new StringBuilder();
            int r;
            while ((r = buffer.read()) != -1) {
                Character c = (char) r;
                if (c.toString().equals(SEPARATOR)) {
                    break;
                }
                sb.append(c);
            }
            if (sb.length() == 0) {
                return null;
            }
            return Integer.parseInt(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el paquete");
        }
    }

    /**
     * Lee la cantidad de bytes del paquete de datos del netString
     * 
     * @param reader Buffer de entrada
     * @param bytes  bytes a leer del paquete de datos
     * @return
     */
    private static String readBytes(Reader reader, int bytes) {
        StringBuilder sb = new StringBuilder();
        int leidos = 0;
        try {
            int r;
            while (leidos < bytes) {
                r = reader.read();
                if (r == -1) {
                    throw new RuntimeException("Underflow Error");
                }
                sb.append((char) r);
                leidos++;
            }
            r = reader.read();
            Character c = (char) r;
            if (c.toString().equals(SEPARATOR_DATA)) {
                return sb.toString();
            } else {
                throw new RuntimeException("Separador invalido");
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el paquete");
        }
    }

    private String data = "";

    public static final String SEPARATOR = ":";
    public static final String SEPARATOR_DATA = ";";
}
