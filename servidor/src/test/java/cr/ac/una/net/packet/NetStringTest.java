package cr.ac.una.net.packet;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NetStringTest {
    @Test
    void testParseNetString() {
        String texto = "Juan";
        // Juan ->(NetString) "4:Juan;"
        String netString = "4:" + texto + ";";
        NetString packetData = NetString.parseNetString(netString);
        assertTrue(packetData.getData().equals(texto));
    }

    @Test
    void testSerializacion() {
        String texto = "hola mundo";
        NetString packetData = new NetString(texto);
        assertTrue(packetData.toString().equals("10:" + texto + ";"));
    }
}
