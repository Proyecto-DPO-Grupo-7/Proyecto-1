package clubseneca.individual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaIndividual implements Serializable {
    private String nombre;
    private int maxInstalacionesSimultaneas;
    private List<InscripcionDisciplina> inscripciones;
    private List<Instalacion> instalaciones;
    private List<PartidoPractica> partidosPractica;

    public DisciplinaIndividual(String nombre, int maxInstalacionesSimultaneas) {
        this.nombre = nombre;
        this.maxInstalacionesSimultaneas = maxInstalacionesSimultaneas;
        this.inscripciones = new ArrayList<>();
        this.instalaciones = new ArrayList<>();
        this.partidosPractica = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getMaxInstalacionesSimultaneas() {
        return maxInstalacionesSimultaneas;
    }

    public boolean nivelValido(String nivel) {
        if (nivel == null) {
            return false;
        }

        if (nivel.equalsIgnoreCase("Principiante")) {
            return true;
        }

        if (nivel.equalsIgnoreCase("Intermedio")) {
            return true;
        }

        if (nivel.equalsIgnoreCase("Avanzado")) {
            return true;
        }

        return false;
    }

    public boolean nivelesCompatibles(String nivel1, String nivel2) {
        if (!nivelValido(nivel1) || !nivelValido(nivel2)) {
            return false;
        }

        int valor1 = valorNivel(nivel1);
        int valor2 = valorNivel(nivel2);
        int diferencia = valor1 - valor2;

        if (diferencia < 0) {
            diferencia = diferencia * -1;
        }

        return diferencia <= 1;
    }

    public boolean modalidadValida(String modalidad, int cantidadJugadores) {
        if (modalidad == null) {
            return false;
        }

        if (nombre.equalsIgnoreCase("Padel") || nombre.equalsIgnoreCase("Pádel")) {
            if (cantidadJugadores == 4 && modalidad.equalsIgnoreCase("Dobles")) {
                return true;
            }
            return false;
        }

        if (nombre.equalsIgnoreCase("Tenis de mesa")) {
            if (cantidadJugadores == 2 && modalidad.equalsIgnoreCase("Sencillos")) {
                return true;
            }
            if (cantidadJugadores == 4 && modalidad.equalsIgnoreCase("Dobles")) {
                return true;
            }
            return false;
        }

        if (nombre.equalsIgnoreCase("Tenis")) {
            if (cantidadJugadores == 2 && modalidad.equalsIgnoreCase("Sencillos")) {
                return true;
            }
            if (cantidadJugadores == 4 && modalidad.equalsIgnoreCase("Dobles")) {
                return true;
            }
            return false;
        }

        return false;
    }

    public void agregarInscripcion(InscripcionDisciplina inscripcion) {
        if (inscripcion != null && !inscripciones.contains(inscripcion)) {
            inscripciones.add(inscripcion);
        }
    }

    public List<InscripcionDisciplina> getInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    public void agregarInstalacion(Instalacion instalacion) {
        if (instalacion != null && !instalaciones.contains(instalacion)) {
            instalaciones.add(instalacion);
        }
    }

    public List<Instalacion> getInstalaciones() {
        return new ArrayList<>(instalaciones);
    }

    public void agregarPartidoPractica(PartidoPractica partido) {
        if (partido != null && !partidosPractica.contains(partido)) {
            partidosPractica.add(partido);
        }
    }

    public List<PartidoPractica> getPartidosPractica() {
        return new ArrayList<>(partidosPractica);
    }

    private int valorNivel(String nivel) {
        if (nivel.equalsIgnoreCase("Principiante")) {
            return 1;
        }

        if (nivel.equalsIgnoreCase("Intermedio")) {
            return 2;
        }

        return 3;
    }
}
