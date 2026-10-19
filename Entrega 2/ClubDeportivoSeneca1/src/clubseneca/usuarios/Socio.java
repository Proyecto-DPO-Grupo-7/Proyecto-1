package clubseneca.usuarios;

import java.util.Calendar;
import java.util.Date;

public class Socio extends Usuario {
    private Date fechaNacimiento;
    private double puntosFidelidad;
    private boolean activo;
    private boolean tieneBebidaCaliente;

    public Socio(String login, String password, Date fechaNacimiento) {
        super(login, password);
        this.fechaNacimiento = fechaNacimiento;
        this.puntosFidelidad = 0;
        this.activo = true;
        this.tieneBebidaCaliente = false;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int calcularEdad() {
        Calendar hoy = Calendar.getInstance();
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fechaNacimiento);
        int edad = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        if (hoy.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }
        return edad;
    }

    public double getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void agregarPuntos(double puntos) {
        if (puntos > 0) {
            puntosFidelidad += puntos;
        }
    }

    public boolean redimirPuntos(double puntos) {
        if (puntos > 0 && puntos <= puntosFidelidad) {
            puntosFidelidad -= puntos;
            return true;
        }
        return false;
    }

    public boolean esSocio() {
        return true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void darDeBaja() {
        activo = false;
    }

    public boolean isTieneBebidaCaliente() {
        return tieneBebidaCaliente;
    }

    public void setTieneBebidaCaliente(boolean tieneBebidaCaliente) {
        this.tieneBebidaCaliente = tieneBebidaCaliente;
    }
}
