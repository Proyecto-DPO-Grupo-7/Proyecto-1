package clubseneca.comercial;

import java.io.Serializable;

public class DetalleVenta implements Serializable {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double calcularSubtotal() {
        return precioUnitario * cantidad;
    }

    public double calcularImpuesto() {
        return calcularSubtotal() * producto.getTasaImpuesto();
    }
}
