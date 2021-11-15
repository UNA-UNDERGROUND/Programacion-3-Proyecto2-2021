package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando el numero de bytes de una cadena NetString es
 * negativo.
 * 
 * ejemplos:
 * <ul>
 * <li>"-1:hola mundo;"</li>
 * <li>"-2:adios mundo cruel;"</li>
 * <li>"-3:Alcina Dimistrescu;"</li>
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