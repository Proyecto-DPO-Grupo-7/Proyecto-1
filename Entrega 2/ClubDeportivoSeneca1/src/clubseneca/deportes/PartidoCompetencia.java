package clubseneca.deportes;

import java.util.Date;

public class PartidoCompetencia extends Partido {
    private String institucionRival;
    private String escenario;
    private String fase;
    private Competencia competencia;
    private InscripcionCompetencia inscripcion;

    public PartidoCompetencia(Date fecha, String institucionRival, String escenario, String fase, Competencia competencia, InscripcionCompetencia inscripcion) {
        super(fecha);
        this.institucionRival = institucionRival;
        this.escenario = escenario;
        this.fase = fase;
        this.competencia = competencia;
        this.inscripcion = inscripcion;
    }

    public String getInstitucionRival() {
        return institucionRival;
    }

    public String getEscenario() {
        return escenario;
    }

    public String getFase() {
        return fase;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public InscripcionCompetencia getInscripcion() {
        return inscripcion;
    }

    @Override
    public void registrarMarcador(int marcadorEquipo1, int marcadorEquipo2) {
        if (competencia.validarMarcador(marcadorEquipo1, marcadorEquipo2)) {
            super.registrarMarcador(marcadorEquipo1, marcadorEquipo2);
        }
    }

    @Override
    public boolean puedeDisputarse() {
        if (competencia == null || inscripcion == null) {
            return false;
        }
        boolean mismaCompetencia = inscripcion.getCompetencia() == competencia;
        boolean categoriaValida = inscripcion.getEquipo().getCategoria().getNombre().equalsIgnoreCase(competencia.getCategoria().getNombre());
        boolean deporteValido = inscripcion.getEquipo().getDeporte().getNombre().equalsIgnoreCase(competencia.getDeporte().getNombre());
        return mismaCompetencia && categoriaValida && deporteValido
                && inscripcion.cantidadJugadoresHabilitados() >= competencia.getDeporte().getMinJugadoresHabilitados();
    }
}
