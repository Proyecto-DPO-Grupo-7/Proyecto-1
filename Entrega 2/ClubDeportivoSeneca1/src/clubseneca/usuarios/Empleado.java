package clubseneca.usuarios;

public abstract class Empleado extends Usuario {
    private String codigoDescuento;
    private double puntosFidelidad;

    protected Empleado(String login, String password, String codigoDescuento) {
        super(login, password);
        this.codigoDescuento = codigoDescuento;
        this.puntosFidelidad = 0;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
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

    public boolean esEmpleado() {
        return true;
    }
}
