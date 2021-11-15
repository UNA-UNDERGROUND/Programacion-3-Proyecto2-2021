package cr.ac.una.model.DAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import cr.ac.una.model.Usuario;
import cr.ac.una.model.SQL.DAO;

/**
 * DAO de Usuario
 */
public class DAOUsuario extends DAO {
    public boolean agregarUsuario(Usuario usuario) {
        return ejecutarUpdate(sqlAgregarUsuario(), usuario.getUser(), usuario.getPassword());
    }

    public Usuario recuperarUsuario(String usuario) {
        List<Map<String, Object>> consulta = ejecutarConsulta(sqlRecuperarUsuario(), usuario);
        if (consulta != null) {
            try {
                if (consulta.size() > 0) {
                    Map<String, Object> fila = consulta.get(0);
                    Usuario u = new Usuario();
                    u.setUser((String) fila.get("usuario"));
                    u.setPassword((String) fila.get("pass"));
                    return u;
                }
            } catch (Exception e) {
                System.err.println("Error al recuperar usuario");
            }
        }
        return null;
    }

    private static String sqlAgregarUsuario() {
        return new QueryBuilder() //
                .insertInto("usuarios") // insertar en usuaarios
                .values("usuario", "?") // usuario
                .values("pass", "?") // pass
                .build(); //
    }

    private static String sqlRecuperarUsuario() {
        return new QueryBuilder() //
                .select("*") // seleccionar todos los campos
                .from("usuarios") // de la tabla usuarios
                .where("usuario = ?") // donde el usuario sea igual al parametro
                .build(); //
    }

}
