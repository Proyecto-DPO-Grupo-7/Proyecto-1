package clubseneca.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import clubseneca.usuarios.Socio;
import clubseneca.usuarios.Usuario;

public class GestionUsuarios implements Serializable {
    private Map<String, Usuario> usuarios;

    public GestionUsuarios() {
        usuarios = new HashMap<>();
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }

        if (existeLogin(usuario.getLogin())) {
            return false;
        }

        usuarios.put(usuario.getLogin(), usuario);
        return true;
    }

    public Usuario buscarUsuario(String login) {
        return usuarios.get(login);
    }

    public Usuario autenticar(String login, String password) {
        Usuario usuario = buscarUsuario(login);

        if (usuario == null) {
            return null;
        }

        if (usuario.validarPassword(password)) {
            return usuario;
        }

        return null;
    }

    public boolean existeLogin(String login) {
        return usuarios.containsKey(login);
    }

    public boolean darDeBajaSocio(String login) {
        Usuario usuario = buscarUsuario(login);

        if (usuario == null || !usuario.esSocio()) {
            return false;
        }

        Socio socio = (Socio) usuario;
        socio.darDeBaja();
        return true;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}
