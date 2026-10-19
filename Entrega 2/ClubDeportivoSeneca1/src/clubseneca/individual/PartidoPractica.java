package clubseneca.individual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartidoPractica implements Serializable {
    private Date fecha;
    private DisciplinaIndividual disciplina;
    private List<InscripcionDisciplina> participantes;
    private List<EstadisticaPractica> estadisticas;

    public PartidoPractica(Date fecha, DisciplinaIndividual disciplina) {
        this.fecha = fecha;
        this.disciplina = disciplina;
        this.participantes = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
    }

    public Date getFecha() {
        return fecha;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public List<InscripcionDisciplina> getParticipantes() {
        return new ArrayList<>(participantes);
    }

    public boolean agregarParticipante(InscripcionDisciplina inscripcion) {
        if (inscripcion == null || inscripcion.getDisciplina() != disciplina || participantes.size() >= 4 || participantes.contains(inscripcion)) {
            return false;
        }
        participantes.add(inscripcion);
        return true;
    }

    public boolean nivelesValidos() {
        for (int i = 0; i < participantes.size(); i++) {
            for (int j = i + 1; j < participantes.size(); j++) {
                if (!participantes.get(i).puedeEnfrentar(participantes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean puedeProgramarse() {
        if (participantes.size() != 2 && participantes.size() != 4) {
            return false;
        }

        String modalidad;
        if (participantes.size() == 2) {
            modalidad = "Sencillos";
        } else {
            modalidad = "Dobles";
        }

        if (!nivelesValidos()) {
            return false;
        }

        return disciplina.modalidadValida(modalidad, participantes.size());
    }

    public void agregarEstadistica(EstadisticaPractica estadistica) {
        if (estadistica != null && estadistica.getPartido() == this) {
            estadisticas.add(estadistica);
        }
    }

    public List<EstadisticaPractica> getEstadisticas() {
        return new ArrayList<>(estadisticas);
    }

    public void actualizarRankings() {
        if (estadisticas.size() < 2) {
            return;
        }

        int primerValor = estadisticas.get(0).getSetsGanados();
        int maxSets = primerValor;
        boolean todosIguales = true;

        for (EstadisticaPractica estadistica : estadisticas) {
            int sets = estadistica.getSetsGanados();

            if (sets != primerValor) {
                todosIguales = false;
            }

            if (sets > maxSets) {
                maxSets = sets;
            }
        }

        if (todosIguales) {
            return;
        }

        for (EstadisticaPractica estadistica : estadisticas) {
            if (estadistica.getSetsGanados() == maxSets) {
                estadistica.getInscripcion().ajustarRanking(10);
            } else {
                estadistica.getInscripcion().ajustarRanking(-10);
            }
        }
    }
}
