package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion que se lanza cuando ocurre un error en el proceso de serialización
 * o deserialización de un paquete NetString.
 * 
 * <ul>
 * <li>{@link LengthException} no se pudo deserializar la longitud del paquete
 * </li>
 * <li>{@link DataException}no se pudo deserializar los datos del paquete</li>
 * </ul>
 */
public class NetStringFormatException extends Exception {
    public NetStringFormatException(String message) {
        super(message);
    }

    public NetStringFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
