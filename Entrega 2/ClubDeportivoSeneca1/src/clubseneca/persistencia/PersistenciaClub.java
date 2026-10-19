package clubseneca.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import clubseneca.gestion.GestionComercial;
import clubseneca.gestion.GestionDeportiva;
import clubseneca.gestion.GestionTurnos;
import clubseneca.gestion.GestionUsuarios;

public class PersistenciaClub {
    private String carpetaDatos;
    private String archivoUsuarios;
    private String archivoDeportiva;
    private String archivoComercial;
    private String archivoTurnos;

    public PersistenciaClub(String carpetaDatos) {
        this.carpetaDatos = carpetaDatos;
        crearCarpetaSiNoExiste();

        archivoUsuarios = carpetaDatos + File.separator + "usuarios.dat";
        archivoDeportiva = carpetaDatos + File.separator + "deportiva.dat";
        archivoComercial = carpetaDatos + File.separator + "comercial.dat";
        archivoTurnos = carpetaDatos + File.separator + "turnos.dat";
    }

    public void guardarUsuarios(GestionUsuarios gestion) {
        guardarObjeto(gestion, archivoUsuarios);
    }

    public GestionUsuarios cargarUsuarios() {
        Object objeto = cargarObjeto(archivoUsuarios);

        if (objeto == null) {
            return new GestionUsuarios();
        }

        return (GestionUsuarios) objeto;
    }

    public void guardarDeportiva(GestionDeportiva gestion) {
        guardarObjeto(gestion, archivoDeportiva);
    }

    public GestionDeportiva cargarDeportiva() {
        Object objeto = cargarObjeto(archivoDeportiva);

        if (objeto == null) {
            return new GestionDeportiva();
        }

        return (GestionDeportiva) objeto;
    }

    public void guardarComercial(GestionComercial gestion) {
        guardarObjeto(gestion, archivoComercial);
    }

    public GestionComercial cargarComercial() {
        Object objeto = cargarObjeto(archivoComercial);

        if (objeto == null) {
            return new GestionComercial();
        }

        return (GestionComercial) objeto;
    }

    public void guardarTurnos(GestionTurnos gestion) {
        guardarObjeto(gestion, archivoTurnos);
    }

    public GestionTurnos cargarTurnos() {
        Object objeto = cargarObjeto(archivoTurnos);

        if (objeto == null) {
            return new GestionTurnos();
        }

        return (GestionTurnos) objeto;
    }

    private void crearCarpetaSiNoExiste() {
        File carpeta = new File(carpetaDatos);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    private void guardarObjeto(Object objeto, String ruta) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
            salida.writeObject(objeto);
        } catch (Exception e) {
            System.out.println("No se pudo guardar la informacion.");
        }
    }

    private Object cargarObjeto(String ruta) {
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return null;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            return entrada.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la informacion.");
            return null;
        }
    }
}
