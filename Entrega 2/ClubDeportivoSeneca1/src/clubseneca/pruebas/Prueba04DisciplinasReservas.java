package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.individual.DisciplinaIndividual;
import clubseneca.individual.EstadisticaTenisPadel;
import clubseneca.individual.InscripcionDisciplina;
import clubseneca.individual.Instalacion;
import clubseneca.individual.PartidoPractica;
import clubseneca.individual.Reserva;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Entrenador;
import clubseneca.usuarios.Socio;

public class Prueba04DisciplinasReservas {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        DisciplinaIndividual tenis = new DisciplinaIndividual("Tenis", 2);
        Instalacion cancha1 = new Instalacion("Cancha 1", 4, false, tenis);
        Instalacion cancha2 = new Instalacion("Cancha 2", 4, false, tenis);
        tenis.agregarInstalacion(cancha1);
        tenis.agregarInstalacion(cancha2);
        club.getGestionDeportiva().agregarDisciplina(tenis);

        Socio socio = new Socio("tenista", "1234", new GregorianCalendar(2001, Calendar.MARCH, 1).getTime());
        Entrenador entrenador = new Entrenador("coach", "1234", "COACH15");
        club.getGestionUsuarios().agregarUsuario(socio);
        club.getGestionUsuarios().agregarUsuario(entrenador);

        InscripcionDisciplina inscripcionSocio = club.getGestionDeportiva().inscribirUsuarioEnDisciplina(socio, tenis, "Principiante");
        InscripcionDisciplina inscripcionEntrenador = club.getGestionDeportiva().inscribirUsuarioEnDisciplina(entrenador, tenis, "Avanzado");
        System.out.println("Principiante puede enfrentar entrenador avanzado: " + inscripcionSocio.puedeEnfrentar(inscripcionEntrenador));

        Date fecha = new GregorianCalendar(2026, Calendar.OCTOBER, 20).getTime();
        Reserva reserva1 = club.getGestionDeportiva().reservarInstalacion(socio, cancha1, fecha, "10:00", "11:00", 2, "Sencillos");
        Reserva reservaRepetida = club.getGestionDeportiva().reservarInstalacion(socio, cancha1, fecha, "10:30", "11:30", 2, "Sencillos");
        Reserva reserva2 = club.getGestionDeportiva().reservarInstalacion(socio, cancha2, fecha, "10:00", "11:00", 4, "Dobles");
        System.out.println("Primera reserva creada: " + (reserva1 != null));
        System.out.println("Reserva cruzada rechazada: " + (reservaRepetida == null));
        System.out.println("Segunda cancha reservada: " + (reserva2 != null));

        PartidoPractica partido = new PartidoPractica(fecha, tenis);
        partido.agregarParticipante(inscripcionSocio);
        partido.agregarParticipante(inscripcionEntrenador);
        System.out.println("Partido con excepcion de entrenador: " + partido.puedeProgramarse());
        club.getGestionDeportiva().programarPartidoPractica(partido);
        EstadisticaTenisPadel e1 = new EstadisticaTenisPadel(inscripcionSocio, partido);
        EstadisticaTenisPadel e2 = new EstadisticaTenisPadel(inscripcionEntrenador, partido);
        e1.registrarSetGanado();
        e1.registrarSetGanado();
        e2.registrarSetGanado();
        partido.agregarEstadistica(e1);
        partido.agregarEstadistica(e2);
        partido.actualizarRankings();
        System.out.println("Ranking socio: " + inscripcionSocio.getRankingElo());
        System.out.println("Ranking entrenador: " + inscripcionEntrenador.getRankingElo());
    }
}
