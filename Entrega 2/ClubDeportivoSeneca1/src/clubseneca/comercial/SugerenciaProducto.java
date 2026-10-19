package clubseneca.comercial;

import java.io.Serializable;
import java.util.Date;
import clubseneca.usuarios.Administrador;
import clubseneca.usuarios.Empleado;

public class SugerenciaProducto implements Serializable {
    private Date fecha;
    private String tipo;
    private String nombrePropuesto;
    private String descripcion;
    private String estado;
    private Empleado empleado;
    private Administrador administradorRevisor;

    public SugerenciaProducto(Date fecha, String tipo, String nombrePropuesto, String descripcion, Empleado empleado) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.nombrePropuesto = nombrePropuesto;
        this.descripcion = descripcion;
        this.empleado = empleado;
        this.estado = "PENDIENTE";
        this.administradorRevisor = null;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombrePropuesto() {
        return nombrePropuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Administrador getAdministradorRevisor() {
        return administradorRevisor;
    }

    public void aprobar(Administrador administrador) {
        estado = "APROBADA";
        administradorRevisor = administrador;
    }

    public void rechazar(Administrador administrador) {
        estado = "RECHAZADA";
        administradorRevisor = administrador;
    }
}
