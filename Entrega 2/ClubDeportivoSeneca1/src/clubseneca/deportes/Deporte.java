package clubseneca.deportes;

import java.io.Serializable;

public class Deporte implements Serializable {
    private String nombre;
    private int minJugadoresHabilitados;

    public Deporte(String nombre, int minJugadoresHabilitados) {
        this.nombre = nombre;
        this.minJugadoresHabilitados = minJugadoresHabilitados;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinJugadoresHabilitados() {
        return minJugadoresHabilitados;
    }
}
