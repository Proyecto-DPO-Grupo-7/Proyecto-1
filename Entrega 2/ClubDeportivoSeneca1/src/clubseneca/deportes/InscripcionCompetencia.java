package clubseneca.deportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InscripcionCompetencia implements Serializable {
    private Date fechaInscripcion;
    private String grupo;
    private Equipo equipo;
    private Competencia competencia;
    private List<JugadorCompetencia> nomina;

    public InscripcionCompetencia(Date fechaInscripcion, String grupo, Equipo equipo, Competencia competencia) {
        this.fechaInscripcion = fechaInscripcion;
        this.grupo = grupo;
        this.equipo = equipo;
        this.competencia = competencia;
        this.nomina = new ArrayList<>();
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public String getGrupo() {
        return grupo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public List<JugadorCompetencia> getNomina() {
        return new ArrayList<>(nomina);
    }

    public boolean agregarJugador(JugadorEquipo jugador) {
        if (jugador == null || jugador.getEquipo() != equipo || !jugador.isHabilitado() || estaEnNomina(jugador)) {
            return false;
        }
        nomina.add(new JugadorCompetencia(jugador, this));
        return true;
    }

    public boolean estaEnNomina(JugadorEquipo jugador) {
        for (JugadorCompetencia inscrito : nomina) {
            if (inscrito.getJugadorEquipo() == jugador) {
                return true;
            }
        }
        return false;
    }

    public int cantidadJugadoresHabilitados() {
        int cantidad = 0;
        for (JugadorCompetencia jugador : nomina) {
            if (jugador.isHabilitado()) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
