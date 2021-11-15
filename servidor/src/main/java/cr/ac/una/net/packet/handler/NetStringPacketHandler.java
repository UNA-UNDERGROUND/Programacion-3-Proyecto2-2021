package cr.ac.una.net.packet.handler;

import cr.ac.una.net.io.PacketHandler;
import cr.ac.una.net.io.PacketProcessor;
import cr.ac.una.net.io.SocketData;
import cr.ac.una.net.packet.netstring.NetString;
import cr.ac.una.net.packet.netstring.exception.CharacterNotExpectedException;
import cr.ac.una.net.packet.netstring.exception.DataDelimiterNotFoundException;
import cr.ac.una.net.packet.netstring.exception.InvalidNumberException;
import cr.ac.una.net.packet.netstring.exception.LengthDelimiterNotFoundException;
import cr.ac.una.net.packet.netstring.exception.NegativeLengthException;
import cr.ac.una.net.packet.netstring.exception.NetStringFormatException;
import cr.ac.una.net.packet.netstring.exception.NotEnoughtBytesException;

public class NetStringPacketHandler extends ProcessorPacketHandler implements PacketHandler {

    public NetStringPacketHandler(PacketProcessor processor) {
        super(processor);
    }

    @Override
    public int processPacket(String buffer, SocketData client) {
        try {
            // primero intentamos recibir el netstring
            // si no es posible veremos si es un error faltal o no
            // con la excepcion lanzada
            NetString netString = NetString.parseNetString(buffer);
            // luego procesamos su valor
            processor.process(netString.getData(), client, this);
            // los bytes leidos es equivalente a la longitud del netstring
            return netString.toString().length();
        }
        // faltan mas bytes por leer no es un error fatal
        catch (NotEnoughtBytesException | LengthDelimiterNotFoundException ex) {
            return 0;
        }
        // un caracter no esperado es un error fatal
        catch (CharacterNotExpectedException | DataDelimiterNotFoundException | NegativeLengthException
                | InvalidNumberException ex) {
            System.err.println("Error de veficacion: " + ex.getMessage());
            return -1;
        }
        // error desconocido de netstring
        catch (NetStringFormatException e) {
            System.err.println("Error de protocolo: " + e.getMessage());
            // y retornamos -1 para indicar que es un error desconocido
            return -1;
        }
        // excepcion desconocida // error faltal
        catch (Exception e) {
            System.err.println("Error desconocido: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void transferPacket(String packet, SocketData client) {
        client.sendData(new NetString(packet).toString());
    }

}
