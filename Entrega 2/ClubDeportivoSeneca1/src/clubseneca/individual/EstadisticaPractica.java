package clubseneca.individual;

import java.io.Serializable;

public abstract class EstadisticaPractica implements Serializable {
    private InscripcionDisciplina inscripcion;
    private PartidoPractica partido;

    protected EstadisticaPractica(InscripcionDisciplina inscripcion, PartidoPractica partido) {
        this.inscripcion = inscripcion;
        this.partido = partido;
    }

    public InscripcionDisciplina getInscripcion() {
        return inscripcion;
    }

    public PartidoPractica getPartido() {
        return partido;
    }

    public abstract int getSetsGanados();
}
