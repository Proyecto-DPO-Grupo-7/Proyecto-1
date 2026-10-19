package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.deportes.Categoria;
import clubseneca.deportes.Competencia;
import clubseneca.deportes.Deporte;
import clubseneca.deportes.Equipo;
import clubseneca.deportes.InscripcionCompetencia;
import clubseneca.deportes.JugadorEquipo;
import clubseneca.deportes.PartidoCompetencia;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Socio;

public class Prueba03CompetenciasExternas {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        Deporte futbol = new Deporte("Futbol", 7);
        Categoria mayores = new Categoria("Mayores", 18, 99);
        Equipo equipo = new Equipo("Seneca Mayores", 20, futbol, mayores);
        club.getGestionDeportiva().agregarEquipo(equipo);

        for (int i = 1; i <= 7; i++) {
            Socio socio = new Socio("comp" + i, "1234", new GregorianCalendar(2000, Calendar.FEBRUARY, i).getTime());
            club.getGestionUsuarios().agregarUsuario(socio);
            club.getGestionDeportiva().inscribirSocioEnEquipo(socio, equipo, "Jugador", i);
        }

        Competencia competencia = new Competencia("Torneo de los Cerros", "Universidades", 2026, "Liga", futbol, mayores);
        club.getGestionDeportiva().registrarCompetencia(competencia);
        InscripcionCompetencia inscripcion = club.getGestionDeportiva().inscribirEquipoEnCompetencia(equipo, competencia, new Date(), "Unico");
        for (JugadorEquipo jugador : equipo.getJugadores()) {
            inscripcion.agregarJugador(jugador);
        }
        PartidoCompetencia partido = new PartidoCompetencia(new Date(), "Universidad Rival", "Cancha Central", "Liga", competencia, inscripcion);
        System.out.println("Nomina habilitada: " + inscripcion.cantidadJugadoresHabilitados());
        System.out.println("Partido puede disputarse: " + partido.puedeDisputarse());
        partido.registrarMarcador(2, 1);
        competencia.registrarPartido(partido);
        System.out.println("Puntos por victoria en futbol: " + competencia.calcularPuntos(2, 1));
        System.out.println("Puntos por empate en futbol: " + competencia.calcularPuntos(1, 1));
    }
}
