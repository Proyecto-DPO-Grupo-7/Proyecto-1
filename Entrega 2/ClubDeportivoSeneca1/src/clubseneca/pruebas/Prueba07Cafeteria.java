package clubseneca.pruebas;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.comercial.Bebida;
import clubseneca.comercial.Snack;
import clubseneca.comercial.Venta;
import clubseneca.individual.DisciplinaIndividual;
import clubseneca.individual.Instalacion;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Socio;

public class Prueba07Cafeteria {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        Snack barra = new Snack("Barra energetica", 8000, Arrays.asList("Mani", "Gluten"));
        Bebida cafe = new Bebida("Cafe", 6000, true);
        Bebida jugo = new Bebida("Jugo", 7000, false);
        club.getGestionComercial().agregarProducto(barra);
        club.getGestionComercial().agregarProducto(cafe);
        club.getGestionComercial().agregarProducto(jugo);

        Socio socio = new Socio("socioCafe", "1234", new GregorianCalendar(2000, Calendar.JULY, 1).getTime());
        System.out.println("Alergenos de la barra: " + barra.getAlergenos());

        DisciplinaIndividual padel = new DisciplinaIndividual("Padel", 2);
        Instalacion canchaPadel = new Instalacion("Padel 1", 4, false, padel);
        Venta ventaCaliente = new Venta(new Date(), socio);
        ventaCaliente.agregarDetalle(cafe, 1);
        System.out.println("Bebida caliente en padel rechazada: " + !club.registrarCompra(socio, ventaCaliente, canchaPadel));

        Venta ventaCafeteria = new Venta(new Date(), socio);
        ventaCafeteria.agregarDetalle(jugo, 1);
        ventaCafeteria.agregarDetalle(barra, 1);
        ventaCafeteria.configurarPropina(0.12);
        System.out.println("Impuesto cafeteria: " + ventaCafeteria.calcularImpuestos());
        System.out.println("Propina modificada: " + ventaCafeteria.calcularPropina());
        System.out.println("Venta cafeteria registrada: " + club.registrarCompra(socio, ventaCafeteria, null));
        System.out.println("Puntos generados: " + socio.getPuntosFidelidad());
    }
}
