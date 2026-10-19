package clubseneca.comercial;

import java.util.ArrayList;
import java.util.List;

public class Snack extends ProductoCafeteria {
    private List<String> alergenos;

    public Snack(String nombre, double precio, List<String> alergenos) {
        super(nombre, precio);
        this.alergenos = new ArrayList<>(alergenos);
    }

    public List<String> getAlergenos() {
        return new ArrayList<>(alergenos);
    }

    public boolean tieneAlergeno(String alergeno) {
        for (String actual : alergenos) {
            if (actual.equalsIgnoreCase(alergeno)) {
                return true;
            }
        }
        return false;
    }
}
