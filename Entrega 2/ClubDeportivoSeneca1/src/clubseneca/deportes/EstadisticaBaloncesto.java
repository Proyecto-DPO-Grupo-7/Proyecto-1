package clubseneca.deportes;

public class EstadisticaBaloncesto extends EstadisticaJugador {
    private int puntos;
    private int rebotes;

    public EstadisticaBaloncesto(JugadorEquipo jugador, Partido partido) {
        super(jugador, partido);
        puntos = 0;
        rebotes = 0;
    }

    public void agregarPuntos(int puntos) {
        if (puntos > 0) {
            this.puntos += puntos;
        }
    }

    public void registrarRebote() {
        rebotes++;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getRebotes() {
        return rebotes;
    }
}
