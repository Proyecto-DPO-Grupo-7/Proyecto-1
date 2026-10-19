package clubseneca.comercial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import clubseneca.usuarios.Empleado;
import clubseneca.usuarios.Socio;
import clubseneca.usuarios.Usuario;

public class Venta implements Serializable {
    private Date fecha;
    private Usuario comprador;
    private List<DetalleVenta> detalles;
    private double porcentajeDescuento;
    private double puntosRedimidos;
    private double porcentajePropina;

    public Venta(Date fecha, Usuario comprador) {
        this.fecha = fecha;
        this.comprador = comprador;
        this.detalles = new ArrayList<>();
        this.porcentajeDescuento = 0;
        this.puntosRedimidos = 0;
        this.porcentajePropina = 0.10;
    }

    public Date getFecha() {
        return fecha;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public List<DetalleVenta> getDetalles() {
        return new ArrayList<>(detalles);
    }

    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return false;
        }

        DetalleVenta detalle = new DetalleVenta(producto, cantidad);
        detalles.add(detalle);
        return true;
    }

    public boolean aplicarDescuentoEmpleado() {
        if (comprador != null && comprador.esEmpleado()) {
            porcentajeDescuento = 0.15;
            return true;
        }

        return false;
    }

    public boolean aplicarCodigoCompartido(String codigo, Empleado empleado) {
        if (comprador == null || !comprador.esSocio()) {
            return false;
        }

        if (empleado == null || codigo == null) {
            return false;
        }

        if (empleado.getCodigoDescuento().equals(codigo) && porcentajeDescuento == 0) {
            porcentajeDescuento = 0.08;
            return true;
        }

        return false;
    }

    public boolean configurarPropina(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 1) {
            return false;
        }

        porcentajePropina = porcentaje;
        return true;
    }

    public boolean redimirPuntos(double puntos) {
        if (comprador == null || puntos <= 0) {
            return false;
        }

        if (comprador.esSocio()) {
            Socio socio = (Socio) comprador;

            if (puntos <= socio.getPuntosFidelidad()) {
                puntosRedimidos = puntos;
                return true;
            }
        }

        if (comprador.esEmpleado()) {
            Empleado empleado = (Empleado) comprador;

            if (puntos <= empleado.getPuntosFidelidad()) {
                puntosRedimidos = puntos;
                return true;
            }
        }

        return false;
    }

    public double calcularSubtotal() {
        double total = 0;

        for (DetalleVenta detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

        return total;
    }

    public double calcularSubtotalTienda() {
        double total = 0;

        for (DetalleVenta detalle : detalles) {
            if (detalle.getProducto().esArticuloTienda()) {
                total += detalle.calcularSubtotal();
            }
        }

        return total;
    }

    public double calcularSubtotalCafeteria() {
        double total = 0;

        for (DetalleVenta detalle : detalles) {
            if (detalle.getProducto().esProductoCafeteria()) {
                total += detalle.calcularSubtotal();
            }
        }

        return total;
    }

    public double calcularImpuestos() {
        double total = 0;

        for (DetalleVenta detalle : detalles) {
            total += detalle.calcularImpuesto();
        }

        return total;
    }

    public double calcularPropina() {
        double subtotalCafeteria = calcularSubtotalCafeteria();
        return subtotalCafeteria * porcentajePropina;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        return subtotal * porcentajeDescuento;
    }

    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double impuestos = calcularImpuestos();
        double propina = calcularPropina();
        double descuento = calcularDescuento();

        double total = subtotal + impuestos + propina - descuento - puntosRedimidos;

        if (total < 0) {
            return 0;
        }

        return total;
    }

    public double calcularPuntosGenerados() {
        return calcularTotal() * 0.02;
    }

    public boolean contieneBebidaCaliente() {
        for (DetalleVenta detalle : detalles) {
            if (detalle.getProducto().esBebidaCaliente()) {
                return true;
            }
        }

        return false;
    }

    public double getPuntosRedimidos() {
        return puntosRedimidos;
    }
}
