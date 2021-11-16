package cr.ac.una.model;

/**
 * Clase que representa a un usuario
 * 
 */
public class Usuario {

    /**
     * Crea un nuevo usuario
     */
    public Usuario() {
        this.user = "";
        this.password = "";
    }

    /**
     * Crea un nuevo usuario
     * 
     * @param user     Nombre de usuario, tiene que ser unico
     * @param password Contraseña, tiene que ser segura, ver tienePasswordSegura
     * 
     * @throws Exception Si el usuario no es valido
     */
    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
        if (!tienePasswordSegura()) {
            throw new RuntimeException("La contraseña no es segura");
        }
    }

    /**
     * recupera el nombre del usuario
     * 
     * @return Nombre de usuario
     */
    public String getUser() {
        return this.user;
    }

    /**
     * recupera la contraseña del usuario
     * 
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Cambia la contraseña del usuario
     * 
     * @param user Nuevo nombre de usuario
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Cambia la contraseña del usuario<br />
     * la contraseña debe cumplir con los
     * <ul>
     * <li>almenos 8 caracteres</li>
     * <li>almenos una letra mayuscula</li>
     * <li>almenos una letra minuscula</li>
     * <li>almenos un numero</li>
     * <li>almenos un caracter especial</li>
     * </ul>
     * 
     * @param password Nueva contraseña
     * @throws Exception Si la contraseña no es segura
     */
    public void setPassword(String password) {
        String actual = password;
        this.password = password;
        if (!tienePasswordSegura()) {
            this.password = actual;
            throw new RuntimeException("La contraseña no es segura");
        }
    }

    /**
     * recupera el saldo de dinero del usuario
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Cambia el saldo de dinero del usuario
     * 
     * @param saldo Nuevo saldo de dinero
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**
     * verifica si la contraseña cumple con los requisitos de una contraseña segura:
     * los requisitos son determinados por {@link #isSecurePassword(String)}
     * 
     * @return true si la contraseña cumple con los requisitos
     */
    public boolean tienePasswordSegura() {
        return isSecurePassword(password);
    }

    /**
     * verifica si la contraseña cumple con los siguientes requisitos:
     * <ul>
     * <li>almenos 8 caracteres</li>
     * <li>almenos una letra mayuscula</li>
     * <li>almenos una letra minuscula</li>
     * <li>almenos un numero</li>
     * <li>almenos un caracter especial</li>
     * </ul>
     * 
     * @param password Contraseña a verificar
     * 
     * @return true si la contraseña cumple con los requisitos
     */
    public static boolean isSecurePassword(String password) {
        return password.length() >= 8 // mayor o igual a 8 caracteres
                && password.matches(".*[A-Z].*") // al menos una letra mayúscula
                && password.matches(".*[a-z].*") // al menos una letra minúscula
                && password.matches(".*[0-9].*") // al menos un número
                && password.matches(".*[^a-zA-Z0-9].*"); // al menos un caracter especial
    }

    private String user;
    private String password;
    private float saldo = 0;
}