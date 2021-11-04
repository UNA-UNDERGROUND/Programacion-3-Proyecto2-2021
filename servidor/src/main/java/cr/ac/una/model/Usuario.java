package cr.ac.una.model;

/**
 * Clase que representa a un usuario
 * 
 */
public class Usuario {

    public Usuario() {
        this.user = "";
        this.password = "";
    }

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String user;
    private String password;
}