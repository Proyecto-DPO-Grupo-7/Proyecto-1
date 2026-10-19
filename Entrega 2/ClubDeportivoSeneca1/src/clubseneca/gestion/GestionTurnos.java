package clubseneca.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import clubseneca.turnos.SolicitudCambioTurno;
import clubseneca.turnos.TurnoSemanal;
import clubseneca.usuarios.Administrador;
import clubseneca.usuarios.Empleado;

public class GestionTurnos implements Serializable {
    private List<TurnoSemanal> turnos;
    private List<SolicitudCambioTurno> solicitudes;

    public GestionTurnos() {
        turnos = new ArrayList<>();
        solicitudes = new ArrayList<>();
    }

    public void asignarTurno(TurnoSemanal turno) {
        if (turno != null) {
            turnos.add(turno);
        }
    }

    public void registrarSolicitud(SolicitudCambioTurno solicitud) {
        if (solicitud != null) {
            solicitudes.add(solicitud);
        }
    }

    public boolean aprobarSolicitud(SolicitudCambioTurno solicitud, Administrador administrador) {
        if (solicitud == null || administrador == null) {
            return false;
        }

        if (!solicitud.getEstado().equals("PENDIENTE")) {
            return false;
        }

        if (!cumpleCoberturaMinima(solicitud.getTurnoActual(), solicitud.getTurnoPropuesto())) {
            solicitud.rechazar(administrador);
            return false;
        }

        turnos.remove(solicitud.getTurnoActual());
        turnos.add(solicitud.getTurnoPropuesto());
        solicitud.aprobar(administrador);
        return true;
    }

    public void rechazarSolicitud(SolicitudCambioTurno solicitud, Administrador administrador) {
        if (solicitud == null || administrador == null) {
            return;
        }

        if (solicitud.getEstado().equals("PENDIENTE")) {
            solicitud.rechazar(administrador);
        }
    }

    public List<TurnoSemanal> getTurnosEmpleado(Empleado empleado) {
        List<TurnoSemanal> resultado = new ArrayList<>();

        for (TurnoSemanal turno : turnos) {
            if (turno.getEmpleado().getLogin().equals(empleado.getLogin())) {
                resultado.add(turno);
            }
        }

        return resultado;
    }

    public List<SolicitudCambioTurno> getSolicitudes() {
        return new ArrayList<>(solicitudes);
    }

    public List<TurnoSemanal> getTurnos() {
        return new ArrayList<>(turnos);
    }

    private boolean cumpleCoberturaMinima(TurnoSemanal turnoActual, TurnoSemanal turnoPropuesto) {
        int entrenadoresActual = 0;
        int fisioterapeutasActual = 0;

        for (TurnoSemanal turno : turnos) {
            if (turno != turnoActual && turno.coincideCon(turnoActual)) {
                if (turno.getEmpleado().esEntrenador()) {
                    entrenadoresActual++;
                }

                if (turno.getEmpleado().esFisioterapeuta()) {
                    fisioterapeutasActual++;
                }
            }
        }

        int entrenadoresPropuesto = 0;
        int fisioterapeutasPropuesto = 0;

        for (TurnoSemanal turno : turnos) {
            if (turno != turnoActual && turno.coincideCon(turnoPropuesto)) {
                if (turno.getEmpleado().esEntrenador()) {
                    entrenadoresPropuesto++;
                }

                if (turno.getEmpleado().esFisioterapeuta()) {
                    fisioterapeutasPropuesto++;
                }
            }
        }

        if (turnoPropuesto.getEmpleado().esEntrenador()) {
            entrenadoresPropuesto++;
        }

        if (turnoPropuesto.getEmpleado().esFisioterapeuta()) {
            fisioterapeutasPropuesto++;
        }

        boolean actualCubierto = entrenadoresActual >= 2 && fisioterapeutasActual >= 1;
        boolean propuestoCubierto = entrenadoresPropuesto >= 2 && fisioterapeutasPropuesto >= 1;

        return actualCubierto && propuestoCubierto;
    }
}
