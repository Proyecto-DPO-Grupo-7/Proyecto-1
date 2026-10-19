package clubseneca.deportes;

import java.io.Serializable;

public class Categoria implements Serializable {
    private String nombre;
    private int edadMinima;
    private int edadMaxima;

    public Categoria(String nombre, int edadMinima, int edadMaxima) {
        this.nombre = nombre;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public boolean aceptaEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }
}
