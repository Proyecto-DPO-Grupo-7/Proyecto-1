package clubseneca.individual;

import java.io.Serializable;
import java.util.Date;
import clubseneca.usuarios.Socio;

public class Reserva implements Serializable {
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private int cantidadJugadores;
    private String modalidad;
    private Socio socio;
    private Instalacion instalacion;

    public Reserva(Date fecha, String horaInicio, String horaFin, int cantidadJugadores, String modalidad, Socio socio, Instalacion instalacion) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cantidadJugadores = cantidadJugadores;
        this.modalidad = modalidad;
        this.socio = socio;
        this.instalacion = instalacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public String getModalidad() {
        return modalidad;
    }

    public Socio getSocio() {
        return socio;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public boolean seCruzaCon(Reserva otra) {
        if (otra == null || !fecha.equals(otra.fecha) || instalacion != otra.instalacion) {
            return false;
        }
        return horaInicio.compareTo(otra.horaFin) < 0 && horaFin.compareTo(otra.horaInicio) > 0;
    }
}
