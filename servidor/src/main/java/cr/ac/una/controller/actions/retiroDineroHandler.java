package cr.ac.una.controller.actions;

import cr.ac.una.controller.GeneralController;
import cr.ac.una.controller.dispatcher.Context;
import cr.ac.una.controller.dispatcher.RequestHandler;
import cr.ac.una.model.Usuario;
import cr.ac.una.net.packet.RequestPacket;

public class retiroDineroHandler implements RequestHandler {

    @Override
    public void handle(RequestPacket request, Context context) {
        System.out.println("[Retiro Dinero]");
        Usuario usuario = (Usuario) context.getUsuario();
        String monto = request.getParametro("monto");
        GeneralController controller = GeneralController.getInstance();
        RequestPacket response = new RequestPacket("response");
        if (controller.retiroDinero(usuario.getUser(), monto)) {
            response.setParametro("status", "ok");
            response.setParametro("message", "Retiro exitoso");
        } else {
            response.setParametro("status", "error");
            response.setParametro("message", "No se pudo realizar el retiro");
        }
        context.sendResponse(response);
    }
}
