package clubseneca.deportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Competencia implements Serializable {
    private String nombre;
    private String entidadOrganiza;
    private int anio;
    private String formatoJuego;
    private Deporte deporte;
    private Categoria categoria;
    private List<InscripcionCompetencia> inscripciones;
    private List<PartidoCompetencia> partidos;

    public Competencia(String nombre, String entidadOrganiza, int anio, String formatoJuego, Deporte deporte, Categoria categoria) {
        this.nombre = nombre;
        this.entidadOrganiza = entidadOrganiza;
        this.anio = anio;
        this.formatoJuego = formatoJuego;
        this.deporte = deporte;
        this.categoria = categoria;
        this.inscripciones = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEntidadOrganiza() {
        return entidadOrganiza;
    }

    public int getAnio() {
        return anio;
    }

    public String getFormatoJuego() {
        return formatoJuego;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void agregarInscripcion(InscripcionCompetencia inscripcion) {
        if (inscripcion != null) {
            inscripciones.add(inscripcion);
        }
    }

    public void registrarPartido(PartidoCompetencia partido) {
        if (partido != null) {
            partidos.add(partido);
        }
    }

    public boolean validarMarcador(int marcador1, int marcador2) {
        if (marcador1 < 0 || marcador2 < 0) {
            return false;
        }

        if (deporte.getNombre().equalsIgnoreCase("Futbol")
                || deporte.getNombre().equalsIgnoreCase("Fútbol")) {
            return true;
        }

        if (marcador1 == marcador2) {
            return false;
        }

        return true;
    }

    public int calcularPuntos(int marcadorPropio, int marcadorRival) {
        if (!validarMarcador(marcadorPropio, marcadorRival)) {
            return -1;
        }

        if (deporte.getNombre().equalsIgnoreCase("Futbol")
                || deporte.getNombre().equalsIgnoreCase("Fútbol")) {

            if (marcadorPropio > marcadorRival) {
                return 3;
            }

            if (marcadorPropio == marcadorRival) {
                return 1;
            }

            return 0;
        }

        if (marcadorPropio > marcadorRival) {
            return 1;
        }

        return 0;
    }

    public List<InscripcionCompetencia> getInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    public List<PartidoCompetencia> getPartidos() {
        return new ArrayList<>(partidos);
    }
}
