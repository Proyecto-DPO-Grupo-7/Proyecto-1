package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.deportes.Categoria;
import clubseneca.deportes.Competencia;
import clubseneca.deportes.Deporte;
import clubseneca.deportes.Equipo;
import clubseneca.deportes.EstadisticaFutbol;
import clubseneca.deportes.InscripcionCompetencia;
import clubseneca.deportes.JugadorCompetencia;
import clubseneca.deportes.JugadorEquipo;
import clubseneca.deportes.PartidoInterno;
import clubseneca.usuarios.Socio;

public class Prueba02EstadisticasSanciones {
    public static void main(String[] args) {
        Deporte futbol = new Deporte("Futbol", 7);
        Categoria mayores = new Categoria("Mayores", 18, 99);
        Equipo equipo = new Equipo("Seneca", 15, futbol, mayores);
        Socio socio = new Socio("jugador", "1234", new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime());
        equipo.agregarJugador(socio, "Delantero", 9);
        JugadorEquipo jugador = equipo.buscarJugador(socio);

        PartidoInterno partido = new PartidoInterno(new Date(), equipo, equipo);
        EstadisticaFutbol estadistica = new EstadisticaFutbol(jugador, partido);
        estadistica.registrarAmarilla();
        System.out.println("Habilitado con una amarilla: " + jugador.isHabilitado());
        estadistica.registrarAmarilla();
        System.out.println("Habilitado con dos amarillas: " + jugador.isHabilitado());
        jugador.cumplirSuspension();
        System.out.println("Habilitado despues de cumplir suspension: " + jugador.isHabilitado());
        estadistica.registrarRoja();
        System.out.println("Habilitado despues de roja: " + jugador.isHabilitado());

        Competencia competencia = new Competencia("ASCUN", "ASCUN", 2026, "Grupos y eliminacion", futbol, mayores);
        InscripcionCompetencia inscripcion = new InscripcionCompetencia(new Date(), "A", equipo, competencia);
        jugador.cumplirSuspension();
        inscripcion.agregarJugador(jugador);
        JugadorCompetencia jugadorCompetencia = inscripcion.getNomina().get(0);
        jugadorCompetencia.registrarAmarilla();
        jugadorCompetencia.registrarAmarilla();
        System.out.println("Habilitado en competencia con dos amarillas: " + jugadorCompetencia.isHabilitado());
    }
}
