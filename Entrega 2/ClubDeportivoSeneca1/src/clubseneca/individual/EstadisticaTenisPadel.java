package clubseneca.individual;

public class EstadisticaTenisPadel extends EstadisticaPractica {
    private int setsGanados;
    private int gamesGanados;
    private int aces;
    private int erroresNoForzados;

    public EstadisticaTenisPadel(InscripcionDisciplina inscripcion, PartidoPractica partido) {
        super(inscripcion, partido);
        setsGanados = 0;
        gamesGanados = 0;
        aces = 0;
        erroresNoForzados = 0;
    }

    public void registrarSetGanado() {
        setsGanados++;
    }

    public void registrarGameGanado() {
        gamesGanados++;
    }

    public void registrarAce() {
        aces++;
    }

    public void registrarErrorNoForzado() {
        erroresNoForzados++;
    }

    public int getSetsGanados() {
        return setsGanados;
    }

    public int getGamesGanados() {
        return gamesGanados;
    }

    public int getAces() {
        return aces;
    }

    public int getErroresNoForzados() {
        return erroresNoForzados;
    }
}
