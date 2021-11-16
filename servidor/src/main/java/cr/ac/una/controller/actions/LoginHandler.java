package cr.ac.una.controller.actions;

import cr.ac.una.controller.GeneralController;
import cr.ac.una.controller.dispatcher.Context;
import cr.ac.una.controller.dispatcher.RequestHandler;
import cr.ac.una.model.Usuario;
import cr.ac.una.net.packet.RequestPacket;

public class LoginHandler implements RequestHandler {

    @Override
    public void handle(RequestPacket request, Context context) {
        System.out.println("[Auth]");
        GeneralController controlador = GeneralController.getInstance();
        String usuario = request.getParametro("usuario");
        String password = request.getParametro("password");

        Usuario credenciales = controlador.login(usuario, password);
        RequestPacket response = new RequestPacket("response");
        context.setUsuario(credenciales);
        if (credenciales != null) {
            System.out.println("usuario autenticado");
            response.setParametro("status", "ok");
        } else {
            System.out.println("usuario no autenticado");
            response.setParametro("status", "error");
            //response.setParametro("mensaje", "invalid creds");
        }
        context.sendResponse(response);
    }

}
