package clubseneca.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import clubseneca.comercial.ArticuloTienda;
import clubseneca.comercial.DetalleVenta;
import clubseneca.comercial.PagoMensualidad;
import clubseneca.comercial.Producto;
import clubseneca.comercial.StockArticulo;
import clubseneca.comercial.SugerenciaProducto;
import clubseneca.comercial.UbicacionInventario;
import clubseneca.comercial.Venta;
import clubseneca.usuarios.Administrador;
import clubseneca.usuarios.Empleado;
import clubseneca.usuarios.Socio;
import clubseneca.usuarios.Usuario;

public class GestionComercial implements Serializable {
    private List<Producto> productos;
    private List<Venta> ventas;
    private List<UbicacionInventario> ubicaciones;
    private List<PagoMensualidad> mensualidades;
    private List<SugerenciaProducto> sugerencias;

    public GestionComercial() {
        productos = new ArrayList<>();
        ventas = new ArrayList<>();
        ubicaciones = new ArrayList<>();
        mensualidades = new ArrayList<>();
        sugerencias = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            return;
        }

        if (buscarProducto(producto.getNombre()) == null) {
            productos.add(producto);
        }
    }

    public Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }

        return null;
    }

    public void agregarUbicacion(UbicacionInventario ubicacion) {
        if (ubicacion == null) {
            return;
        }

        if (!ubicaciones.contains(ubicacion)) {
            ubicaciones.add(ubicacion);
        }
    }

    public boolean registrarVenta(Venta venta) {
        if (venta == null) {
            return false;
        }

        if (venta.getDetalles().isEmpty()) {
            return false;
        }

        Usuario comprador = venta.getComprador();

        if (comprador == null) {
            return false;
        }

        if (!comprador.esSocio() && !comprador.esEmpleado()) {
            return false;
        }

        if (!hayInventarioSuficiente(venta)) {
            return false;
        }

        for (DetalleVenta detalle : venta.getDetalles()) {
            if (detalle.getProducto().esArticuloTienda()) {
                ArticuloTienda articulo = (ArticuloTienda) detalle.getProducto();
                descontarInventario(articulo, detalle.getCantidad());
            }
        }

        aplicarPuntosRedimidos(venta);
        ventas.add(venta);

        double puntos = venta.calcularPuntosGenerados();

        if (comprador.esSocio()) {
            Socio socio = (Socio) comprador;
            socio.agregarPuntos(puntos);
        }

        if (comprador.esEmpleado()) {
            Empleado empleado = (Empleado) comprador;
            empleado.agregarPuntos(puntos);
        }

        return true;
    }

    public StockArticulo buscarStock(ArticuloTienda articulo, UbicacionInventario ubicacion) {
        if (articulo == null || ubicacion == null) {
            return null;
        }

        return ubicacion.buscarStock(articulo);
    }

    public boolean moverArticulo(ArticuloTienda articulo, UbicacionInventario origen, UbicacionInventario destino, int cantidad) {
        if (articulo == null || origen == null || destino == null) {
            return false;
        }

        if (cantidad <= 0 || origen == destino) {
            return false;
        }

        StockArticulo stockOrigen = buscarStock(articulo, origen);

        if (stockOrigen == null) {
            return false;
        }

        if (!stockOrigen.disminuirCantidad(cantidad)) {
            return false;
        }

        StockArticulo stockDestino = buscarStock(articulo, destino);

        if (stockDestino == null) {
            stockDestino = new StockArticulo(articulo, destino, cantidad);
            destino.agregarStock(stockDestino);
        } else {
            stockDestino.aumentarCantidad(cantidad);
        }

        return true;
    }

    public void reabastecerArticulo(ArticuloTienda articulo, UbicacionInventario ubicacion, int cantidad) {
        if (articulo == null || ubicacion == null || cantidad <= 0) {
            return;
        }

        StockArticulo stock = buscarStock(articulo, ubicacion);

        if (stock == null) {
            stock = new StockArticulo(articulo, ubicacion, cantidad);
            ubicacion.agregarStock(stock);
        } else {
            stock.aumentarCantidad(cantidad);
        }
    }

    public void registrarMensualidad(PagoMensualidad pago) {
        if (pago != null) {
            mensualidades.add(pago);
        }
    }

    public void registrarSugerencia(SugerenciaProducto sugerencia) {
        if (sugerencia != null) {
            sugerencias.add(sugerencia);
        }
    }

    public void aprobarSugerencia(SugerenciaProducto sugerencia, Administrador administrador) {
        if (sugerencia == null || administrador == null) {
            return;
        }

        if (sugerencia.getEstado().equals("PENDIENTE")) {
            sugerencia.aprobar(administrador);
        }
    }

    public void rechazarSugerencia(SugerenciaProducto sugerencia, Administrador administrador) {
        if (sugerencia == null || administrador == null) {
            return;
        }

        if (sugerencia.getEstado().equals("PENDIENTE")) {
            sugerencia.rechazar(administrador);
        }
    }

    public double calcularVentasPorRubro(String rubro, Date fechaInicio, Date fechaFin) {
        double total = 0;

        if (rubro == null || fechaInicio == null || fechaFin == null) {
            return total;
        }

        if (rubro.equalsIgnoreCase("mensualidades")) {
            for (PagoMensualidad pago : mensualidades) {
                if (dentroPeriodo(pago.getFecha(), fechaInicio, fechaFin)) {
                    total += pago.getValor();
                }
            }

            return total;
        }

        for (Venta venta : ventas) {
            if (dentroPeriodo(venta.getFecha(), fechaInicio, fechaFin)) {
                if (rubro.equalsIgnoreCase("tienda")) {
                    total += venta.calcularSubtotalTienda();
                }

                if (rubro.equalsIgnoreCase("cafeteria")
                        || rubro.equalsIgnoreCase("cafetería")) {
                    total += venta.calcularSubtotalCafeteria();
                }
            }
        }

        return total;
    }

    public List<Venta> getVentas() {
        return new ArrayList<>(ventas);
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public List<UbicacionInventario> getUbicaciones() {
        return new ArrayList<>(ubicaciones);
    }

    public List<PagoMensualidad> getMensualidades() {
        return new ArrayList<>(mensualidades);
    }

    public List<SugerenciaProducto> getSugerencias() {
        return new ArrayList<>(sugerencias);
    }

    private boolean hayInventarioSuficiente(Venta venta) {
        for (DetalleVenta detalle : venta.getDetalles()) {
            if (detalle.getProducto().esArticuloTienda()) {
                ArticuloTienda articulo = (ArticuloTienda) detalle.getProducto();

                if (cantidadTotal(articulo) < detalle.getCantidad()) {
                    return false;
                }
            }
        }

        return true;
    }

    private int cantidadTotal(ArticuloTienda articulo) {
        int total = 0;

        for (UbicacionInventario ubicacion : ubicaciones) {
            StockArticulo stock = ubicacion.buscarStock(articulo);

            if (stock != null) {
                total += stock.getCantidad();
            }
        }

        return total;
    }

    private void descontarInventario(ArticuloTienda articulo, int cantidad) {
        int faltante = cantidad;

        for (UbicacionInventario ubicacion : ubicaciones) {
            StockArticulo stock = ubicacion.buscarStock(articulo);

            if (stock != null && faltante > 0) {
                int retirar;

                if (stock.getCantidad() < faltante) {
                    retirar = stock.getCantidad();
                } else {
                    retirar = faltante;
                }

                stock.disminuirCantidad(retirar);
                faltante -= retirar;
            }
        }
    }

    private void aplicarPuntosRedimidos(Venta venta) {
        double puntos = venta.getPuntosRedimidos();

        if (puntos <= 0) {
            return;
        }

        Usuario comprador = venta.getComprador();

        if (comprador.esSocio()) {
            Socio socio = (Socio) comprador;
            socio.redimirPuntos(puntos);
        }

        if (comprador.esEmpleado()) {
            Empleado empleado = (Empleado) comprador;
            empleado.redimirPuntos(puntos);
        }
    }

    private boolean dentroPeriodo(Date fecha, Date inicio, Date fin) {
        if (fecha.before(inicio)) {
            return false;
        }

        if (fecha.after(fin)) {
            return false;
        }

        return true;
    }
}
