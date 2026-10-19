package clubseneca.deportes;

public class EstadisticaFutbol extends EstadisticaJugador {
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

    public EstadisticaFutbol(JugadorEquipo jugador, Partido partido) {
        super(jugador, partido);
        goles = 0;
        asistencias = 0;
        tarjetasAmarillas = 0;
        tarjetasRojas = 0;
    }

    public void registrarGol() {
        goles++;
    }

    public void registrarAsistencia() {
        asistencias++;
    }

    public void registrarAmarilla() {
        tarjetasAmarillas++;
        getJugador().registrarAmarilla();
    }

    public void registrarRoja() {
        tarjetasRojas++;
        getJugador().registrarRoja();
    }

    public int getGoles() {
        return goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }
}
