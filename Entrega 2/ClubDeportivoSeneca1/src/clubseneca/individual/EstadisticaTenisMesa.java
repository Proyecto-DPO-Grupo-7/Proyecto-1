package clubseneca.individual;

public class EstadisticaTenisMesa extends EstadisticaPractica {
    private int setsGanados;
    private int puntosTotales;

    public EstadisticaTenisMesa(InscripcionDisciplina inscripcion, PartidoPractica partido) {
        super(inscripcion, partido);
        setsGanados = 0;
        puntosTotales = 0;
    }

    public void registrarSetGanado() {
        setsGanados++;
    }

    public void agregarPuntos(int puntos) {
        if (puntos > 0) {
            puntosTotales += puntos;
        }
    }

    public int getSetsGanados() {
        return setsGanados;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }
}
