package cr.ac.una.controller.actions;

import cr.ac.una.controller.GeneralController;
import cr.ac.una.controller.dispatcher.Context;
import cr.ac.una.controller.dispatcher.RequestHandler;
import cr.ac.una.model.Usuario;
import cr.ac.una.net.packet.RequestPacket;

public class PasswordChangeHandler implements RequestHandler {

    @Override
    public void handle(RequestPacket request, Context context) {
        System.out.println("[Password Change]");
        GeneralController controller = GeneralController.getInstance();
        String originalPassword = request.getParametro("originalPassword");
        String newPassword = request.getParametro("newPassword");
        Usuario usuario = context.getUsuario();
        RequestPacket response = new RequestPacket("response");

        if (usuario == null) {
            response.setParametro("status", "requires-auth");
            response.setParametro("error", "No se ha iniciado sesión");
        } else {
            if (controller.cambiarPass(usuario, originalPassword, newPassword)) {
                response.setParametro("status", "ok");
                response.setParametro("message", "Contraseña cambiada");
            } else {
                response.setParametro("status", "error");
                if (Usuario.isSecurePassword(newPassword)) {
                    response.setParametro("error", "Contraseña no segura");
                } else {
                    response.setParametro("error", "Contraseña no cambiada");
                }
            }
        }
        context.sendResponse(response);
    }

}
