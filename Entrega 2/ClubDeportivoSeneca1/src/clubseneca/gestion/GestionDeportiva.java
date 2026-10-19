package clubseneca.gestion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import clubseneca.deportes.Competencia;
import clubseneca.deportes.Deporte;
import clubseneca.deportes.Equipo;
import clubseneca.deportes.InscripcionCompetencia;
import clubseneca.deportes.JugadorEquipo;
import clubseneca.deportes.Partido;
import clubseneca.individual.DisciplinaIndividual;
import clubseneca.individual.InscripcionDisciplina;
import clubseneca.individual.Instalacion;
import clubseneca.individual.PartidoPractica;
import clubseneca.individual.Reserva;
import clubseneca.individual.SesionEntrenamiento;
import clubseneca.usuarios.Socio;
import clubseneca.usuarios.Usuario;

public class GestionDeportiva implements Serializable {
    private List<Equipo> equipos;
    private List<Competencia> competencias;
    private List<Partido> partidos;
    private List<DisciplinaIndividual> disciplinas;
    private List<Reserva> reservas;
    private List<SesionEntrenamiento> sesionesEntrenamiento;

    public GestionDeportiva() {
        equipos = new ArrayList<>();
        competencias = new ArrayList<>();
        partidos = new ArrayList<>();
        disciplinas = new ArrayList<>();
        reservas = new ArrayList<>();
        sesionesEntrenamiento = new ArrayList<>();
    }

    public boolean agregarEquipo(Equipo equipo) {
        if (equipo == null) {
            return false;
        }

        if (buscarEquipo(equipo.getNombre()) != null) {
            return false;
        }

        equipos.add(equipo);
        return true;
    }

    public Equipo buscarEquipo(String nombre) {
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombre)) {
                return equipo;
            }
        }

        return null;
    }

    public boolean inscribirSocioEnEquipo(Socio socio, Equipo equipo, String posicion, int numeroCamiseta) {
        if (socio == null || equipo == null) {
            return false;
        }

        if (socioYaEstaEnDeporte(socio, equipo.getDeporte())) {
            return false;
        }

        return equipo.agregarJugador(socio, posicion, numeroCamiseta);
    }

    public void registrarCompetencia(Competencia competencia) {
        if (competencia != null && !competencias.contains(competencia)) {
            competencias.add(competencia);
        }
    }

    public InscripcionCompetencia inscribirEquipoEnCompetencia(Equipo equipo, Competencia competencia, Date fecha, String grupo) {
        if (equipo == null || competencia == null) {
            return null;
        }

        if (!equipo.isActivo()) {
            return null;
        }

        boolean mismoDeporte = equipo.getDeporte().getNombre().equalsIgnoreCase(competencia.getDeporte().getNombre());
        boolean mismaCategoria = equipo.getCategoria().getNombre().equalsIgnoreCase(competencia.getCategoria().getNombre());

        if (!mismoDeporte || !mismaCategoria) {
            return null;
        }

        if (!equipo.puedeJugarPartido()) {
            return null;
        }

        InscripcionCompetencia inscripcion = new InscripcionCompetencia(fecha, grupo, equipo, competencia);
        competencia.agregarInscripcion(inscripcion);
        return inscripcion;
    }

    public boolean registrarPartido(Partido partido) {
        if (partido == null) {
            return false;
        }

        if (!partido.puedeDisputarse()) {
            return false;
        }

        partidos.add(partido);
        return true;
    }

    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    public List<Competencia> getCompetencias() {
        return new ArrayList<>(competencias);
    }

    public List<Partido> getPartidos() {
        return new ArrayList<>(partidos);
    }

    public void agregarDisciplina(DisciplinaIndividual disciplina) {
        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public List<DisciplinaIndividual> getDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    public InscripcionDisciplina inscribirUsuarioEnDisciplina(Usuario usuario, DisciplinaIndividual disciplina, String nivel) {
        if (usuario == null || disciplina == null) {
            return null;
        }

        if (!usuario.esSocio() && !usuario.esEntrenador()) {
            return null;
        }

        if (!disciplina.nivelValido(nivel)) {
            return null;
        }

        if (buscarInscripcion(usuario, disciplina) != null) {
            return null;
        }

        InscripcionDisciplina inscripcion = new InscripcionDisciplina(usuario, disciplina, nivel);
        disciplina.agregarInscripcion(inscripcion);
        return inscripcion;
    }

    public InscripcionDisciplina buscarInscripcion(Usuario usuario, DisciplinaIndividual disciplina) {
        if (usuario == null || disciplina == null) {
            return null;
        }

        for (InscripcionDisciplina inscripcion : disciplina.getInscripciones()) {
            if (inscripcion.getTitular().getLogin().equals(usuario.getLogin())) {
                return inscripcion;
            }
        }

        return null;
    }

    public Reserva reservarInstalacion(Socio socio, Instalacion instalacion, Date fecha, String horaInicio, String horaFin, int cantidadJugadores, String modalidad) {
        if (socio == null || instalacion == null) {
            return null;
        }

        if (!socio.isActivo()) {
            return null;
        }

        DisciplinaIndividual disciplina = instalacion.getDisciplina();

        if (!instalacion.permiteCantidad(cantidadJugadores)) {
            return null;
        }

        if (!disciplina.modalidadValida(modalidad, cantidadJugadores)) {
            return null;
        }

        if (!puedeUsarInstalacion(socio, instalacion)) {
            return null;
        }

        if (!instalacionDisponible(instalacion, fecha, horaInicio, horaFin)) {
            return null;
        }

        if (!cupoDisciplinaDisponible(disciplina, fecha, horaInicio, horaFin)) {
            return null;
        }

        Reserva reserva = new Reserva(fecha, horaInicio, horaFin, cantidadJugadores, modalidad, socio, instalacion);
        reservas.add(reserva);
        return reserva;
    }

    public boolean programarPartidoPractica(PartidoPractica partido) {
        if (partido == null) {
            return false;
        }

        if (!partido.puedeProgramarse()) {
            return false;
        }

        partido.getDisciplina().agregarPartidoPractica(partido);
        return true;
    }

    public boolean programarSesion(SesionEntrenamiento sesion) {
        if (sesion == null) {
            return false;
        }

        if (sesion.getEntrenador() == null || sesion.getInstalacion() == null) {
            return false;
        }

        if (sesion.esEntrenamientoEquipo() && sesion.esEntrenamientoDisciplina()) {
            return false;
        }

        if (!sesion.esEntrenamientoEquipo() && !sesion.esEntrenamientoDisciplina()) {
            return false;
        }

        sesionesEntrenamiento.add(sesion);
        return true;
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    public List<SesionEntrenamiento> getSesionesEntrenamiento() {
        return new ArrayList<>(sesionesEntrenamiento);
    }

    public boolean puedeUsarInstalacion(Socio socio, Instalacion instalacion) {
        if (socio.isTieneBebidaCaliente() && instalacion.restringeBebidaCaliente()) {
            return false;
        }

        return true;
    }

    private boolean socioYaEstaEnDeporte(Socio socio, Deporte deporte) {
        for (Equipo equipo : equipos) {
            if (equipo.getDeporte().getNombre().equalsIgnoreCase(deporte.getNombre())) {
                JugadorEquipo jugador = equipo.buscarJugador(socio);

                if (jugador != null) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean instalacionDisponible(Instalacion instalacion, Date fecha, String horaInicio, String horaFin) {
        Reserva candidata = new Reserva(fecha, horaInicio, horaFin, 1, "Sencillos", null, instalacion);

        for (Reserva reserva : reservas) {
            if (reserva.seCruzaCon(candidata)) {
                return false;
            }
        }

        return true;
    }

    private boolean cupoDisciplinaDisponible(DisciplinaIndividual disciplina, Date fecha, String horaInicio, String horaFin) {
        int simultaneas = 0;

        for (Reserva reserva : reservas) {
            if (reserva.getInstalacion().getDisciplina() == disciplina && reserva.getFecha().equals(fecha)) {
                boolean cruza = horaInicio.compareTo(reserva.getHoraFin()) < 0
                        && horaFin.compareTo(reserva.getHoraInicio()) > 0;

                if (cruza) {
                    simultaneas++;
                }
            }
        }

        return simultaneas < disciplina.getMaxInstalacionesSimultaneas();
    }
}
