package clubseneca.deportes;

import java.util.Date;

public class PartidoInterno extends Partido {
    private Equipo equipo1;
    private Equipo equipo2;

    public PartidoInterno(Date fecha, Equipo equipo1, Equipo equipo2) {
        super(fecha);
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public boolean equiposCompatibles() {
        if (equipo1 == null || equipo2 == null || equipo1 == equipo2) {
            return false;
        }
        boolean mismoDeporte = equipo1.getDeporte().getNombre().equalsIgnoreCase(equipo2.getDeporte().getNombre());
        boolean mismaCategoria = equipo1.getCategoria().getNombre().equalsIgnoreCase(equipo2.getCategoria().getNombre());
        return mismoDeporte && mismaCategoria;
    }

    @Override
    public boolean puedeDisputarse() {
        return equiposCompatibles() && equipo1.puedeJugarPartido() && equipo2.puedeJugarPartido();
    }
}
