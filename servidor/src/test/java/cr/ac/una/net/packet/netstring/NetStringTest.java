package cr.ac.una.net.packet.netstring;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

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
        assertThrows(NegativeLengthException.class, () -> NetString.parseNetString(netString));
    }

    @Test
    void testSerializacion() {
        String texto = "hola mundo";
        NetString packetData = new NetString(texto);
        assertTrue(packetData.toString().equals("10:" + texto + ";"));
    }
}
