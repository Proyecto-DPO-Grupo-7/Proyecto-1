package clubseneca.deportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Partido implements Serializable {
    private Date fecha;
    private int marcadorEquipo1;
    private int marcadorEquipo2;
    private List<EstadisticaJugador> estadisticas;

    protected Partido(Date fecha) {
        this.fecha = fecha;
        this.marcadorEquipo1 = 0;
        this.marcadorEquipo2 = 0;
        this.estadisticas = new ArrayList<>();
    }

    public Date getFecha() {
        return fecha;
    }

    public int getMarcadorEquipo1() {
        return marcadorEquipo1;
    }

    public int getMarcadorEquipo2() {
        return marcadorEquipo2;
    }

    public void registrarMarcador(int marcadorEquipo1, int marcadorEquipo2) {
        if (marcadorEquipo1 >= 0 && marcadorEquipo2 >= 0) {
            this.marcadorEquipo1 = marcadorEquipo1;
            this.marcadorEquipo2 = marcadorEquipo2;
        }
    }

    public void agregarEstadistica(EstadisticaJugador estadistica) {
        if (estadistica != null) {
            estadisticas.add(estadistica);
        }
    }

    public List<EstadisticaJugador> getEstadisticas() {
        return new ArrayList<>(estadisticas);
    }

    public abstract boolean puedeDisputarse();
}
