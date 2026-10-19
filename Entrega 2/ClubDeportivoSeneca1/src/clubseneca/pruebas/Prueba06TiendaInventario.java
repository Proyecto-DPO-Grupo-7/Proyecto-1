package clubseneca.pruebas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.comercial.ArticuloTienda;
import clubseneca.comercial.StockArticulo;
import clubseneca.comercial.UbicacionInventario;
import clubseneca.comercial.Venta;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Empleado;
import clubseneca.usuarios.Entrenador;

public class Prueba06TiendaInventario {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        ArticuloTienda balon = new ArticuloTienda("Balon", 100000, "Balones");
        UbicacionInventario tienda = new UbicacionInventario("Tienda");
        UbicacionInventario bodega = new UbicacionInventario("Bodega");
        club.getGestionComercial().agregarProducto(balon);
        club.getGestionComercial().agregarUbicacion(tienda);
        club.getGestionComercial().agregarUbicacion(bodega);
        club.getGestionComercial().reabastecerArticulo(balon, bodega, 10);
        club.getGestionComercial().moverArticulo(balon, bodega, tienda, 4);

        Empleado empleado = new Entrenador("empleado", "1234", "DESC15");
        Venta venta = new Venta(new Date(), empleado);
        venta.agregarDetalle(balon, 2);
        venta.aplicarDescuentoEmpleado();
        System.out.println("Subtotal: " + venta.calcularSubtotal());
        System.out.println("IVA: " + venta.calcularImpuestos());
        System.out.println("Descuento: " + venta.calcularDescuento());
        System.out.println("Total: " + venta.calcularTotal());
        System.out.println("Venta registrada: " + club.registrarCompra(empleado, venta, null));
        StockArticulo stockTienda = tienda.buscarStock(balon);
        System.out.println("Stock restante en tienda: " + stockTienda.getCantidad());
        System.out.println("Puntos del empleado: " + empleado.getPuntosFidelidad());

        Venta ventaInvalida = new Venta(new Date(), empleado);
        ventaInvalida.agregarDetalle(balon, 50);
        System.out.println("Compra sin inventario rechazada: " + !club.registrarCompra(empleado, ventaInvalida, null));
    }
}
