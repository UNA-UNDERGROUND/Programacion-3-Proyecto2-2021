package cr.ac.una.controller;

import cr.ac.una.model.Usuario;
import cr.ac.una.model.DAO.DAOUsuario;

public class GeneralController {
    private GeneralController() {
    }

    public Usuario login(String username, String password) {
        try {
            Usuario usuario = new Usuario(username, password);
            DAOUsuario daoUsuario = new DAOUsuario();
            return daoUsuario.recuperarUsuario(usuario.getUser());
        } catch (Exception e) {
            return null;
        }
    }

    private static GeneralController instance = null;

    public static GeneralController getInstance() {
        if (instance == null) {
            instance = new GeneralController();
        }
        return instance;
    }
}
