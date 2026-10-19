package clubseneca.deportes;

import java.io.Serializable;
import clubseneca.usuarios.Socio;

public class JugadorEquipo implements Serializable {
    private Socio socio;
    private Equipo equipo;
    private String posicion;
    private int numeroCamiseta;
    private int amarillasInternas;
    private boolean suspendidoInterno;

    public JugadorEquipo(Socio socio, Equipo equipo, String posicion, int numeroCamiseta) {
        this.socio = socio;
        this.equipo = equipo;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
        this.amarillasInternas = 0;
        this.suspendidoInterno = false;
    }

    public Socio getSocio() {
        return socio;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void registrarAmarilla() {
        amarillasInternas++;
        if (amarillasInternas >= 2) {
            suspendidoInterno = true;
        }
    }

    public void registrarRoja() {
        suspendidoInterno = true;
    }

    public boolean isHabilitado() {
        return socio.isActivo() && equipo.isActivo() && !suspendidoInterno;
    }

    public void cumplirSuspension() {
        suspendidoInterno = false;
        amarillasInternas = 0;
    }
}
