package cr.ac.una.net.packet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cr.ac.una.net.packet.netstring.NetString;

/**
 * Paquete que contiene la solicitud de un servidor.
 * <ul>
 * <li>las acciones son las solicitudes de un cliente al servidor.</li>
 * <li>las acciones van desde una autenticacion hasta modificacion de la
 * cuenta</li>
 * <li>se componen de una accion y una lista de parametros.</li>
 * </ul>
 * </p>
 * 
 * las acciones estan codificadas mediante una accion y una nueva linea seguido
 * de varios netstring que contienen los parametros. los parametros son
 * clave=valor
 * 
 * ejemplo:
 * 
 * <ul>
 * <li>
 * 
 * <pre>
 * "login\n12:usuario=juan;13:password:1234;"
 * </pre>
 * 
 * </li>
 * <li>
 * 
 * <pre>
 * accion = login, parametros = ["usuario=juan", "password=1234"]
 * </pre>
 * 
 * </li>
 * <li>
 * 
 * <pre>
 * accion = login, parametros = {"usuario": "juan", "password": "1234"}
 * </pre>
 * 
 * </li>
 * </ul>
 * 
 * 
 */
public class RequestPacket {

    /**
     * Crea una nueva solicitud
     * 
     * @param accion     la accion que se desea realizar
     * @param parametros los parametros que se desean enviar
     */
    public RequestPacket(String accion, String parametros) {
        this(accion, splitParams(parametros));
    }

    /**
     * Crea una nueva solicitud
     * 
     * @param accion     la accion que se desea realizar
     * @param parametros los parametros que se desean enviar
     */
    public RequestPacket(String accion, List<String> parametros) {
        this(accion, parseParams(parametros));
    }

    /**
     * Crea una nueva solicitud
     * 
     * @param accion     la accion que se desea realizar
     * @param parametros los parametros que se desean enviar
     */
    public RequestPacket(String accion, HashMap<String, String> parametros) {
        this.accion = accion;
        // verifica que la accion no tenga una nueva linea o caracter nulo \0
        if (accion.contains("\n") || accion.contains("\0")) {
            throw new IllegalArgumentException("Accion no valida");
        }
        this.parametros = parametros;
    }

    /**
     * recupera la accion del paquete, la accion no puede contener ni una nueva
     * linea ni un caracter nulo
     * 
     * @return la accion del paquete
     */
    public String getAccion() {
        return accion;
    }

    /**
     * recupera la lista de parametros del paquete como un diccionario
     * 
     * @return los parametros del paquete
     */
    public HashMap<String, String> getParametros() {
        return parametros;
    }

    /**
     * separa las cadenas de texto que contienen los parametros. las cadenas de
     * texto estan codificadas como un netstring
     * 
     * @return la lista de strings
     */
    private static List<String> splitParams(String string) {
        try {
            InputStream stream = new ByteArrayInputStream(string.getBytes());
            Reader reader = new InputStreamReader(stream);
            ArrayList<String> lista = new ArrayList<String>();
            try {
                NetString ns;
                while ((ns = NetString.parseNetString(reader)) != null) {
                    lista.add(ns.getData());
                }
            } catch (Exception e) {
                // verificamos que el stream no este vacio
                if (reader.read() != -1) {
                    throw new IllegalArgumentException("Parametros no validos");
                }
            }
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Error al recuperar parametros:" + e.getMessage());
        }
    }

    /**
     * serializa los parametros de una cadena de texto
     * 
     * @return el diccionario con los parametros
     */
    private static HashMap<String, String> parseParams(List<String> params) {
        HashMap<String, String> res = new HashMap<String, String>();
        for (String param : params) {
            String[] split = param.split(SEPARATOR_PARAMETER);
            if (split.length != 2) {
                throw new RuntimeException("Error al parsear parametros");
            }
            res.put(split[0], split[1]);
        }
        return res;
    }

    /**
     * deserializa una solicitud
     * 
     * @param request la cadena de texto que contiene la solicitud
     * @return la solicitud
     */
    public static RequestPacket parseRequest(String request) {
        String[] split = request.split("\n");
        if (split.length != 2) {
            throw new RuntimeException("Error al parsear request");
        }
        String accion = split[0];
        String parametros = split[1];
        return new RequestPacket(accion, parametros);
    }

    /**
     * serializa una solicitud usando el formato "accion\n{parametros}" donde los
     * parametros estan codificados como netstrings ejemplo:
     * "login\n12:usuario=juan;13:password=1234;"
     * 
     * @return la cadena de texto que contiene la solicitud
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(accion);
        builder.append("\n");
        for (String key : parametros.keySet()) {
            String param = new NetString(key + SEPARATOR_PARAMETER + parametros.get(key)).toString();
            builder.append(param);
        }
        return builder.toString();
    }

    private String accion;
    private HashMap<String, String> parametros;

    public static String SEPARATOR_PARAMETER = "=";
}
