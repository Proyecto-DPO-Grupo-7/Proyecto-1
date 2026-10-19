package clubseneca.comercial;

import java.io.Serializable;

public class StockArticulo implements Serializable {
    private ArticuloTienda articulo;
    private UbicacionInventario ubicacion;
    private int cantidad;

    public StockArticulo(ArticuloTienda articulo, UbicacionInventario ubicacion, int cantidad) {
        this.articulo = articulo;
        this.ubicacion = ubicacion;

        if (cantidad < 0) {
            this.cantidad = 0;
        } else {
            this.cantidad = cantidad;
        }
    }

    public ArticuloTienda getArticulo() {
        return articulo;
    }

    public UbicacionInventario getUbicacion() {
        return ubicacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean hayDisponibilidad(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }

        if (this.cantidad < cantidad) {
            return false;
        }

        return true;
    }

    public void aumentarCantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    public boolean disminuirCantidad(int cantidad) {
        if (hayDisponibilidad(cantidad)) {
            this.cantidad -= cantidad;
            return true;
        }
        return false;
    }
}
