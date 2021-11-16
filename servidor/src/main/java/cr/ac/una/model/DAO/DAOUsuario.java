package cr.ac.una.model.DAO;

import java.math.BigDecimal;
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

    public boolean actualizarUsuario(Usuario usuarioDB) {
        return ejecutarUpdate(sqlActualizarUsuario(), usuarioDB.getPassword(), usuarioDB.getUser());
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
                    u.setSaldo(((BigDecimal) fila.get("saldo")).floatValue());
                    return u;
                }
            } catch (Exception e) {
                System.err.println("Error al recuperar usuario");
            }
        }
        return null;
    }

    public boolean actualizarSaldo(Usuario usuarioDB) {
        return ejecutarUpdate(sqlActualizarSaldo(), usuarioDB.getSaldo(), usuarioDB.getUser());
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

    private static String sqlActualizarUsuario() {
        return new QueryBuilder() //
                .update("usuarios") // actualizar la tabla usuarios
                .set("pass", "?") // setear el campo pass
                .where("usuario = ?") // donde el usuario sea igual al parametro
                .build(); //
    }

    private static String sqlActualizarSaldo() {
        return new QueryBuilder() //
                .update("usuarios") // actualizar la tabla usuarios
                .set("saldo", "?") // setear el campo saldo
                .where("usuario = ?") // donde el usuario sea igual al parametro
                .build(); //
    }
}
