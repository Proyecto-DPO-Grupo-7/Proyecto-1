package clubseneca.comercial;

import java.io.Serializable;
import java.util.Date;
import clubseneca.usuarios.Socio;

public class PagoMensualidad implements Serializable {
    private Date fecha;
    private double valor;
    private Socio socio;

    public PagoMensualidad(Date fecha, double valor, Socio socio) {
        this.fecha = fecha;
        this.valor = valor;
        this.socio = socio;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getValor() {
        return valor;
    }

    public Socio getSocio() {
        return socio;
    }
}
