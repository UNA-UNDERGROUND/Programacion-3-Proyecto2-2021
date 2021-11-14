package cr.ac.una.net.packet.netstring;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import cr.ac.una.net.packet.netstring.exception.CharacterNotExpectedException;
import cr.ac.una.net.packet.netstring.exception.LengthDelimiterNotFoundException;
import cr.ac.una.net.packet.netstring.exception.NegativeLengthException;
import cr.ac.una.net.packet.netstring.exception.NetStringFormatException;

public class NetStringTest {
    @Test
    void testParseNetString() throws IOException, NetStringFormatException {
        String texto = "Juan";
        // Juan ->(NetString) "4:Juan;"
        String netString = "4:" + texto + ";";
        NetString packetData = NetString.parseNetString(netString);
        assertTrue(packetData.getData().equals(texto));
    }

    @Test
    void negativeLengthExceptionTest() {
        String netString = "-1:hola mundo";
        // en algunas implementaciones de NetString se puede que el valor de la longitud
        // sea negativo
        // assertThrows(NegativeLengthException.class, () ->
        // NetString.parseNetString(netString));
        // en nuestra implementacion de NetString no se puede que el valor de la
        // longitud sea negativo
        // ya que se genera una excepcion de formato character not expected exception
        CharacterNotExpectedException ex1 = assertThrows(CharacterNotExpectedException.class,
                () -> NetString.parseNetString(netString));
        // y la causa de la excepcion es longitud negativa
        assertTrue(ex1.getCause() instanceof NegativeLengthException);
    }

    @Test
    void InvalidNumberExceptionTest() {
        String netString = "uno:hola mundo";
        String netString2 = "1,0:hola mundo";
        assertThrows(NetStringFormatException.class, () -> NetString.parseNetString(netString));
        assertThrows(NetStringFormatException.class, () -> NetString.parseNetString(netString2));
    }

    @Test
    void LengthDelimiterNotFoundExceptionTest() {
        String netString = "1;a;";
        assertThrows(NetStringFormatException.class, () -> NetString.parseNetString(netString));
    }

    @Test
    void NotEnoughtBytesExceptionTest() {
        String str1 = "";
        String str2 = "1:a";
        String str3 = "2";
        assertThrows(NetStringFormatException.class, () -> NetString.parseNetString(str1));
        assertThrows(NetStringFormatException.class, () -> NetString.parseNetString(str2));
        assertThrows(NetStringFormatException.class, () -> NetString.parseNetString(str3));
    }

    @Test
    void testSerializacion() {
        String texto = "hola mundo";
        NetString packetData = new NetString(texto);
        assertTrue(packetData.toString().equals("10:" + texto + ";"));
    }
}
