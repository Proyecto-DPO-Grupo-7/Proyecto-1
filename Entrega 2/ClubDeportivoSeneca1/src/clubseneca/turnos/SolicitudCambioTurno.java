package clubseneca.turnos;

import java.io.Serializable;
import java.util.Date;
import clubseneca.usuarios.Administrador;
import clubseneca.usuarios.Empleado;

public class SolicitudCambioTurno implements Serializable {
    private Date fechaSolicitud;
    private String estado;
    private Empleado empleado;
    private TurnoSemanal turnoActual;
    private TurnoSemanal turnoPropuesto;
    private Administrador administradorRevisor;

    public SolicitudCambioTurno(Date fechaSolicitud, Empleado empleado, TurnoSemanal turnoActual, TurnoSemanal turnoPropuesto) {
        this.fechaSolicitud = fechaSolicitud;
        this.empleado = empleado;
        this.turnoActual = turnoActual;
        this.turnoPropuesto = turnoPropuesto;
        this.estado = "PENDIENTE";
        this.administradorRevisor = null;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public TurnoSemanal getTurnoActual() {
        return turnoActual;
    }

    public TurnoSemanal getTurnoPropuesto() {
        return turnoPropuesto;
    }

    public Administrador getAdministradorRevisor() {
        return administradorRevisor;
    }

    public void aprobar(Administrador administrador) {
        estado = "APROBADA";
        administradorRevisor = administrador;
    }

    public void rechazar(Administrador administrador) {
        estado = "RECHAZADA";
        administradorRevisor = administrador;
    }
}
