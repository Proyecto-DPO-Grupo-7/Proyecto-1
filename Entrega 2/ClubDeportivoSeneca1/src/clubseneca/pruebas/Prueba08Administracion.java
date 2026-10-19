package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.comercial.ArticuloTienda;
import clubseneca.comercial.StockArticulo;
import clubseneca.comercial.SugerenciaProducto;
import clubseneca.comercial.UbicacionInventario;
import clubseneca.deportes.Categoria;
import clubseneca.deportes.Deporte;
import clubseneca.deportes.Equipo;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Administrador;
import clubseneca.usuarios.Entrenador;
import clubseneca.usuarios.Socio;

public class Prueba08Administracion {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        Administrador admin = new Administrador("admin", "admin123");
        club.getGestionUsuarios().agregarUsuario(admin);

        Socio socio = new Socio("nuevoSocio", "1234", new GregorianCalendar(2000, Calendar.AUGUST, 1).getTime());
        club.getGestionUsuarios().agregarUsuario(socio);
        System.out.println("Socio creado: " + (club.getGestionUsuarios().buscarUsuario("nuevoSocio") != null));
        club.getGestionUsuarios().darDeBajaSocio("nuevoSocio");
        System.out.println("Socio dado de baja: " + !socio.isActivo());

        Equipo equipo = new Equipo("Equipo Admin", 15, new Deporte("Baloncesto", 5), new Categoria("Mayores", 18, 99));
        club.getGestionDeportiva().agregarEquipo(equipo);
        System.out.println("Equipo creado: " + (club.getGestionDeportiva().buscarEquipo("Equipo Admin") != null));
        equipo.darDeBaja();
        System.out.println("Equipo dado de baja: " + !equipo.isActivo());

        ArticuloTienda raqueta = new ArticuloTienda("Raqueta", 250000, "Raquetas");
        UbicacionInventario bodega = new UbicacionInventario("Bodega");
        UbicacionInventario tienda = new UbicacionInventario("Tienda");
        club.getGestionComercial().agregarProducto(raqueta);
        club.getGestionComercial().agregarUbicacion(bodega);
        club.getGestionComercial().agregarUbicacion(tienda);
        club.getGestionComercial().reabastecerArticulo(raqueta, bodega, 8);
        club.getGestionComercial().moverArticulo(raqueta, bodega, tienda, 3);
        StockArticulo stockTienda = tienda.buscarStock(raqueta);
        System.out.println("Stock movido a tienda: " + stockTienda.getCantidad());

        Entrenador empleado = new Entrenador("empleadoSug", "1234", "COD15");
        SugerenciaProducto sugerencia = new SugerenciaProducto(new Date(), "Articulo", "Nueva malla", "Malla profesional", empleado);
        club.getGestionComercial().registrarSugerencia(sugerencia);
        club.getGestionComercial().aprobarSugerencia(sugerencia, admin);
        System.out.println("Sugerencia aprobada: " + sugerencia.getEstado());

        club.guardarDatos();
        ClubDeportivoSeneca clubCargado = new ClubDeportivoSeneca();
        clubCargado.cargarDatos();
        System.out.println("Usuarios cargados desde archivo: " + clubCargado.getGestionUsuarios().getUsuarios().size());
        System.out.println("Productos cargados desde archivo: " + clubCargado.getGestionComercial().getProductos().size());
    }
}
