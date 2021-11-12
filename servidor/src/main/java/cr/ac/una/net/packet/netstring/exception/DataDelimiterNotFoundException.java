package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando no se encuentra el separador de fin de datos.
 * 
 * @Note: Este error se produce cuando ya se ha leido la longitud del NetString
 *        y todavía hay bytes que no se han leido, si no hay bytes disponibles
 *        se lanzara {@link NotEnoughtBytesException}.
 * 
 */
public class DataDelimiterNotFoundException extends DataException {
    public DataDelimiterNotFoundException(String message) {
        super(message);
    }

    public DataDelimiterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}