package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando no se puede deserializar la longitud del NetString
 * 
 * <ul>
 * <li>{@link NegativeLengthException} El numero de bytes de una cadena
 * NetString es negativo</li>
 * <li>{@link InvalidNumberException} El numero de bytes no es un numero
 * entero.</li>
 * <li>{@link LengthDelimiterNotFoundException} no se encuentra el separador de
 * fin de bytes</li>
 * </ul>
 */
public class LengthException extends NetStringFormatException {
    public LengthException(String message) {
        super(message);
    }

    public LengthException(String message, Throwable cause) {
        super(message, cause);
    }

}
