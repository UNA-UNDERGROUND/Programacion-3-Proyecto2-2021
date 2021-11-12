package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando el numero de bytes de una cadena NetString es
 * negativo.
 * 
 * ejemplos:
 * <ul>
 * <li>-1</li>
 * <li>-2</li>
 * <li>-3</li>
 * </ul>
 */
public class NegativeLengthException extends LengthException {
    public NegativeLengthException(String message) {
        super(message);
    }

    public NegativeLengthException(String message, Throwable cause) {
        super(message, cause);
    }
}