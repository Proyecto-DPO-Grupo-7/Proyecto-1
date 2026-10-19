package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.deportes.Categoria;
import clubseneca.deportes.Deporte;
import clubseneca.deportes.Equipo;
import clubseneca.deportes.EstadisticaFutbol;
import clubseneca.deportes.JugadorEquipo;
import clubseneca.deportes.PartidoInterno;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Socio;

public class Prueba01EquiposJugadoresPartidos {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        Deporte futbol = new Deporte("Futbol", 7);
        Categoria mayores = new Categoria("Mayores", 18, 99);
        Equipo senecaA = new Equipo("Seneca A", 15, futbol, mayores);
        Equipo senecaB = new Equipo("Seneca B", 15, futbol, mayores);

        club.getGestionDeportiva().agregarEquipo(senecaA);
        club.getGestionDeportiva().agregarEquipo(senecaB);

        Socio socio1 = null;

        for (int i = 1; i <= 14; i++) {
            Date nacimiento = new GregorianCalendar(2000, Calendar.JANUARY, i).getTime();
            Socio socio = new Socio("socio" + i, "1234", nacimiento);

            if (i == 1) {
                socio1 = socio;
            }

            club.getGestionUsuarios().agregarUsuario(socio);

            Equipo equipo;
            int camiseta;

            if (i <= 7) {
                equipo = senecaA;
                camiseta = i;
            } else {
                equipo = senecaB;
                camiseta = i - 7;
            }

            boolean inscrito = club.getGestionDeportiva().inscribirSocioEnEquipo(
                    socio, equipo, "Jugador", camiseta);

            System.out.println("Inscripcion " + socio.getLogin() + ": " + inscrito);
        }

        Socio extra = new Socio(
                "extra",
                "1234",
                new GregorianCalendar(2000, Calendar.JANUARY, 20).getTime());

        boolean camisetaRepetida = senecaA.agregarJugador(extra, "Jugador", 1);

        System.out.println("Camiseta repetida aceptada: " + camisetaRepetida);
        System.out.println("Seneca A puede jugar: " + senecaA.puedeJugarPartido());
        System.out.println("Seneca B puede jugar: " + senecaB.puedeJugarPartido());

        PartidoInterno partido = new PartidoInterno(new Date(), senecaA, senecaB);

        System.out.println("Partido compatible: " + partido.equiposCompatibles());
        System.out.println("Partido registrado: " + club.getGestionDeportiva().registrarPartido(partido));

        partido.registrarMarcador(2, 1);

        JugadorEquipo jugador = senecaA.buscarJugador(socio1);
        EstadisticaFutbol estadistica = new EstadisticaFutbol(jugador, partido);

        estadistica.registrarGol();
        estadistica.registrarAsistencia();
        partido.agregarEstadistica(estadistica);

        System.out.println("Marcador: " + partido.getMarcadorEquipo1() + " - " + partido.getMarcadorEquipo2());
        System.out.println("Goles socio1: " + estadistica.getGoles());
        System.out.println("Asistencias socio1: " + estadistica.getAsistencias());
    }
}
