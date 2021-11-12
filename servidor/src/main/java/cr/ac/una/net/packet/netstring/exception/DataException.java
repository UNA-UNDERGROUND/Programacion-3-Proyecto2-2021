package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando no se puede deserializar los datos del NetString.
 * 
 * 
 * <ul>
 * <li>{@link NotEnoughtBytesException} no hay suficientes bytes para leer el
 * NetString</li>
 * <li>{@link DataDelimiterNotFoundException} no se encuentra el separador de
 * fin de datos</li>
 * </ul>
 */
public class DataException extends NetStringFormatException {
    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

}
