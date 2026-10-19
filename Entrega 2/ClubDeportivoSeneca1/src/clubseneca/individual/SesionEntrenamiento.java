package clubseneca.individual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import clubseneca.deportes.Equipo;
import clubseneca.usuarios.Entrenador;
import clubseneca.usuarios.Socio;

public class SesionEntrenamiento implements Serializable {
    private Date fecha;
    private String hora;
    private int duracionMinutos;
    private Entrenador entrenador;
    private Instalacion instalacion;
    private List<Socio> convocados;
    private List<Asistencia> asistencias;
    private Equipo equipo;
    private DisciplinaIndividual disciplina;

    public SesionEntrenamiento(Date fecha, String hora, int duracionMinutos, Entrenador entrenador, Instalacion instalacion, Equipo equipo) {
        this.fecha = fecha;
        this.hora = hora;
        this.duracionMinutos = duracionMinutos;
        this.entrenador = entrenador;
        this.instalacion = instalacion;
        this.equipo = equipo;
        this.disciplina = null;
        this.convocados = new ArrayList<>();
        this.asistencias = new ArrayList<>();
    }

    public SesionEntrenamiento(Date fecha, String hora, int duracionMinutos, Entrenador entrenador, Instalacion instalacion, DisciplinaIndividual disciplina) {
        this.fecha = fecha;
        this.hora = hora;
        this.duracionMinutos = duracionMinutos;
        this.entrenador = entrenador;
        this.instalacion = instalacion;
        this.equipo = null;
        this.disciplina = disciplina;
        this.convocados = new ArrayList<>();
        this.asistencias = new ArrayList<>();
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public Instalacion getInstalacion() {
        return instalacion;
    }

    public List<Socio> getConvocados() {
        return new ArrayList<>(convocados);
    }

    public List<Asistencia> getAsistencias() {
        return new ArrayList<>(asistencias);
    }

    public boolean convocarSocio(Socio socio) {
        if (socio == null || !socio.isActivo()) {
            return false;
        }
        for (Socio convocado : convocados) {
            if (convocado.getLogin().equals(socio.getLogin())) {
                return false;
            }
        }
        convocados.add(socio);
        return true;
    }

    public boolean estaConvocado(Socio socio) {
        for (Socio convocado : convocados) {
            if (convocado.getLogin().equals(socio.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarAsistencia(Asistencia asistencia) {
        if (asistencia == null || asistencia.getSesion() != this || !estaConvocado(asistencia.getSocio())) {
            return false;
        }
        for (Asistencia existente : asistencias) {
            if (existente.getSocio().getLogin().equals(asistencia.getSocio().getLogin())) {
                return false;
            }
        }
        asistencias.add(asistencia);
        return true;
    }

    public boolean esEntrenamientoEquipo() {
        return equipo != null;
    }

    public boolean esEntrenamientoDisciplina() {
        return disciplina != null;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public DisciplinaIndividual getDisciplina() {
        return disciplina;
    }
}
