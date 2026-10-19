package clubseneca.deportes;

import java.io.Serializable;

public class JugadorCompetencia implements Serializable {
    private JugadorEquipo jugadorEquipo;
    private InscripcionCompetencia inscripcion;
    private int amarillasAcumuladas;
    private boolean suspendido;

    public JugadorCompetencia(JugadorEquipo jugadorEquipo, InscripcionCompetencia inscripcion) {
        this.jugadorEquipo = jugadorEquipo;
        this.inscripcion = inscripcion;
        this.amarillasAcumuladas = 0;
        this.suspendido = false;
    }

    public JugadorEquipo getJugadorEquipo() {
        return jugadorEquipo;
    }

    public InscripcionCompetencia getInscripcion() {
        return inscripcion;
    }

    public void registrarAmarilla() {
        amarillasAcumuladas++;
        if (amarillasAcumuladas >= 2) {
            suspendido = true;
        }
    }

    public void registrarRoja() {
        suspendido = true;
    }

    public boolean isHabilitado() {
        return jugadorEquipo.isHabilitado() && !suspendido;
    }

    public void cumplirSuspension() {
        suspendido = false;
        amarillasAcumuladas = 0;
    }
}
