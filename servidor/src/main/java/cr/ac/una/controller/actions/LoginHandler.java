package cr.ac.una.controller.actions;

import cr.ac.una.controller.GeneralController;
import cr.ac.una.controller.dispatcher.Context;
import cr.ac.una.controller.dispatcher.RequestHandler;
import cr.ac.una.model.Usuario;
import cr.ac.una.net.packet.RequestPacket;

public class LoginHandler implements RequestHandler {

    @Override
    public void handle(RequestPacket request, Context context) {
        GeneralController controlador = GeneralController.getInstance();
        String usuario = request.getParametro("usuario");
        String password = request.getParametro("password");

        Usuario credenciales = controlador.login(usuario, password);
        RequestPacket response = new RequestPacket("response");
        context.setUsuario(credenciales);
        if (credenciales != null) {
            response.setParametro("estado", "ok");
        } else {
            response.setParametro("estado", "error");
            response.setParametro("mensaje", "Usuario o contraseña incorrectos");
        }
        context.sendResponse(response);
    }

}
