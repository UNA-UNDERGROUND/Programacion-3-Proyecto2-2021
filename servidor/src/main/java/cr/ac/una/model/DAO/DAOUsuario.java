package cr.ac.una.model.DAO;

import java.sql.ResultSet;

import cr.ac.una.model.Usuario;
import cr.ac.una.model.SQL.DAO;

/**
 * DAO de Usuario
 */
public class DAOUsuario extends DAO {
    public boolean agregarUsuario(Usuario usuario){
        return ejecutarUpdate(sqlAgregarUsuario(usuario.getUser(), usuario.getPassword()));
    }
    public Usuario recuperarUsuario(String usuario) {
        ResultSet consulta = ejecutarConsulta(sqlRecuperarUsuario(usuario));
        if (consulta != null) {
            try {
                if (consulta.next()) {
                    Usuario u = new Usuario();
                    u.setUser(consulta.getString("usuario"));
                    u.setPassword(consulta.getString("pass"));
                    return u;
                }
            } catch (Exception e) {
                System.err.println("Error al recuperar usuario");
            }
        }
        return null;
    }

    private static String sqlAgregarUsuario(String usuario, String pass) {
        return new QueryBuilder()
                .insertInto("usuarios")
                .values("usuario", usuario)
                .values("pass", pass)
                .build();
    }

    private static String sqlRecuperarUsuario(String usuario) {
        return new QueryBuilder()
                .select("*")
                .from("usuarios")
                .where("usuario = " + usuario)
                .build();
    }


}
