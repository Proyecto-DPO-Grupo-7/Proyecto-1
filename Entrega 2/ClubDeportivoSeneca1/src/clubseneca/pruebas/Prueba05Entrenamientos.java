package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.individual.Asistencia;
import clubseneca.individual.DisciplinaIndividual;
import clubseneca.individual.Instalacion;
import clubseneca.individual.SesionEntrenamiento;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.usuarios.Entrenador;
import clubseneca.usuarios.Socio;

public class Prueba05Entrenamientos {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        DisciplinaIndividual tenis = new DisciplinaIndividual("Tenis", 2);
        Instalacion cancha = new Instalacion("Cancha Principal", 4, false, tenis);
        tenis.agregarInstalacion(cancha);
        club.getGestionDeportiva().agregarDisciplina(tenis);

        Entrenador entrenador = new Entrenador("entrenador", "1234", "ENT15");
        Socio socio1 = new Socio("alumno1", "1234", new GregorianCalendar(2002, Calendar.APRIL, 1).getTime());
        Socio socio2 = new Socio("alumno2", "1234", new GregorianCalendar(2002, Calendar.MAY, 1).getTime());
        Socio noConvocado = new Socio("noConvocado", "1234", new GregorianCalendar(2002, Calendar.JUNE, 1).getTime());

        SesionEntrenamiento sesion = new SesionEntrenamiento(new Date(), "16:00", 90, entrenador, cancha, tenis);
        sesion.convocarSocio(socio1);
        sesion.convocarSocio(socio2);
        club.getGestionDeportiva().programarSesion(sesion);

        Asistencia a1 = new Asistencia(sesion, socio1, "ASISTIO");
        Asistencia a2 = new Asistencia(sesion, socio2, "LLEGO TARDE");
        Asistencia a3 = new Asistencia(sesion, noConvocado, "ASISTIO");
        System.out.println("Asistencia socio1 registrada: " + sesion.registrarAsistencia(a1));
        System.out.println("Asistencia socio2 registrada: " + sesion.registrarAsistencia(a2));
        System.out.println("No convocado rechazado: " + !sesion.registrarAsistencia(a3));
        System.out.println("Evaluacion valida: " + a1.registrarEvaluacion(5, "Buen entrenamiento"));
        System.out.println("Evaluacion invalida rechazada: " + !a2.registrarEvaluacion(7, "Fuera de rango"));
        System.out.println("Total convocados: " + sesion.getConvocados().size());
        System.out.println("Total asistencias: " + sesion.getAsistencias().size());
    }
}
