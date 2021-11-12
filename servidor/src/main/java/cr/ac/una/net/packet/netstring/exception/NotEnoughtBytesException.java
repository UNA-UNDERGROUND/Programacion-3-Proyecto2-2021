package cr.ac.una.net.packet.netstring.exception;

/**
 * Excepcion causada cuando no hay suficientes bytes para leer el NetString
 * 
 * por lo general es causado cuando no se han recuperado todos los bytes del
 * NetString, por ejemplo: en una conexion de red.
 * 
 * ejemplos:
 * <ul>
 * <li>""</li>
 * <li>"1:a"</li>
 */
public class NotEnoughtBytesException extends DataException {
    public NotEnoughtBytesException(String message) {
        super(message);
    }
}