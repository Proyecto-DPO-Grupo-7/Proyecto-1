package clubseneca.individual;

import java.io.Serializable;

public class Instalacion implements Serializable {
    private String nombre;
    private int capacidadMaxima;
    private boolean techada;
    private DisciplinaIndividual disciplina;

    public Instalacion(String nombre, int capacidadMaxima, boolean techada, DisciplinaIndividual disciplina) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.techada = techada;
        this.disciplina = disciplina;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }

    public boolean isTechada() {
        return techada;
    }

    public boolean permiteCantidad(int cantidadJugadores) {
        if (cantidadJugadores <= 0) {
            return false;
        }

        if (cantidadJugadores > capacidadMaxima) {
            return false;
        }

        return true;
    }

    public boolean restringeBebidaCaliente() {
        String nombreDisciplina = disciplina.getNombre();

        if (nombreDisciplina.equalsIgnoreCase("Padel") || nombreDisciplina.equalsIgnoreCase("Pádel")) {
            return true;
        }

        if (nombreDisciplina.equalsIgnoreCase("Tenis de mesa") && techada) {
            return true;
        }

        return false;
    }
}
