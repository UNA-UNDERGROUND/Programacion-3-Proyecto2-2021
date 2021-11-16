package cr.ac.una.controller.actions;

import cr.ac.una.controller.GeneralController;
import cr.ac.una.controller.dispatcher.Context;
import cr.ac.una.controller.dispatcher.RequestHandler;
import cr.ac.una.model.Usuario;
import cr.ac.una.net.packet.RequestPacket;

public class recuperarDineroHandler implements RequestHandler {

    @Override
    public void handle(RequestPacket request, Context context) {
        System.out.println("[Recuperar Dinero]");
        Usuario usuario = (Usuario) context.getUsuario();
        GeneralController controller = GeneralController.getInstance();
        RequestPacket response = new RequestPacket("response");

        Float saldoActual = controller.getSaldoActual(usuario.getUser());
        if (saldoActual != null) {
            response.setParametro("status", "ok");
            response.setParametro("monto", saldoActual.toString());
        } else {
            response.setParametro("status", "error");
            response.setParametro("error", "No se pudo recuperar el saldo actual");
        }
        context.sendResponse(response);

    }

}
