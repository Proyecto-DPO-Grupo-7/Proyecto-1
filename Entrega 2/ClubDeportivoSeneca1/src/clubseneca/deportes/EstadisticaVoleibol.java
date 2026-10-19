package clubseneca.deportes;

public class EstadisticaVoleibol extends EstadisticaJugador {
    private int aces;
    private int bloqueos;

    public EstadisticaVoleibol(JugadorEquipo jugador, Partido partido) {
        super(jugador, partido);
        aces = 0;
        bloqueos = 0;
    }

    public void registrarAce() {
        aces++;
    }

    public void registrarBloqueo() {
        bloqueos++;
    }

    public int getAces() {
        return aces;
    }

    public int getBloqueos() {
        return bloqueos;
    }
}
