package clubseneca.comercial;

public class Bebida extends ProductoCafeteria {
    private boolean caliente;

    public Bebida(String nombre, double precio, boolean caliente) {
        super(nombre, precio);
        this.caliente = caliente;
    }

    public boolean isCaliente() {
        return caliente;
    }

    public boolean esBebidaCaliente() {
        return caliente;
    }
}
