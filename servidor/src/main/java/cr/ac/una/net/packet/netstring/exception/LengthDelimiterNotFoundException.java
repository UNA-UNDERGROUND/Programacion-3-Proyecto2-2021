package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando no se encuentra el separador de fin de bytes.
 * 
 * por lo general este error se produce cuando no se han recuperado todos los
 * bytes del NetString, por ejemplo: en una conexion de red.
 * 
 * 
 * algunos ejemplos de netstrings que no se pueden serializar:
 * <ul>
 * <li>"1;a;" -> no se esperaba ";"</li>
 * <li>"1;a;b;"</li>
 * <li>"1"</li>
 * <li>""</li>
 * </ul>
 * 
 */
public class LengthDelimiterNotFoundException extends LengthException {
    public LengthDelimiterNotFoundException(String message) {
        super(message);
    }

    public LengthDelimiterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}