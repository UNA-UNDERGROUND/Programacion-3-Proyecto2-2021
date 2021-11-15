package cr.ac.una.net.packet;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import cr.ac.una.net.packet.netstring.NetString;

public class RequestPacketTest {
    @Test
    void testParse() {
        String accion = "login";
        String paramUsuario = "usuario";
        String paramPass = "pass";
        String usuario = "juan";
        String pass = "1234";

        HashMap<String, String> parametros = new HashMap<>();

        parametros.put(paramUsuario, usuario);
        parametros.put(paramPass, pass);
        String str1 = new NetString(paramUsuario + "=" + usuario).toString();
        String str2 = new NetString(paramPass + "=" + pass).toString();
        String solicitud = accion + "\n" + str1 + str2;
        RequestPacket requestPacket = RequestPacket.parseRequest(solicitud);

        assertTrue(requestPacket.getAccion().equals(accion));
        assertTrue(requestPacket.getParametros().equals(parametros));
    }

    @Test
    void testToString() {
        String accion = "login";
        String paramUsuario = "usuario";
        String paramPass = "pass";
        String usuario = "juan";
        String pass = "1234";

        HashMap<String, String> parametros = new HashMap<>();
        parametros.put(paramUsuario, usuario);
        parametros.put(paramPass, pass);

        RequestPacket requestPacket = new RequestPacket(accion, parametros);
        String resultado = requestPacket.toString();
        String esperado = RequestPacket.parseRequest(resultado).toString();
        assertTrue(resultado.equals(esperado));
    }
}
