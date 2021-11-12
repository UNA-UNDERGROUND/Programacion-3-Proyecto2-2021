package cr.ac.una.net.packet.netstring.exception;

/**
 * esta excepcion es lanzada cuando se esperaba un caracter en una posicion no
 * esperada, por ejemplo cuando se recibe un caracter distinto de un numero en
 * el delimitador de bytes, o cuando el separador de datos es distinto al
 * esperado.
 * 
 * ejemplos:
 * <ul>
 * <li>"1;2:"</li>
 * <li>"12:hola mundo:"</li>
 * </ul>
 * 
 */
public class CharacterNotExpectedException extends NetStringFormatException {
    private Character c;

    public CharacterNotExpectedException(Character c) {
        super("character -> '" + c + "'");
        this.c = c;
    }

    public CharacterNotExpectedException(Character c, Throwable cause) {
        super("character -> '" + c + "'", cause);
        this.c = c;
    }

    public Character getCharacter() {
        return c;
    }
}
