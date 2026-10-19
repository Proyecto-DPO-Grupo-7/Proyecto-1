package clubseneca.deportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import clubseneca.usuarios.Socio;

public class Equipo implements Serializable {
    private String nombre;
    private int jugadoresMaximo;
    private boolean activo;
    private Deporte deporte;
    private Categoria categoria;
    private List<JugadorEquipo> jugadores;

    public Equipo(String nombre, int jugadoresMaximo, Deporte deporte, Categoria categoria) {
        this.nombre = nombre;
        this.jugadoresMaximo = jugadoresMaximo;
        this.deporte = deporte;
        this.categoria = categoria;
        this.activo = true;
        this.jugadores = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<JugadorEquipo> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    public boolean agregarJugador(Socio socio, String posicion, int numeroCamiseta) {
        if (!activo || socio == null || !socio.isActivo()) {
            return false;
        }
        if (!categoria.aceptaEdad(socio.calcularEdad()) || !tieneCupo() || !camisetaDisponible(numeroCamiseta)) {
            return false;
        }
        if (buscarJugador(socio) != null) {
            return false;
        }
        jugadores.add(new JugadorEquipo(socio, this, posicion, numeroCamiseta));
        return true;
    }

    public boolean removerJugador(Socio socio) {
        JugadorEquipo jugador = buscarJugador(socio);
        if (jugador != null) {
            jugadores.remove(jugador);
            return true;
        }
        return false;
    }

    public JugadorEquipo buscarJugador(Socio socio) {
        if (socio == null) {
            return null;
        }
        for (JugadorEquipo jugador : jugadores) {
            if (jugador.getSocio().getLogin().equals(socio.getLogin())) {
                return jugador;
            }
        }
        return null;
    }

    public boolean tieneCupo() {
        return jugadores.size() < jugadoresMaximo;
    }

    public boolean camisetaDisponible(int numeroCamiseta) {
        for (JugadorEquipo jugador : jugadores) {
            if (jugador.getNumeroCamiseta() == numeroCamiseta) {
                return false;
            }
        }
        return true;
    }

    public int cantidadJugadoresHabilitados() {
        int cantidad = 0;
        for (JugadorEquipo jugador : jugadores) {
            if (jugador.isHabilitado()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public boolean puedeJugarPartido() {
        return activo && cantidadJugadoresHabilitados() >= deporte.getMinJugadoresHabilitados();
    }

    public boolean isActivo() {
        return activo;
    }

    public void darDeBaja() {
        activo = false;
    }
}
