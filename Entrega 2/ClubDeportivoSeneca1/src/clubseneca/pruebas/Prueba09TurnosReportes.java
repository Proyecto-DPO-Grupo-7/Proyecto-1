package clubseneca.pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import clubseneca.comercial.PagoMensualidad;
import clubseneca.gestion.GestionTurnos;
import clubseneca.principal.ClubDeportivoSeneca;
import clubseneca.turnos.SolicitudCambioTurno;
import clubseneca.turnos.TurnoSemanal;
import clubseneca.usuarios.Administrador;
import clubseneca.usuarios.Entrenador;
import clubseneca.usuarios.Fisioterapeuta;
import clubseneca.usuarios.Socio;

public class Prueba09TurnosReportes {
    public static void main(String[] args) {
        ClubDeportivoSeneca club = new ClubDeportivoSeneca();
        GestionTurnos gestion = club.getGestionTurnos();
        Administrador admin = new Administrador("adminTurnos", "1234");

        Entrenador e1 = new Entrenador("e1", "1234", "E1");
        Entrenador e2 = new Entrenador("e2", "1234", "E2");
        Entrenador e3 = new Entrenador("e3", "1234", "E3");
        Entrenador e4 = new Entrenador("e4", "1234", "E4");
        Entrenador e5 = new Entrenador("e5", "1234", "E5");
        Fisioterapeuta f1 = new Fisioterapeuta("f1", "1234", "F1");
        Fisioterapeuta f2 = new Fisioterapeuta("f2", "1234", "F2");

        TurnoSemanal t1 = new TurnoSemanal("Lunes", "08:00", "12:00", e1);
        gestion.asignarTurno(t1);
        gestion.asignarTurno(new TurnoSemanal("Lunes", "08:00", "12:00", e2));
        gestion.asignarTurno(new TurnoSemanal("Lunes", "08:00", "12:00", e3));
        gestion.asignarTurno(new TurnoSemanal("Lunes", "08:00", "12:00", f1));
        gestion.asignarTurno(new TurnoSemanal("Martes", "08:00", "12:00", e4));
        gestion.asignarTurno(new TurnoSemanal("Martes", "08:00", "12:00", e5));
        gestion.asignarTurno(new TurnoSemanal("Martes", "08:00", "12:00", f2));

        TurnoSemanal propuesto = new TurnoSemanal("Martes", "08:00", "12:00", e1);
        SolicitudCambioTurno solicitud = new SolicitudCambioTurno(new Date(), e1, t1, propuesto);
        gestion.registrarSolicitud(solicitud);
        System.out.println("Solicitud aprobada: " + gestion.aprobarSolicitud(solicitud, admin));
        System.out.println("Estado solicitud: " + solicitud.getEstado());

        Socio socio = new Socio("mensualidad", "1234", new GregorianCalendar(2000, Calendar.SEPTEMBER, 1).getTime());
        Date hoy = new Date();
        club.getGestionComercial().registrarMensualidad(new PagoMensualidad(hoy, 180000, socio));
        double mensualidades = club.getGestionComercial().calcularVentasPorRubro("mensualidades", hoy, hoy);
        System.out.println("Reporte mensualidades: " + mensualidades);
        System.out.println("Cantidad de partidos registrados: " + club.getGestionDeportiva().getPartidos().size());
        System.out.println("Cantidad de sesiones registradas: " + club.getGestionDeportiva().getSesionesEntrenamiento().size());
    }
}
