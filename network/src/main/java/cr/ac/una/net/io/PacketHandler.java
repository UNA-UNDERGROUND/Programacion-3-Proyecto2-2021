package cr.ac.una.net.io;

/**
 * Se encarga de manejar los paquetes entrantes y salientes.
 */
public interface PacketHandler {

    /**
     * intenta procesar un paquete entrante. si el paquete es valido, lo procesa y
     * retorna la cantidad de bytes del paquete para que este pueda ser removido del
     * buffer, si no se puede generar un paquete porque falta informacion este
     * retornará 0. si el paquete es invalido, retorna -1, por lo tanto la conexion
     * debe ser cerrrada.
     * 
     * 
     * @param buffer el buffer con los datos recibidos.
     * @return la cantidad de bytes del paquete procesado.
     */
    public int processPacket(String buffer, SocketData client);

    public void transferPacket(String packet, SocketData client);

}
