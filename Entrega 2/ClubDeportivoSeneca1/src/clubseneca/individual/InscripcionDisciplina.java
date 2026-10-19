package clubseneca.individual;

import java.io.Serializable;
import clubseneca.usuarios.Usuario;

public class InscripcionDisciplina implements Serializable {
    private Usuario titular;
    private DisciplinaIndividual disciplina;
    private String nivel;
    private double rankingElo;

    public InscripcionDisciplina(Usuario titular, DisciplinaIndividual disciplina, String nivel) {
        this.titular = titular;
        this.disciplina = disciplina;
        this.nivel = nivel;
        this.rankingElo = 1000;
    }

    public Usuario getTitular() {
        return titular;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public String getNivel() {
        return nivel;
    }

    public double getRankingElo() {
        return rankingElo;
    }

    public boolean cambiarNivel(String nivel) {
        if (disciplina.nivelValido(nivel)) {
            this.nivel = nivel;
            return true;
        }
        return false;
    }

    public void ajustarRanking(double cambio) {
        rankingElo += cambio;
        if (rankingElo < 0) {
            rankingElo = 0;
        }
    }

    public boolean puedeEnfrentar(InscripcionDisciplina otra) {
        if (otra == null || otra.disciplina != disciplina) {
            return false;
        }
        if (esEntrenador() || otra.esEntrenador()) {
            return true;
        }
        return disciplina.nivelesCompatibles(nivel, otra.nivel);
    }

    public boolean esEntrenador() {
        return titular.esEntrenador();
    }
}
