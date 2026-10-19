package clubseneca.principal;

import clubseneca.comercial.Venta;
import clubseneca.gestion.GestionComercial;
import clubseneca.gestion.GestionDeportiva;
import clubseneca.gestion.GestionTurnos;
import clubseneca.gestion.GestionUsuarios;
import clubseneca.individual.Instalacion;
import clubseneca.persistencia.PersistenciaClub;
import clubseneca.usuarios.Socio;
import clubseneca.usuarios.Usuario;

public class ClubDeportivoSeneca {
    private GestionUsuarios gestionUsuarios;
    private GestionDeportiva gestionDeportiva;
    private GestionComercial gestionComercial;
    private GestionTurnos gestionTurnos;
    private PersistenciaClub persistencia;

    public ClubDeportivoSeneca() {
        gestionUsuarios = new GestionUsuarios();
        gestionDeportiva = new GestionDeportiva();
        gestionComercial = new GestionComercial();
        gestionTurnos = new GestionTurnos();
        persistencia = new PersistenciaClub("datos");
    }

    public GestionUsuarios getGestionUsuarios() {
        return gestionUsuarios;
    }

    public GestionDeportiva getGestionDeportiva() {
        return gestionDeportiva;
    }

    public GestionComercial getGestionComercial() {
        return gestionComercial;
    }

    public GestionTurnos getGestionTurnos() {
        return gestionTurnos;
    }

    public void cargarDatos() {
        gestionUsuarios = persistencia.cargarUsuarios();
        gestionDeportiva = persistencia.cargarDeportiva();
        gestionComercial = persistencia.cargarComercial();
        gestionTurnos = persistencia.cargarTurnos();
    }

    public void guardarDatos() {
        persistencia.guardarUsuarios(gestionUsuarios);
        persistencia.guardarDeportiva(gestionDeportiva);
        persistencia.guardarComercial(gestionComercial);
        persistencia.guardarTurnos(gestionTurnos);
    }

    public boolean registrarCompra(Usuario comprador, Venta venta, Instalacion instalacionActual) {
        if (comprador == null || venta == null) {
            return false;
        }

        if (!comprador.esSocio() && !comprador.esEmpleado()) {
            return false;
        }

        if (venta.getComprador() != comprador) {
            return false;
        }

        if (comprador.esSocio() && venta.contieneBebidaCaliente()) {
            if (instalacionActual != null && instalacionActual.restringeBebidaCaliente()) {
                return false;
            }

            Socio socio = (Socio) comprador;
            socio.setTieneBebidaCaliente(true);
        }

        return gestionComercial.registrarVenta(venta);
    }
}
