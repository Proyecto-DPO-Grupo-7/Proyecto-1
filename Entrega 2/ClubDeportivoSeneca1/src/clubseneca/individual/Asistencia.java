package clubseneca.individual;

import java.io.Serializable;
import clubseneca.usuarios.Socio;

public class Asistencia implements Serializable {
    private SesionEntrenamiento sesion;
    private Socio socio;
    private String estado;
    private int nota;
    private String comentario;

    public Asistencia(SesionEntrenamiento sesion, Socio socio, String estado) {
        this.sesion = sesion;
        this.socio = socio;

        if (estadoValido(estado)) {
            this.estado = estado;
        } else {
            this.estado = "FALTO";
        }

        this.nota = 0;
        this.comentario = "";
    }

    public SesionEntrenamiento getSesion() {
        return sesion;
    }

    public Socio getSocio() {
        return socio;
    }

    public String getEstado() {
        return estado;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean registrarEvaluacion(int nota, String comentario) {
        if (nota < 1 || nota > 5) {
            return false;
        }

        this.nota = nota;

        if (comentario == null) {
            this.comentario = "";
        } else {
            this.comentario = comentario;
        }

        return true;
    }

    public boolean tieneEvaluacion() {
        return nota >= 1 && nota <= 5;
    }

    private boolean estadoValido(String estado) {
        if (estado == null) {
            return false;
        }

        if (estado.equalsIgnoreCase("ASISTIO")) {
            return true;
        }

        if (estado.equalsIgnoreCase("FALTO")) {
            return true;
        }

        if (estado.equalsIgnoreCase("LLEGO TARDE")) {
            return true;
        }

        return false;
    }
}
