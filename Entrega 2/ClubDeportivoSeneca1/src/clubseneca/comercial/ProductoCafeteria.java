package clubseneca.comercial;

public abstract class ProductoCafeteria extends Producto {
    protected ProductoCafeteria(String nombre, double precio) {
        super(nombre, precio);
    }

    public double getTasaImpuesto() {
        return 0.08;
    }

    public boolean esProductoCafeteria() {
        return true;
    }
}
