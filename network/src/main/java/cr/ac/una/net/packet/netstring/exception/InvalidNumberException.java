package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando el numero de bytes no es un numero entero o no se
 * puede serializar a uno.
 * 
 * ejemplos:
 * <ul>
 * <li>1.0</li>
 * <li>1,0</li>
 * <li>1.2.3</li>
 * <li>1,2,3</li>
 * <li>uno</li>
 * </ul>
 */
public class InvalidNumberException extends LengthException {
    public InvalidNumberException(String message) {
        super(message);
    }

    public InvalidNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}