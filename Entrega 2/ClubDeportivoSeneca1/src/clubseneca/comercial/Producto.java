package clubseneca.comercial;

import java.io.Serializable;

public abstract class Producto implements Serializable {
    private String nombre;
    private double precio;

    protected Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public abstract double getTasaImpuesto();

    public boolean esArticuloTienda() {
        return false;
    }

    public boolean esProductoCafeteria() {
        return false;
    }

    public boolean esBebidaCaliente() {
        return false;
    }
}
