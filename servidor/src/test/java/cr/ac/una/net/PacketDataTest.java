package cr.ac.una.net;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PacketDataTest {
    @Test
    void testParseNetString() {
        String texto = "Juan";
        // Juan ->(NetString) "4:Juan;"
        String netString = "4:" + texto + ";";
        PacketData packetData = PacketData.parseNetString(netString);
        assertTrue(packetData.getData().equals(texto));
    }

    @Test
    void testSerializacion() {
        String texto = "hola mundo";
        PacketData packetData = new PacketData(texto);
        assertTrue(packetData.toString().equals("10:" + texto + ";"));
    }
}
