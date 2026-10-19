package clubseneca.turnos;

import java.io.Serializable;
import clubseneca.usuarios.Empleado;

public class TurnoSemanal implements Serializable {
    private String dia;
    private String horaInicio;
    private String horaFin;
    private Empleado empleado;

    public TurnoSemanal(String dia, String horaInicio, String horaFin, Empleado empleado) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleado = empleado;
    }

    public String getDia() {
        return dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public boolean coincideCon(TurnoSemanal turno) {
        return dia.equals(turno.dia) && horaInicio.equals(turno.horaInicio) && horaFin.equals(turno.horaFin);
    }
}
