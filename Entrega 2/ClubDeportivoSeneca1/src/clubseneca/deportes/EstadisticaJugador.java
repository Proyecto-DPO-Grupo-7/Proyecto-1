package clubseneca.deportes;

import java.io.Serializable;

public abstract class EstadisticaJugador implements Serializable {
    private JugadorEquipo jugador;
    private Partido partido;

    protected EstadisticaJugador(JugadorEquipo jugador, Partido partido) {
        this.jugador = jugador;
        this.partido = partido;
    }

    public JugadorEquipo getJugador() {
        return jugador;
    }

    public Partido getPartido() {
        return partido;
    }
}
