package clubseneca.usuarios;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private String login;
    private String password;

    protected Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }

    public void cambiarPassword(String nuevaPassword) {
        this.password = nuevaPassword;
    }

    public boolean esSocio() {
        return false;
    }

    public boolean esEmpleado() {
        return false;
    }

    public boolean esEntrenador() {
        return false;
    }

    public boolean esFisioterapeuta() {
        return false;
    }
}
